package com.ss.isc.data.viid.common.dto;


public class ViidRegisterPageQueryDTO
        extends ViidRegisterQueryDTO {
    private Integer currentPage;
    private Integer pageSize;

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ViidRegisterPageQueryDTO)) return false;
        ViidRegisterPageQueryDTO other = (ViidRegisterPageQueryDTO) o;
        if (!other.canEqual(this)) return false;
        Object this$currentPage = getCurrentPage(), other$currentPage = other.getCurrentPage();
        if ((this$currentPage == null) ? (other$currentPage != null) : !this$currentPage.equals(other$currentPage))
            return false;
        Object this$pageSize = getPageSize(), other$pageSize = other.getPageSize();
        return !((this$pageSize == null) ? (other$pageSize != null) : !this$pageSize.equals(other$pageSize));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ViidRegisterPageQueryDTO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $currentPage = getCurrentPage();
        result = result * 59 + (($currentPage == null) ? 0 : $currentPage.hashCode());
        Object $pageSize = getPageSize();
        return result * 59 + (($pageSize == null) ? 0 : $pageSize.hashCode());
    }

    public String toString() {
        return "ViidRegisterPageQueryDTO(currentPage=" + getCurrentPage() + ", pageSize=" + getPageSize() + ")";
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
