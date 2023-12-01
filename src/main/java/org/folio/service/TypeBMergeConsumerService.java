package org.folio.service;

import org.folio.event.MergeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TypeBMergeConsumerService extends AbstractMergeConsumerService {


    public TypeBMergeConsumerService(KafkaTemplate<String, Object> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected int mergeData(MergeEvent mergeEvent) {

        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        return 0;
    }
}
