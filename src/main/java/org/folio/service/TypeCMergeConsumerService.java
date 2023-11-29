package org.folio.service;

import org.folio.event.MergeEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TypeCMergeConsumerService extends AbstractMergeConsumerService {


    public TypeCMergeConsumerService(KafkaTemplate<String, Object> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected int mergeData(MergeEvent mergeEvent) {

        throw new RuntimeException("Error merging data");

    }
}
