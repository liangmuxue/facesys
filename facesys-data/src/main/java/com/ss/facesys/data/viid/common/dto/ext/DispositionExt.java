package com.ss.facesys.data.viid.common.dto.ext;

import com.ss.facesys.data.viid.common.dto.common.Disposition;


public class DispositionExt
        extends Disposition {
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof DispositionExt)) return false;
        DispositionExt other = (DispositionExt) o;
        return !!other.canEqual(this);
    }

    protected boolean canEqual(Object other) {
        return other instanceof DispositionExt;
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "DispositionExt()";
    }
}
