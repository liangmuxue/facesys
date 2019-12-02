package com.ss.facesys.data.viid.common.exceptions;

import com.ss.facesys.data.viid.common.dict.ConfirmStatusType;


public class ViidException
        extends RuntimeException {
    private ConfirmStatusType statusType;

    public ViidException(ConfirmStatusType statusType) {
        this.statusType = statusType;
    }


    public ViidException(ConfirmStatusType statusType, String message) {
        super(message);
        this.statusType = statusType;
    }

    public ViidException(ConfirmStatusType statusType, String message, Throwable cause) {
        super(message, cause);
        this.statusType = statusType;
    }


    public ConfirmStatusType getStatusType() {
        return this.statusType;
    }
}
