package com.stackoverflow.dream.util;

/**
 * @author rocky
 * @ClassName MmMallConstants
 * @Description   整个项目的全局配置
 * @Create by rocky
 * @Date 2020/10/17 上午10:18
 */
public interface MmMallConstants {

    /**
     * 默认状态的登录凭证的超时时间
     */
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    /**
     * 记住状态的登录凭证超时时间
     */
    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;
}
