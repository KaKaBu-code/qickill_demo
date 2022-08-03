package com.example.qickill_demo.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }
    private static final String salt = "1a2b3c4d";// 和前端统一，后端需要盐获取密钥
    public static String inputPassToFromPass(String inputPass) {
        String str =""+ salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass, String salt) {
        String fromPass = inputPassToFromPass(inputPass);
        String dbPass = formPassToDBPass(fromPass, salt);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFromPass("121"));
        System.out.println(formPassToDBPass("c3e601a8a1a2a6f13e9d1fac40f66c9e","1a2b3c4d"));
        System.out.println(inputPassToDBPass("121","1a2b3c4d"));
    }
}
