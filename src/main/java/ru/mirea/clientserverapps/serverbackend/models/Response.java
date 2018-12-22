package ru.mirea.clientserverapps.serverbackend.models;

import ru.mirea.clientserverapps.serverbackend.enums.StatusType;

public class Response {
    StatusType statusType;
    Object body;

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public Object getBody() {
        return body;
    }

    public Response(StatusType statusType, Object body) {
        this.statusType = statusType;
        this.body = body;
    }
}
