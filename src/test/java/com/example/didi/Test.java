package com.example.didi;

import java.util.Objects;

public class Test {
    public static void main(String[]args){
        System.out.println("账务哈希：");
        System.out.println(Objects.hash("288396045548961809")%1024);

        System.out.println("分库分表：");
        System.out.println(Objects.hashCode(Long.valueOf("100100050898")%3));



//        int[] a= {1,5,2,4,3};
//        int temp=0;
//        for(int i=a.length-1;i>0;i--){
//            for(int j=0;j<i;j++){
//                if(a[j]>a[j+1]){
//                    temp=a[j];
//                    a[j]=a[j+1];
//                    a[j+1]=temp;
//                }
//            }
//        }
//        for(int m=0;m<a.length;m++)
//            System.out.print(a[m]+"\t");

    }
}


