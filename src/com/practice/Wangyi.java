package com.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Copyright (C), XXX有限公司
 * FileName: Wangyi
 * Author:   chenlu
 * Date:     2018/3/23 19:50
 * Email:  1768245095@qq.com
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class Wangyi {
    public static void main(String []args){
//        Scanner sc=new Scanner(System.in);
//        int n=sc.nextInt();
//        String s=n+"";
//        char []array=s.toCharArray();
//        int start=0;
//        int end=array.length-1;
//        while(start<end){
//            char c=array[start];
//            array[start]=array[end];
//            array[end]=c;
//            start++;
//            end--;
//        }
//        //System.out.print(array.toString());
//        int m=Integer.parseInt(new String(array))+n;
//        System.out.println(m);
        
        System.out.print(Wangyi.getAvg());
    }


    public static double getAvg(){
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        char []array=s.toCharArray();
        List list=new ArrayList();
        if(s==null||"".equals(s)){
            System.out.println(0);
            return 0;
        }
        char c=array[0];
        int count=1;
        int i=1;
        int len=array.length;
        for(i=1;i<len;i++){
            if(c!=array[i]){
                list.add(count);
                count=1;
                c=array[i];
            }else{
                count++;
            }
        }
        if(array[len-1]==array[len-2]){
            list.add(count);
        }else{
            list.add(1);
        }

        int p=0;
        for(int j=0;j<list.size();j++){
            p=p+(int)list.get(j);
        }
        double d= (Math.round(p*100)/100.0);
        double size=(Math.round(list.size()*100)/100.0);
        return d/size;
    }
}
