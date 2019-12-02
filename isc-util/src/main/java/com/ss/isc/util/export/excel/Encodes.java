package com.ss.isc.util.export.excel;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;


public class Encodes {

    private static final String DEFAULT_URL_ENCODING = "UTF-8";
    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();


    public static String encodeHex(byte[] input) {
        return new String(Hex.encodeHex(input));
    }


    public static byte[] decodeHex(String input) throws Exception {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw new Exception(e);
        }
    }


    public static String encodeBase64(byte[] input) {
        return new String(Base64.encodeBase64(input));
    }


    public static String encodeBase64(String input) {
        try {
            return new String(Base64.encodeBase64(input.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }


    public static byte[] decodeBase64(String input) throws Exception {
        return Base64.decodeBase64(input.getBytes());
    }


    public static String decodeBase64String(String input) {
        try {
            return new String(Base64.decodeBase64(input.getBytes()), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }


    public static String encodeBase62(byte[] input) {
        char[] chars = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            chars[i] = BASE62[(input[i] & 0xFF) % BASE62.length];
        }
        return new String(chars);
    }


    public static String urlEncode(String part) {
        try {
            return URLEncoder.encode(part, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw unchecked(e);
        }
    }


    public static String urlDecode(String part) {
        try {
            return URLDecoder.decode(part, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw unchecked(e);
        }
    }


    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }

        return new RuntimeException(e);
    }

}
