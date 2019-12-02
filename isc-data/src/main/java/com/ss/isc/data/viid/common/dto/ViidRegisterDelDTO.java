package com.ss.isc.data.viid.common.dto;


public class ViidRegisterDelDTO {
    private String registerId;

    public String toString() {
        return "ViidRegisterDelDTO(registerId=" + getRegisterId() + ")";
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $registerId = getRegisterId();
        return result * 59 + (($registerId == null) ? 0 : $registerId.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof ViidRegisterDelDTO;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ViidRegisterDelDTO)) return false;
        ViidRegisterDelDTO other = (ViidRegisterDelDTO) o;
        if (!other.canEqual(this)) return false;
        Object this$registerId = getRegisterId(), other$registerId = other.getRegisterId();
        return !((this$registerId == null) ? (other$registerId != null) : !this$registerId.equals(other$registerId));
    }

    public String getRegisterId() {
        return this.registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }
}
