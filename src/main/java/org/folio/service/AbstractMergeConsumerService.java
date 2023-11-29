package org.folio.service;

import org.folio.KafkaListeners;
import org.folio.event.MergeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public abstract class AbstractMergeConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaListeners.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String mergeTopic = MergeJobConstants.MERGE_STATUS_TOPIC;


    @Autowired
    public AbstractMergeConsumerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void consumeMergeEvent(MergeEvent mergeEvent) {

        try {
            logger.info("AbstractMergeConsumerService MergeEvent started:  {}", mergeEvent);

            this.mergeData(mergeEvent);

            mergeEvent.setStatus(String.valueOf(MergeJobConstants.MergeJobStatus.COMPLETED));

            // publish completed event
            kafkaTemplate.send(getTopic(), mergeEvent);
            logger.info("AbstractMergeConsumerService MergeEvent finished:  {}", mergeEvent);
        } catch (Exception e) {

            logger.error("Error processing MergeEvent: {}", mergeEvent, e);

            mergeEvent.setStatus(String.valueOf(MergeJobConstants.MergeJobStatus.FAILED));

            // publish failed event
            kafkaTemplate.send(getTopic(), mergeEvent);
        }
    }

    protected String getTopic() {
        return this.mergeTopic;
    }

    protected abstract int mergeData(MergeEvent mergeEvent);

}
