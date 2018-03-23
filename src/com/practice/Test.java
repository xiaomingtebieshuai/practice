package com.practice;

import java.util.Scanner;

/**
 * Copyright (C), XXX有限公司
 * FileName: Test
 * Author:   chenlu
 * Date:     2018/3/21 23:44
 * Email:  1768245095@qq.com
 * Description:  求最大子数列和的最大值
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class Test {
    public static void main(String[]args){
        int []array={-11,-9,2,-6};
        System.out.println(Test.getMax(array));


    }

    /**
     *
     * @param a 输入的序列
     * @return 最大子序列值的和
     */
    public static int getMax(int []a){
//        if(Test.getStart(a)==-1){
//            return Test.getStartMax(a);
//        }
        int maxSum=a[0],sum=0;
        for(int i=0;i<a.length;i++){
            sum+=a[i];
            if(sum>maxSum){
                maxSum=sum;
            }else if(sum<0){
                sum=0;
            }
        }
        return maxSum;
    }

    public static int getStart(int []array){
        int i=0;

        for(i=0;i<array.length;i++){
            if(array[i]<0){
                continue;
            }else{
                return i;
            }
        }

        if(i==array.length){
            return -1;
        }else{
            return i;
        }

    }

    public static int getStartMax(int []array){
        int temp=array[0];
        for(int i=1;i<array.length;i++){
            if(temp<array[i]){
                temp=array[i];
            }
        }
        return temp;
    }



}
