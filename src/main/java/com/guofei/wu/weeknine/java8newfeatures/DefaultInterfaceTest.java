package com.guofei.wu.weeknine.java8newfeatures;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.guofei.wu.weeknine.sort.javasortutil.User;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Mason
 * @author Mason
 * @version 2018/8/2
 * @since 2018/8/2
 */
public class DefaultInterfaceTest {

    @Test
    public void defaultTest() {
        DefaultInterface di = new DefaultInterface() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 10);
            }
        };

        print(di.calculate(10));
        print(di.sqrt(16));
    }


    public void print(Object value) {
        System.out.println(value);
    }


    @Test
    public void testOptional() {
        Optional<Integer> n = Optional.of(10);

        Optional<Integer> m = Optional.ofNullable(null);


        System.out.println(n.get() + "," + m.orElse(2));

        CacheBuilder.newBuilder().maximumSize(100)
                .expireAfterAccess(30, TimeUnit.SECONDS)
                .build(new CacheLoader<Object, Object>() {
                    @Override
                    public Object load(Object key) throws Exception {
                        return 1;
                    }
                });

    }

}
