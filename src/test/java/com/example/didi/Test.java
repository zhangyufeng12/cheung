package com.example.didi;

import java.util.Objects;

public class Test {
    public static void main(String[]args){
        System.out.println("账务哈希：");
        System.out.println(Objects.hash("288396045548961809")%1024);

        System.out.println("分库分表：");
        System.out.println(Objects.hashCode(Long.valueOf("100100050898")%3));




    }
}


