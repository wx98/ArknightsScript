package com.arknights;

import com.util.AdbActionUtil;

import java.io.IOException;

public class Arknights {

    private static final AdbActionUtil arknights =  AdbActionUtil.getSingleton();


    public static void main(String[] args) throws IOException, InterruptedException {
//
        //arknights.startmumu();
//        arknights.taskFlow("src\\main\\resources\\Script\\启动模拟器.txt");
        arknights.con();
//        ArrayList<Hashtable<String, Integer>> flow = new ArrayList<Hashtable<String, Integer>>();
//        arknights.taskFlow("src\\main\\resources\\Script\\基建.txt");
//        arknights.shd(120);
        for (int i = 0; i < 2; i++) {
//            arknights.taskFlow("src\\main\\resources\\Script\\1-7.txt");
//            arknights.taskFlow("src\\main\\resources\\Script\\2-10.txt");
//            arknights.taskFlow("src\\main\\resources\\Script\\of-8.txt");
            arknights.taskFlow("src\\main\\resources\\Script\\of-f4.txt");
//            arknights.taskFlow("src\\main\\resources\\Script\\龙门市区.txt");
//            arknights.taskFlow("src\\main\\resources\\Script\\RI-8.txt");

//            arknights.taskFlow("src\\main\\resources\\Script\\RI-7.txt");

        }
        arknights.taskFlow("src\\main\\resources\\Script\\日常任务.txt");
        arknights.taskFlow("src\\main\\resources\\Script\\基建.txt");
        arknights.taskFlow("src\\main\\resources\\Script\\日常任务.txt");

    }





}
