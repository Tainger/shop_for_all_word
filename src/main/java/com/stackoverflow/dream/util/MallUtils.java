package com.stackoverflow.dream.util;

import java.util.UUID;

/**
 * @author rocky
 * @ClassName MmallUtils
 * @Description
 * @Create by rocky
 * @Date 2020/10/17 上午10:28
 */
public class MallUtils {

    // 生成随机字符串
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
