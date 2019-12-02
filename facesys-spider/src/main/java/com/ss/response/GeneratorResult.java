package com.ss.response;

import com.ss.enums.CommonEnumClass;
import com.ss.enums.ResponseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GeneratorResult：结果工具类
 * @author FrancisYs
 * @date 2019/8/13
 * @email yaoshuai@ss-cas.com
 */
public class GeneratorResult {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorResult.class);


    public static <T> ResponseEntity<T> genResult(int succ, T data, String message) {
        ResponseEntity<T> result = new ResponseEntity<T>(succ, message, data);

        if (logger.isDebugEnabled()) {
            logger.debug("generate rest result:{}", result);
        }

        return result;
    }


    public static <T> ResponseEntity<T> genResult(String code, T data, String message) {
        ResponseEntity<T> result = new ResponseEntity<T>(code, message, data);

        if (logger.isDebugEnabled()) {
            logger.debug("generate rest result:{}", result);
        }

        return result;
    }


    public static ResponseEntity<String> genResult(int succ, String message) {
        return genResult(succ, null, message);
    }


    public static ResponseEntity<String> genResult(String code, String message) {
        return genResult(code, null, message);
    }


    public static <T> ResponseEntity<T> genSuccessResult() {
        return genResult(CommonEnumClass.CommonInterfaceEnum.SUCCESS.getKey(), null, CommonEnumClass.CommonInterfaceEnum.SUCCESS.getValue());
    }


    public static <T> ResponseEntity<T> genErrorResult() {
        return genResult(ResponseEnum.FAIL.getCode(), null, ResponseEnum.FAIL.getMessage());
    }

}
