package com.guofei.wu.jvm;

/**
 * @author Mason
 * @version v1.0
 * @since 2019/3/5
 */
public class SuperClass {

    private static int a = staticMethod();

    private int b = getVariable();

    private int getVariable() {
        System.out.println("SuperClass getVariable");
        return 2;
    }

    static {
        System.out.println("SuperClass static code block");
    }

    {
        System.out.println("SuperClass non-static code block");
    }

    public SuperClass() {
        System.out.println("SuperClass constructor");
    }

    private static int staticMethod() {
        System.out.println("SuperClass static variable");
        return 1;
    }

}
