package com.guofei.wu.jvm;

/**
 * @version v1.0
 * @since 2019/3/7
 */
public class CustomClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        return super.findClass(name);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        CustomClassLoader ccl = new CustomClassLoader();
        Class<?> aClass = ccl.loadClass("");
    }
}
