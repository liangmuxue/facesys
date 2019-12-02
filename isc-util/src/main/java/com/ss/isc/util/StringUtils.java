package com.ss.isc.util;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    private static final char SEPARATOR = '_';

    private static final String CHARSET_NAME = "UTF-8";

    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }
        return null;
    }

    public static Boolean toBoolean(Object val) {
        if (val == null) {
            return false;
        }
        return BooleanUtils.toBoolean(val.toString()) || "1".equals(val.toString());
    }

    public static String toString(byte[] bytes) {
        try {
            return new String(bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static String toString(Object obj, String defaultVal) {
        return (obj == null) ? defaultVal : obj.toString();
    }

    public static boolean inString(String str, String... strs) {
        if (str != null) {
            for (String s : strs) {
                if (str.equals(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        return m.replaceAll("");
    }

    public static String replaceMobileHtml(String html) {
        if (html == null) {
            return "";
        }
        return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
    }

    public static Double toDouble(Object val) {
        if (val == null) {
            return 0.0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0.0D;
        }
    }

    public static Float toFloat(Object val) {
        return Float.valueOf(toDouble(val).floatValue());
    }

    public static Long toLong(Object val) {
        return Long.valueOf(toDouble(val).longValue());
    }

    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }

    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        } else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return (remoteAddr != null) ? remoteAddr : request.getRemoteAddr();
    }

    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean nextUpperCase = true;

            if (i < s.length() - 1) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if (i > 0 && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    public static String jsGetVal(String objectString) {
        StringBuilder result = new StringBuilder();
        StringBuilder val = new StringBuilder();
        String[] vals = split(objectString, ".");
        for (int i = 0; i < vals.length; i++) {
            val.append("." + vals[i]);
            result.append("!" + val.substring(1) + "?'':");
        }
        result.append(val.substring(1));
        return result.toString();
    }

    public static String cleanChar(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static boolean checkSuccess(JSONObject resultStr) {
        if ("00000000".equals(resultStr.getString("code")) || "0"
                .equals(resultStr.getString("code")) || "0"
                .equals(resultStr.getString("respCode"))) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String str) {
        if (null == str || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(List<?> list) {
        if (null == list || list.size() == 0) {
            return true;
        }
        return false;
    }

    public static String arrToString(Integer[] arr) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            str.append(arr[i]);
            if (i < arr.length - 1) {
                str.append(",");
            }
        }
        return str.toString();
    }

    public static <T> List<T> pickList(List<T> list, T tClass) {
        if (null == list) {
            list = new ArrayList<T>();
        }
        list.add(tClass);
        return list;
    }

    public static String getPinYinHeadChar(String inputString) {
        inputString = cleanChar(inputString);
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        String output = "";
        if (inputString != null && inputString.length() > 0 && !"null".equals(inputString)) {
            char[] input = inputString.trim().toCharArray();
            try {
                for (int i = 0; i < input.length; i++) {
                    if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                        String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                        output = output + temp[0].charAt(0);
                    } else {
                        output = output + input[i];
                    }
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                logger.error(e.toString(), e);
            }
        } else {
            return "*";
        }
        return output;
    }

    public static <T> List<List<T>> averageAssign(List<T> source, int splitItemNum) {
        List<List<T>> result = new ArrayList<List<T>>();
        if (source != null && source.size() > 0 && splitItemNum > 0) {
            if (source.size() <= splitItemNum) {
                result.add(source);
            } else {
                int splitNum = (source.size() % splitItemNum == 0) ? (source.size() / splitItemNum) : (source.size() / splitItemNum + 1);
                List<T> value = null;
                for (int i = 0; i < splitNum; i++) {
                    if (i < splitNum - 1) {
                        value = source.subList(i * splitItemNum, (i + 1) * splitItemNum);
                    } else {
                        value = source.subList(i * splitItemNum, source.size());
                    }
                    result.add(value);
                }
            }
        }
        return result;
    }

    public static Double formatNumber(String numStr) {
        Double d = Double.parseDouble(numStr);
        DecimalFormat df = new DecimalFormat("0.000000");
        return Double.valueOf(df.format(d));
    }

}
