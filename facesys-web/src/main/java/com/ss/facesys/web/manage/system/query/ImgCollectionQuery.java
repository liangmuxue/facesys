package com.ss.facesys.web.manage.system.query;

import com.ss.request.Pagination;

import java.util.StringJoiner;

/**
 * ImgCollectionQuery
 *
 * @author FrancisYs
 * @date 2020/2/18
 * @email yaoshuai@ss-cas.com
 */
public class ImgCollectionQuery extends Pagination {

    private Long collectionTimeMin;
    private Long collectionTimeMax;
    private String userId;

    public Long getCollectionTimeMin() {
        return collectionTimeMin;
    }

    public void setCollectionTimeMin(Long collectionTimeMin) {
        this.collectionTimeMin = collectionTimeMin;
    }

    public Long getCollectionTimeMax() {
        return collectionTimeMax;
    }

    public void setCollectionTimeMax(Long collectionTimeMax) {
        this.collectionTimeMax = collectionTimeMax;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ImgCollectionQuery.class.getSimpleName() + "[", "]")
                .add("collectionTimeMin=" + collectionTimeMin)
                .add("collectionTimeMax=" + collectionTimeMax)
                .add("userId='" + userId + "'")
                .toString();
    }

}
