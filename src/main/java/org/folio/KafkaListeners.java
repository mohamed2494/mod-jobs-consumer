package org.folio;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.folio.event.MergeEvent;
import org.folio.factory.MergeConsumerFactory;
import org.folio.service.AbstractMergeConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private static final Logger logger = LoggerFactory.getLogger(KafkaListeners.class);
    private final ObjectMapper objectMapper;
    private final MergeConsumerFactory mergeConsumerFactory;

    @Autowired
    public KafkaListeners(ObjectMapper objectMapper, MergeConsumerFactory mergeConsumerFactory) {
        this.objectMapper = objectMapper;
        this.mergeConsumerFactory = mergeConsumerFactory;
    }

    @KafkaListener(
            topics = "merge-events",
            groupId = "groupId"
    )
    void listener(String mergeEventJson) {

        logger.info("Received merge event Status JSON: {}", mergeEventJson);

        try {
            MergeEvent mergeEvent = objectMapper.readValue(mergeEventJson, MergeEvent.class);
            logger.info("Converted MergeEvent ID: {}", mergeEvent.getMergeId());

            logger.info("Converted MergeEvent: {}", mergeEvent);
            // Use the factory to get the appropriate merge producer
            AbstractMergeConsumerService mergeConsumerService = this.mergeConsumerFactory.createConsumerService(mergeEvent.getType());
            mergeConsumerService.consumeMergeEvent(mergeEvent);

        } catch (JsonProcessingException e) {
            logger.error("Error converting JSON to MergeEvent: {}", e.getMessage());
        }
    }
}
