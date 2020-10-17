package com.stackoverflow.dream.util;

/**
 * @author rocky
 * @ClassName RedisKeyUtils
 * @Description     存储redis中所有相关表的设计
 * @Create by rocky
 * @Date 2020/10/17 上午10:12
 */
public class RedisKeyUtils {

    private static final String PREFIX_TICKET = "ticket";

    private static final String SPLIT = ":";

    /**
     * 获取登陆凭证的key
     * @param ticket
     * @return
     */
    public static String getTicketKey(String ticket) {
        return PREFIX_TICKET + SPLIT + ticket;
    }
}
