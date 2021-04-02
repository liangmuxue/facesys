package com.ss.facesys.util.constant;

/**
 * SfgoHttpConstant
 *
 * @author zhangao
 * @date 2020/1/20
 * @email zhangao@ss-cas.com
 */
public class SfgoHttpConstant {

    /**
     * 人脸库添加
     */
    public static String FACE_GROUP_ADD = "/group/add";
    /**
     * 人脸添加
     */
    public static String FACE_ADD = "/face/add";
    /**
     * 人脸检索（多库）
     */
    public static String STATICDB_SEARCH_MULTIPLE = "/staticdb/search/multiple";
    /**
     * 人脸检测
     */
    public static String FACE_DETECT = "/face/detect";
    /**
     * 人脸对比（1:1）
     */
    public static String FACE_MATCH = "/match";
    /**
     * 人脸特征值获取
     */
    public static String FACE_FEATURE = "/face/feature";
    /**
     * 人脸删除：删除用户的某一张人脸，如果该用户只有一张人脸图片，则同时删除用户
     */
    public static String FACESET_FACE_DELETE = "/faceset/face/delete";
    /**
     * 人体特征值获取
     */
    public static String REID_FEATURE = "/reid/feature";
    /**
     * 人体添加
     */
    public static String REID_ADD = "/reid/add";
    /**
     * 人体1：N
     */
    public static String REID_SEARCH = "/reid/search";
    /**
     * 人体对比（1:1）
     */
    public static String BODY_MATCH = "/reid/match";
    /**
     * 人体检测
     */
    public static String REID_DETECT = "/reid/detect";

    /**
     * 根据人脸/体库聚类
     */
    public static String DO_CLUSTER = "/cluster/doClusterByGroupId";

    /**
     * 统计人脸/体聚类信息
     */
    public static String GET_CLUSTER = "/cluster/getClusterCount";

    /**
     * 人脸人体判断
     */
    public static String FACE_OR_BODY_CHECK = "/reid/check";
}
