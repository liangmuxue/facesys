package com.ss.util.redis.page;

import java.util.ArrayList;
import java.util.List;


public class RedisPage<E> extends ArrayList<E> {

    private static final long serialVersionUID = 1L;
    private int pageNum;
    private int pageSize;
    private int startRow;
    private int endRow;
    private long total;
    private int pages;
    private boolean count = true;
    private Boolean reasonable = Boolean.valueOf(true);


    private Boolean pageSizeZero;


    public RedisPage() {
    }


    public RedisPage(int pageNum, int pageSize) {
        this(pageNum, pageSize, true, null);
    }


    public RedisPage(int pageNum, int pageSize, boolean count) {
        this(pageNum, pageSize, count, null);
    }


    public RedisPage(int pageNum, int pageSize, boolean count, Boolean reasonable) {
        super(0);
        if (pageNum == 1 && pageSize == Integer.MAX_VALUE) {
            this.pageSizeZero = Boolean.valueOf(true);
            pageSize = 0;
        }
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.count = count;
        calculateStartAndEndRow();
        setReasonable(reasonable);
    }


    public RedisPage(int[] rowBounds, boolean count) {
        super(0);
        if (rowBounds[0] == 0 && rowBounds[1] == Integer.MAX_VALUE) {
            this.pageSizeZero = Boolean.valueOf(true);
            this.pageSize = 0;
        } else {
            this.pageSize = rowBounds[1];
            this.pageNum = (rowBounds[1] != 0) ? (int) Math.ceil((rowBounds[0] + rowBounds[1]) / rowBounds[1]) : 0;
        }
        this.startRow = rowBounds[0];
        this.count = count;
        this.endRow = this.startRow + rowBounds[1];
    }


    public List<E> getResult() {
        return this;
    }


    public int getPages() {
        return this.pages;
    }


    public RedisPage<E> setPages(int pages) {
        this.pages = pages;
        return this;
    }


    public int getEndRow() {
        return this.endRow;
    }


    public RedisPage<E> setEndRow(int endRow) {
        this.endRow = endRow;
        return this;
    }


    public int getPageNum() {
        return this.pageNum;
    }


    public RedisPage<E> setPageNum(int pageNum) {
        this.pageNum = (this.reasonable != null && this.reasonable.booleanValue() && pageNum <= 0) ? 1 : pageNum;
        return this;
    }


    public int getPageSize() {
        return this.pageSize;
    }


    public RedisPage<E> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }


    public int getStartRow() {
        return this.startRow;
    }


    public RedisPage<E> setStartRow(int startRow) {
        this.startRow = startRow;
        return this;
    }


    public long getTotal() {
        return this.total;
    }


    public void setTotal(long total) {
        this.total = total;
        if (total == -1L) {
            this.pages = 1;
            return;
        }
        if (this.pageSize > 0) {
            this.pages = (int) (total / this.pageSize + ((total % this.pageSize == 0L) ? 0 : 1));
        } else {
            this.pages = 0;
        }

        if (this.reasonable != null && this.reasonable.booleanValue() && this.pageNum > this.pages) {
            this.pageNum = this.pages;
            calculateStartAndEndRow();
        }
    }


    public Boolean getReasonable() {
        return this.reasonable;
    }


    public RedisPage<E> setReasonable(Boolean reasonable) {
        if (reasonable == null) {
            return this;
        }
        this.reasonable = reasonable;

        if (this.reasonable.booleanValue() && this.pageNum <= 0) {
            this.pageNum = 1;
            calculateStartAndEndRow();
        }
        return this;
    }


    public Boolean getPageSizeZero() {
        return this.pageSizeZero;
    }


    public RedisPage<E> setPageSizeZero(Boolean pageSizeZero) {
        if (pageSizeZero != null) {
            this.pageSizeZero = pageSizeZero;
        }
        return this;
    }


    private void calculateStartAndEndRow() {
        this.startRow = (this.pageNum > 0) ? ((this.pageNum - 1) * this.pageSize) : 0;
        this.endRow = this.startRow + this.pageSize * ((this.pageNum > 0) ? 1 : 0) - 1;
    }


    public boolean isCount() {
        return this.count;
    }


    public RedisPage<E> setCount(boolean count) {
        this.count = count;
        return this;
    }


    public RedisPage<E> pageNum(int pageNum) {
        this.pageNum = (this.reasonable != null && this.reasonable.booleanValue() && pageNum <= 0) ? 1 : pageNum;
        return this;
    }


    public RedisPage<E> pageSize(int pageSize) {
        this.pageSize = pageSize;
        calculateStartAndEndRow();
        return this;
    }


    public RedisPage<E> count(Boolean count) {
        this.count = count.booleanValue();
        return this;
    }


    public RedisPage<E> reasonable(Boolean reasonable) {
        setReasonable(reasonable);
        return this;
    }


    public RedisPage<E> pageSizeZero(Boolean pageSizeZero) {
        setPageSizeZero(pageSizeZero);
        return this;
    }


    public PageInfo<E> toPageInfo() {
        return new PageInfo<E>(this);
    }


    public <E> RedisPage<E> doSelectPage(ISelect select) {
        select.doSelect();
        return (RedisPage<E>) this;
    }

    public <E> PageInfo<E> doSelectPageInfo(ISelect select) {
        select.doSelect();
        return (PageInfo<E>) toPageInfo();
    }

    public long doCount(ISelect select) {
        this.pageSizeZero = Boolean.valueOf(true);
        this.pageSize = 0;
        select.doSelect();
        return this.total;
    }


    public String toString() {
        return "Page{count=" + this.count + ", pageNum=" + this.pageNum + ", pageSize=" + this.pageSize + ", startRow=" + this.startRow + ", endRow=" + this.endRow + ", total=" + this.total + ", pages=" + this.pages + ", reasonable=" + this.reasonable + ", pageSizeZero=" + this.pageSizeZero + '}';
    }

}
