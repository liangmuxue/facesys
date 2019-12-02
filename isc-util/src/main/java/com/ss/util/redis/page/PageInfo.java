package com.ss.util.redis.page;

import java.io.Serializable;
import java.util.List;


public class PageInfo<T> extends Object implements Serializable {

    private static final long serialVersionUID = 1L;
    private int pageNum;
    private int pageSize;
    private int size;
    private int startRow;
    private int endRow;
    private long total;
    private int pages;
    private List<T> list;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage = false;
    private boolean isLastPage = false;
    private boolean hasPreviousPage = false;
    private boolean hasNextPage = false;
    private int navigatePages;
    private int[] navigatepageNums;
    private int navigateFirstPage;
    private int navigateLastPage;

    public PageInfo() {
    }

    public PageInfo(List<T> list) {
        this(list, 8);
    }


    public PageInfo(List<T> list, int navigatePages) {
        if (list instanceof RedisPage) {
            RedisPage page = (RedisPage) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();

            this.pages = page.getPages();
            this.list = page;
            this.size = page.size();
            this.total = page.getTotal();

            if (this.size == 0) {
                this.startRow = 0;
                this.endRow = 0;
            } else {
                this.startRow = page.getStartRow() + 1;

                this.endRow = this.startRow - 1 + this.size;
            }
        } else if (list instanceof java.util.Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();

            this.pages = 1;
            this.list = list;
            this.size = list.size();
            this.total = list.size();
            this.startRow = 0;
            this.endRow = (list.size() > 0) ? (list.size() - 1) : 0;
        }
        if (list instanceof java.util.Collection) {
            this.navigatePages = navigatePages;

            calcNavigatepageNums();

            calcPage();

            judgePageBoudary();
        }
    }


    private void calcNavigatepageNums() {
        if (this.pages <= this.navigatePages) {
            this.navigatepageNums = new int[this.pages];
            for (int i = 0; i < this.pages; i++) {
                this.navigatepageNums[i] = i + 1;
            }
        } else {
            this.navigatepageNums = new int[this.navigatePages];
            int startNum = this.pageNum - this.navigatePages / 2;
            int endNum = this.pageNum + this.navigatePages / 2;

            if (startNum < 1) {
                startNum = 1;

                for (int i = 0; i < this.navigatePages; i++) {
                    this.navigatepageNums[i] = startNum++;
                }
            } else if (endNum > this.pages) {
                endNum = this.pages;

                for (int i = this.navigatePages - 1; i >= 0; i--) {
                    this.navigatepageNums[i] = endNum--;
                }
            } else {

                for (int i = 0; i < this.navigatePages; i++) {
                    this.navigatepageNums[i] = startNum++;
                }
            }
        }
    }


    private void calcPage() {
        if (this.navigatepageNums != null && this.navigatepageNums.length > 0) {
            this.navigateFirstPage = this.navigatepageNums[0];
            this.navigateLastPage = this.navigatepageNums[this.navigatepageNums.length - 1];
            if (this.pageNum > 1) {
                this.prePage = this.pageNum - 1;
            }
            if (this.pageNum < this.pages) {
                this.nextPage = this.pageNum + 1;
            }
        }
    }


    private void judgePageBoudary() {
        this.isFirstPage = (this.pageNum == 1);
        this.isLastPage = (this.pageNum == this.pages);
        this.hasPreviousPage = (this.pageNum > 1);
        this.hasNextPage = (this.pageNum < this.pages);
    }


    public int getPageNum() {
        return this.pageNum;
    }


    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }


    public int getPageSize() {
        return this.pageSize;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public int getSize() {
        return this.size;
    }


    public void setSize(int size) {
        this.size = size;
    }


    public int getStartRow() {
        return this.startRow;
    }


    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }


    public int getEndRow() {
        return this.endRow;
    }


    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }


    public long getTotal() {
        return this.total;
    }


    public void setTotal(long total) {
        this.total = total;
    }


    public int getPages() {
        return this.pages;
    }


    public void setPages(int pages) {
        this.pages = pages;
    }


    public List<T> getList() {
        return this.list;
    }


    public void setList(List<T> list) {
        this.list = list;
    }


    @Deprecated
    public int getFirstPage() {
        return this.navigateFirstPage;
    }


    @Deprecated
    public void setFirstPage(int firstPage) {
        this.navigateFirstPage = firstPage;
    }


    public int getPrePage() {
        return this.prePage;
    }


    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }


    public int getNextPage() {
        return this.nextPage;
    }


    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }


    @Deprecated
    public int getLastPage() {
        return this.navigateLastPage;
    }


    @Deprecated
    public void setLastPage(int lastPage) {
        this.navigateLastPage = lastPage;
    }


    public boolean isIsFirstPage() {
        return this.isFirstPage;
    }


    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }


    public boolean isIsLastPage() {
        return this.isLastPage;
    }


    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }


    public boolean isHasPreviousPage() {
        return this.hasPreviousPage;
    }


    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }


    public boolean isHasNextPage() {
        return this.hasNextPage;
    }


    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }


    public int getNavigatePages() {
        return this.navigatePages;
    }


    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }


    public int[] getNavigatepageNums() {
        return this.navigatepageNums;
    }


    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }


    public int getNavigateFirstPage() {
        return this.navigateFirstPage;
    }


    public int getNavigateLastPage() {
        return this.navigateLastPage;
    }


    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }


    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }


    public String toString() {
        StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageNum=").append(this.pageNum);
        sb.append(", pageSize=").append(this.pageSize);
        sb.append(", size=").append(this.size);
        sb.append(", startRow=").append(this.startRow);
        sb.append(", endRow=").append(this.endRow);
        sb.append(", total=").append(this.total);
        sb.append(", pages=").append(this.pages);
        sb.append(", list=").append(this.list);
        sb.append(", prePage=").append(this.prePage);
        sb.append(", nextPage=").append(this.nextPage);
        sb.append(", isFirstPage=").append(this.isFirstPage);
        sb.append(", isLastPage=").append(this.isLastPage);
        sb.append(", hasPreviousPage=").append(this.hasPreviousPage);
        sb.append(", hasNextPage=").append(this.hasNextPage);
        sb.append(", navigatePages=").append(this.navigatePages);
        sb.append(", navigateFirstPage").append(this.navigateFirstPage);
        sb.append(", navigateLastPage").append(this.navigateLastPage);
        sb.append(", navigatepageNums=");
        if (this.navigatepageNums == null) {
            sb.append("null");
        } else {
            sb.append('[');
            for (int i = 0; i < this.navigatepageNums.length; i++)
                sb.append((i == 0) ? "" : ", ").append(this.navigatepageNums[i]);
            sb.append(']');
        }

        sb.append('}');
        return sb.toString();
    }

}
