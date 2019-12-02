package com.ss.request;

import com.ss.annotation.IntLength;
import com.ss.valide.APIFeatureExtractionGroup;
import com.ss.valide.APIGetsGroup;
import com.ss.valide.APIListGroup;
import com.ss.valide.APIPageGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Pagination 分页父类
 *
 * <p>提供通用分页属性以下分页属性及对参数非空的校验</p>
 * <p>currentPage: 当前页码</p>
 * <p>pageSize: 每页数据量</p>
 *
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
public class Pagination implements Serializable {

    private static final long serialVersionUID = -1L;

    @NotNull(message = "{currentPage.empty}", groups = {APIPageGroup.class})
    @IntLength(min = 0, max = 8, message = "{currentPage.length}", groups = {APIPageGroup.class, APIGetsGroup.class, APIListGroup.class, APIFeatureExtractionGroup.class})
    private Integer currentPage;
    @NotNull(message = "{pageSize.empty}", groups = {APIPageGroup.class})
    @IntLength(min = 0, max = 8, message = "{pageSize.length}", groups = {APIPageGroup.class, APIGetsGroup.class, APIListGroup.class, APIFeatureExtractionGroup.class})
    private Integer pageSize;

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
