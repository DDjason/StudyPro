package com.example.jason.studypro;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/28$ 14:20$
 * <p/>
 */
public class SimplePoJo {
    private String name;
    private String age;

    public SimplePoJo() {

    }

    public SimplePoJo(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public static class InnerSimplePojo {
        public static final String info = "Inner";
    }
}
