package com.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), XXX有限公司
 * FileName: TestArrayList
 * Author:   chenlu
 * Date:     2018/3/31 23:00
 * Email:  1768245095@qq.com
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class TestArrayList {
    public static void main(String []args){
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add(i);
        }
        for(int v:list){
            System.out.print(v+" ");
        }
        System.out.println();

        List<Integer> sublist=list.subList(1,3);
        for(int value:sublist){
            System.out.print(value+" ");
        }
        System.out.println();
        sublist.set(1,99);
        for (int val:list){
            System.out.print(val+" ");
        }
    }
}
