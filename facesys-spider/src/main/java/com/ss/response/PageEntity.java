package com.ss.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PageEntity<T>
        extends Object
        implements Serializable {
    private Logger logger = LoggerFactory.getLogger(PageEntity.class);

    private static final long serialVersionUID = 2029283115976464985L;
    @JsonView({IPageEntityView.class})
    private int pageSize;
    @JsonView({IPageEntityView.class})
    private int currentPage;
    @JsonView({IPageEntityView.class})
    private int totalPages;
    @JsonView({IPageEntityView.class})
    private int totalRows;
    @JsonView({IPageEntityView.class})
    private int minRowNumber;
    @JsonView({IPageEntityView.class})
    private int maxRowNumber;
    @JsonView({IPageEntityView.class})
    private List<?> datas;
    private boolean reasonable;

    public PageEntity(int rowsOfPage, int currentPage) {
        this.reasonable = true;


        this.pageSize = rowsOfPage;
        this.currentPage = currentPage;
        this.totalPages = 0;
        this.totalRows = -1;
        this.minRowNumber = 0;
        this.maxRowNumber = 0;
    }


    public PageEntity(Page<T> list) {
        this.reasonable = true;
        this.currentPage = list.getPageNum();
        this.datas = list.getResult();
        this.totalPages = list.getPages();
        this.totalRows = (int) list.getTotal();
        this.pageSize = list.getPageSize();
        this.reasonable = list.getReasonable().booleanValue();
        calculate(this.totalRows);
    }


    public void calculate(int totalRows) {
        if (totalRows % this.pageSize == 0) {
            this.totalPages = totalRows / this.pageSize;
        } else {
            this.totalPages = totalRows / this.pageSize + 1;
        }

        if (this.reasonable && this.currentPage > this.totalPages) {
            this.currentPage = this.totalPages;
        }


        this.minRowNumber = (this.currentPage - 1) * this.pageSize + 1;
        this.maxRowNumber = this.minRowNumber + this.pageSize - 1;

        if (this.reasonable && this.maxRowNumber > totalRows) {
            this.maxRowNumber = totalRows;
        }

        if (this.minRowNumber < 0) {
            this.minRowNumber = 0;
        }

        if (this.reasonable && this.currentPage < 1) {
            this.currentPage = 1;
        }

        this.totalRows = totalRows;
    }


    public int getCurrentPage() {
        return this.currentPage;
    }


    public List<?> getDatas() {
        return this.datas;
    }


    public int getMaxRowNumber() {
        return this.maxRowNumber;
    }


    public int getMinRowNumber() {
        return this.minRowNumber;
    }


    public int getTotalPages() {
        return this.totalPages;
    }


    public int getTotalRows() {
        return this.totalRows;
    }


    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


    public void setDatas(List<?> datas) {
        this.datas = datas;
    }


    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }


    public int getPageSize() {
        return this.pageSize;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public boolean isReasonable() {
        return this.reasonable;
    }


    public void setReasonable(boolean reasonable) {
        this.reasonable = reasonable;
    }

    public static interface IPageEntityView extends ResponseEntity.IResponseEntityView {

    }

}
