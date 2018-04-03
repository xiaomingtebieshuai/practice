package com.practice;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;

/**
 * Copyright (C), XXX有限公司
 * FileName: MyStack
 * Author:   chenlu
 * Date:     2018/4/1 11:19
 * Email:  1768245095@qq.com
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class MyStack<T> {
    private LinkedList<T> stack = new LinkedList<>();
    private int size = 0;

    public T push(T item) {
        stack.addFirst(item);
        size++;
        return item;
    }

    public T pop(){
        try {
            if(size<=0){
                throw new Exception();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        size--;
        return stack.removeFirst();
    }

    public void clear(){
        stack.clear();
        size=0;
    }

    public boolean isEmpty(){
        return size==0?true:false;
    }

    public static void main(String []args){
        MyStack<Integer> stack=new MyStack<>();
        for(int i=0;i<4;i++){
            stack.push(i);
        }

        stack.clear();

        while(!stack.isEmpty()){
            int item=stack.pop();
            System.out.println(item);
        }


        Map<String,String> map=new HashMap();
        for(int i=0;i<300;i++){
            map.put(i+"","value"+i);
        }
        System.out.println();

    }

}
