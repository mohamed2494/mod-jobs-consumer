package org.folio.service;

public class MergeJobConstants {


    public static final String MERGE_STATUS_TOPIC = "merge-events-status";

    public enum MergeJobStatus {NEW, PENDING, COMPLETED, FAILED}


    public enum MergeType {
        TYPE_A, TYPE_C, TYPE_B
    }

}
