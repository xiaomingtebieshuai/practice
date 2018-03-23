package com.practice;

/**
 * Copyright (C), XXX有限公司
 * FileName: Main
 * Author:   chenlu
 * Date:     2018/3/20 11:44
 * Email:  1768245095@qq.com
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
import java.util.*;
public class Main{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int []pointX=new int[number];
        int []pointY=new int[number];
        for(int i = 0;i<number;i++){
            pointX[i]=sc.nextInt();
            pointY[i]=sc.nextInt();
        }
        char c = 'a';
        

        int find=-1;
        boolean founded = false;
        for(int j=0;j<number;j++){
            int k = 0 ;
            for(k=0;k<number;k++){
                if(pointX[j]<pointX[k]&&pointY[j]<pointY[k]){
                    break;
                }
            }
            if(k==number){
                find=j;
                System.out.println(pointX[find]+"  "+pointY[find]);
            }
        }


    }
}

