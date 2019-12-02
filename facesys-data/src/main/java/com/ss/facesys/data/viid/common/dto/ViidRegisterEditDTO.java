package com.ss.facesys.data.viid.common.dto;


public class ViidRegisterEditDTO
        extends ViidRegisterAddDTO {
    private String registerId;

    public String toString() {
        return "ViidRegisterEditDTO(registerId=" + getRegisterId() + ")";
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $registerId = getRegisterId();
        return result * 59 + (($registerId == null) ? 0 : $registerId.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof ViidRegisterEditDTO;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ViidRegisterEditDTO)) return false;
        ViidRegisterEditDTO other = (ViidRegisterEditDTO) o;
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
