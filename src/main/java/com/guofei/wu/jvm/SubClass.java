package com.guofei.wu.jvm;

/**
 * @author Mason
 * @version v1.0
 * @since 2019/3/5
 */
public class SubClass extends SuperClass {
    private static String str = staticString();

    private String str2 = getString();

    {
        System.out.println("SubClass non-static code block");
    }

    private String getString() {
        System.out.println("SubClass get String");
        return null;
    }

    private static String staticString() {
        System.out.println("SubClass static String");
        return null;
    }


    public SubClass() {
        System.out.println("subClass constructor");
    }

    static {
        System.out.println("SubClass static code block");
    }

}
