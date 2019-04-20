package com.guofei.wu.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Mason
 * @version v1.0
 * @since 2019/3/6
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] buffer = new byte[is.available()];
                    is.read(buffer);
                    return defineClass(name, buffer, 0, buffer.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object o = classLoader.loadClass("com.guofei.wu.jvm.ClassLoaderTest").newInstance();
//        System.out.println(o.getClass());
//        System.out.println(o instanceof com.guofei.wu.jvm.ClassLoaderTest);
//
//        System.out.println(classLoader.getParent());

        ClassLoaderTest.sayHello('a');
    }



//    public static void sayHello(int a){
//        System.out.println(a);
//    }

    public static void sayHello(float a){

        System.out.println(a);
    }

    public static void sayHello(long a){
        int intSize = Integer.SIZE;

        System.out.println("    int size: " + (intSize/8) + "Byte" );



        int shortSize = Short.SIZE;

        System.out.println("  short size: " + (shortSize/8) + "Byte" );



        int longSize = Long.SIZE;

        System.out.println("   long size: " + (longSize/8) + "Byte" );



        int byteSize = Byte.SIZE;

        System.out.println("   byte size: " + (byteSize/8) + "Byte" );



        int floatSize = Float.SIZE;

        System.out.println("  float size: " + (floatSize/8) + "Byte" );



        int doubleSize = Double.SIZE;

        System.out.println(" double size: " + (doubleSize/8) + "Byte" );
    }



}
