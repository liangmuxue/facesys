package com.ss.isc.data.viid.common.dto;

import com.ss.isc.data.viid.common.dto.ext.DispositionExt;

import java.util.List;

public class DispositionsEditDTO
        extends DispositionsAddDTO {
    public DispositionsEditDTO() {
    }

    public DispositionsEditDTO(List<DispositionExt> dispositions, String appId, String systemId) {
        super(dispositions, appId, systemId);
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof DispositionsEditDTO)) return false;
        DispositionsEditDTO other = (DispositionsEditDTO) o;
        return !!other.canEqual(this);
    }

    protected boolean canEqual(Object other) {
        return other instanceof DispositionsEditDTO;
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "DispositionsEditDTO()";
    }
}
