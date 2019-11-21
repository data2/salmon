package com.data2.example;

public class TestEnum {
    enum A {
        AA , AAA
    }

    public static void main(String[] args) {
        System.out.println(A.AA.name());
        System.out.println(A.AA.toString());
        System.out.println(A.valueOf("AA").name());

    }
}
