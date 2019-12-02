package com.ss.tools;

import com.ss.tools.file.FileType;
import com.ss.tools.file.FileTypeJudge;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

/**
 * 图片base64转换工具类
 *
 * @author FrancisYs
 * @date 2019/10/17
 * @email yaoshuai@ss-cas.com
 */
public class Base64ImageUtils {

    /**
     * 校验图片是否是base64字符串
     *
     * @param base64Data
     * @return
     */
    public static boolean isCheckBase64(String base64Data) {
        boolean state = true;
        try {
            byte[] imgByte = Base64.decodeBase64(base64Data.replaceAll(" ", "+"));
            FileType rtype = FileTypeJudge.getType(imgByte);
            if (rtype == null) {
                return false;
            }
            if (StringUtils.isEmpty(rtype.toString())) {
                state = false;
            }
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    private static String getBase64FileType(String base64Data, String... types) {
        try {
            byte[] imgByte = Base64.decodeBase64(base64Data.replaceAll(" ", "+"));
            FileType rtype = FileTypeJudge.getType(imgByte);
            if (rtype == null) {
                return null;
            }
            String type = rtype.toString().toLowerCase();
            if (types == null || types.length == 0) {
                return type;
            }
            for (String t : types) {
                if (t != null) {
                    if (t.toLowerCase().equals(type)) {
                        return type;
                    }
                }
            }
        } catch (Exception exception) {
            System.err.println("获取base图片类型异常：" + exception.getMessage());
        }
        return null;
    }

    /**
     * 校验图片是否为指定格式的base64内容
     *
     * @param base64Data
     * @param types
     * @return
     */
    public static boolean isCheckBase64(String base64Data, String... types) {
        String type = getBase64FileType(base64Data, types);
        return !StringUtils.isEmpty(type);
    }

}
