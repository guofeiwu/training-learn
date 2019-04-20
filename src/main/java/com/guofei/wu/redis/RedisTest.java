package com.guofei.wu.redis;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author Mason
 * @version v1.0
 * @since 2019/3/14
 */
public class RedisTest {

    @Test
    public void testStrSet(){
        RedisUtils.strSet("test1","value1");

    }
}
