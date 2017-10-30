package com.example.jason.studypro.common;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/21$ 10:12$
 * <p/>
 */
public class TestJava {
    private static ReadBookInter interBook = new EnglishBook();

    public static void main(String[] args) {
        System.out.println(interBook.getBookName());
    }
}
