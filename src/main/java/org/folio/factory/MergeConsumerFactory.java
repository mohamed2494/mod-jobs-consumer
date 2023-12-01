package org.folio.factory;

import org.folio.service.*;
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

        if (type.equalsIgnoreCase(String.valueOf(MergeJobConstants.MergeType.TYPE_A))) {
            return typeAMergeService;
        } else if (type.equalsIgnoreCase(String.valueOf(MergeJobConstants.MergeType.TYPE_B))) {
            return typeBMergeService;
        }
        // Handle unknown merge types or return a default producer
        return typeCMergeConsumerService;
    }
}