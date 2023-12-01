package org.folio.event;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class MergeEvent implements Serializable {
    private final String type;
    private final String mergeId;
    private final String source;
    private final String destination;
    private String status;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    private String tenantId;


    @JsonCreator
    public MergeEvent(
            @JsonProperty("mergeId") String mergeId,
            @JsonProperty("source") String source,
            @JsonProperty("destination") String destination,
            @JsonProperty("type") String type,
            @JsonProperty("status") String status
    ) {
        this.mergeId = mergeId;
        this.source = source;
        this.destination = destination;
        this.status = status;
        this.type = type;

    }

    public String getMergeId() {
        return mergeId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MergeEvent{" +
                "type='" + type + '\'' +
                ", mergeId='" + mergeId + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", status='" + status + '\'' +
                ", tenantId='" + tenantId + '\'' +
                '}';
    }
}
