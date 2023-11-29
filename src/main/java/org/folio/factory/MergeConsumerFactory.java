package org.folio.factory;

import org.folio.service.AbstractMergeConsumerService;
import org.folio.service.TypeAMergeConsumerService;
import org.folio.service.TypeBMergeConsumerService;
import org.folio.service.TypeCMergeConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MergeConsumerFactory {
    private final TypeAMergeConsumerService typeAMergeService;
    private final TypeBMergeConsumerService typeBMergeService;

    private final TypeCMergeConsumerService typeCMergeConsumerService;

    @Autowired
    public MergeConsumerFactory(TypeAMergeConsumerService typeAMergeService, TypeBMergeConsumerService typeBMergeService, TypeCMergeConsumerService typeCMergeConsumerService) {
        this.typeAMergeService = typeAMergeService;
        this.typeBMergeService = typeBMergeService;
        this.typeCMergeConsumerService = typeCMergeConsumerService;
    }

    public AbstractMergeConsumerService createConsumerService(String type) {

        if ("TypeA".equalsIgnoreCase(type)) {
            return typeAMergeService;
        } else if ("TypeB".equalsIgnoreCase(type)) {
            return typeBMergeService;
        }
        // Handle unknown merge types or return a default producer
        return typeCMergeConsumerService;
    }
}