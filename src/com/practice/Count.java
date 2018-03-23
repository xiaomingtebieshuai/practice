package com.practice;

/**
 * Copyright (C), XXX有限公司
 * FileName: Count
 * Author:   chenlu
 * Date:     2018/3/23 19:33
 * Email:  1768245095@qq.com
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Count {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Date start = new Date();

        String dir = "G:/macdata/2017/06";
        List<String> list = new ArrayList<>();
        Count count = new Count();
        count.getFileNumber(dir, list);
        Map<String, Set<String>> map = count.getDataByMin(list);
        CountDownLatch cdl = new CountDownLatch(2);

        List<String> m = count.getDataByHour(map);
        //List<String> l2 = count.sortByTime(m, m.size(), "yyyy\\mm\\dd\\hh");
        //new MyThread(l2, "G:\\outHour.txt", cdl).start();
        System.out.println("outHour has started");
        List<String> result = count.getDataByDay(map);
        for(int i =0;i<m.size();i++){
            System.out.println(m.get(i));
        }


        System.out.println(new Date().getTime() - start.getTime());
        System.exit(0);
    }

    public List<String> getDataByDay(Map<String, Set<String>> map) {
        List<String> result = new ArrayList<>();
        String basepath = "G:\\macdata\\2017\\06\\";
        for (int i = 1; i < 31; i++) {// ÿ��
            String currDay = null;
            Set<String> set = new TreeSet<>();
            if (i < 10) {
                currDay = "0" + i;
            } else {
                currDay = "" + i;
            }

            for (int j = 0; j < 24; j++) {// ÿ��Сʱ
                String currHour = null;
                if (j < 10) {
                    currHour = "0" + j;
                } else {
                    currHour = "" + j;
                }
                for (int k = 0; k < 60; k++) {
                    String currMin = null;
                    if (k < 10) {
                        currMin = "0" + k;
                    } else {
                        currMin = "" + k;
                    }

                    String path = basepath + currDay + "\\" + currHour + "\\" + currMin + "\\" + "part-00000";
                    Set<String> s = map.get(path);
                    if (s == null)
                        continue;
                    Iterator<String> it = s.iterator();
                    while (it.hasNext()) {
                        set.add(it.next());
                    }
                    //result.add("2017\\06\\" + currDay+": "+ set.size());
                }
            }
            if(set.size()!=0)
                result.add("2017\\06\\" + currDay+": "+ set.size());
        }

        return result;
    }

    public Map<String, Set<String>> getDataByMin(List<String> files) {
        AtomicInteger number = new AtomicInteger(0);
        int size = files.size();
        final Map<String, Set<String>> result = new ConcurrentHashMap<>(21800);

        CountDownLatch cdl = new CountDownLatch(size);
        for (int i = 0; i < files.size(); i++) {
            final String filepath = files.get(i);
            final Set<String> set = new TreeSet<>();
            MyThreadPool.submit(new MyThreadPool.QueryTask(cdl, new MyThreadPool.QueryHandler() {

                @Override
                public void doQuery() {
                    // TODO Auto-generated method stub
                    try {
                        //System.out.println(filepath + "  starts");
                        BufferedReader br = new BufferedReader(new FileReader(filepath));
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            if (!"".equals(line)) {
                                String mac = line.split(",")[0];
                                set.add(mac);
                            } else {
                                continue;
                            }
                        }
                        result.put(filepath, set);
                        System.out.println(filepath + "  is over");
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }));
        }
        try {
            cdl.await();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return result;
    }

    public void getFileNumber(String dir, List<String> list) {
        File file = new File(dir);
        if (file.isDirectory()) {
            String[] files = file.list();
            for (int i = 0; i < files.length; i++) {
                // System.out.println(files[i]);
                getFileNumber(dir + "/" + files[i], list);
            }
        } else if ("part-00000".equals(file.getName())) {
            list.add(file.getAbsolutePath());
        }
    }

    public List<String> getDataByHour(Map<String, Set<String>> map) {
        String basepath = "G:\\macdata\\2017\\06\\";
        List<String> result = new ArrayList<>();
        for (int i = 1; i < 31; i++) {// ÿ��
            String currDay = null;
            if (i < 10) {
                currDay = "0" + i;
            } else {
                currDay = "" + i;
            }

            for (int j = 0; j < 24; j++) {// ÿ��Сʱ
                String currHour = null;
                Set<String> set = new TreeSet<>();
                if (j < 10) {
                    currHour = "0" + j;
                } else {
                    currHour = "" + j;
                }
                for (int k = 0; k < 60; k++) {
                    String currMin = null;
                    if (k < 10) {
                        currMin = "0" + k;
                    } else {
                        currMin = "" + k;
                    }

                    String path = basepath + currDay + "\\" + currHour + "\\" + currMin + "\\" + "part-00000";
                    Set<String> s = map.get(path);
                    if (s == null)
                        continue;
                    Iterator<String> it = s.iterator();
                    while (it.hasNext()) {
                        set.add(it.next());
                    }

                }
                if(set.size() != 0)
                    result.add("2017\\06\\" + currDay + "\\" + currHour+": "+ set.size());
            }

        }

        return result;
    }

    public ArrayList<String> sortByTime(Map<String, Integer> map, int c, final String pattern) {

        ArrayList<String> result = new ArrayList<>(c);
        Iterator<Entry<String, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Integer> entry = it.next();
            result.add(entry.getKey() + ":  " + entry.getValue());
        }

        Collections.sort(result, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        // ����
                        String sdate1 = o1.split(":")[0];
                        String sdate2 = o2.split(":")[0];
                        try {
                            Date date1 = new SimpleDateFormat(pattern).parse(sdate1);
                            Date date2 = new SimpleDateFormat(pattern).parse(sdate2);
                            if (date1.getTime()>date2.getTime()) {
                                return -1;
                            }
                            if (date1.equals(date2)) {
                                return 0;
                            } else {
                                return 1;
                            }
                        } catch (Exception e) {
                            return -1;
                        }
                    }
                }

        );
        return result;
    }

}
