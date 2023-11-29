package org.folio.request;

public class MergeRequest {
    private String type;
    private String sourceData;
    private String destinationData;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceData() {
        return sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    public String getDestinationData() {
        return destinationData;
    }

    public void setDestinationData(String destinationData) {
        this.destinationData = destinationData;
    }
}