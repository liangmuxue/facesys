package com.ss.util.nasstorage.enums;


public class EnumClass {

    public enum StorageImageType {
        STORAGE_IMAGE_TYPE_USER("user", "用户账号人脸图片"),


        STORAGE_IMAGE_TYPE_FACEPIC("facepic", "抓拍人脸图片"),


        STORAGE_IMAGE_TYPE_FULLPIC("fullpic", "抓拍全景图片"),


        STORAGE_IMAGE_TYPE_CARNOPIC("carnopic", "车牌图片"),


        STORAGE_IMAGE_TYPE_CARFULLPIC("carfullpic", "车辆抓拍全景图片"),


        STORAGE_IMAGE_TYPE_CT_FACEPIC("ctface", "人证合一抓拍人脸"),


        STORAGE_IMAGE_TYPE_CT_CARD("ctcard", "人证合一证件图"),


        STORAGE_IMAGE_TYPE_GROUPDB("groupdb", "底库图片"),


        STORAGE_IMAGE_TYPE_WARNING("warning", "报警"),


        STORAGE_IMAGE_TYPE_RECOG("recog", "检索条件图片和结果图片"),


        STORAGE_IMAGE_TYPE_VICTORY("victory", "战果图片"),


        STORAGE_IMAGE_TYPE_FAVORITE("favorite", "收藏图片"),


        STORAGE_IMAGE_TYPE_GUARD("guard", "门禁流水抓拍图片"),


        STORAGE_IMAGE_TYPE_RESIDENT("resident", "居民注册图片"),


        STORAGE_IMAGE_TYPE_LION_ANALYSIS("lionanalysis", "实有人口分析图片"),


        STORAGE_IMAGE_TYPE_OEM("oem", "oem图片"),


        STORAGE_IMAGE_TYPE_DT_FACE("dtface", "终端设备抓拍人脸图"),


        STORAGE_IMAGE_TYPE_DT_DTFACEFULL("dtfacefull", "终端设备全景照");

        private final String type;

        private final String desc;

        StorageImageType(String type, String desc) {
            this.type = type;
            this.desc = desc;
        }


        public String getType() {
            return this.type;
        }


        public String getDesc() {
            return this.desc;
        }
    }

}
