package com.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class readTxtUtil {
    public static ArrayList<String> toArrayByFileReader1(String name) {
        // 使用ArrayList来存储每行读取到的字符串
        ArrayList<String> arrayList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader bf = null;
        try {
            fr = new FileReader(name);
            bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
                if (null != bf) {
                    bf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 返回数组
        return arrayList;
    }

    public static void main(String[] args) {
        toArrayByFileReader1("D:\\workspace\\IDEAProject\\AdbScript\\src\\main\\resources\\Script\\日常任务.txt");
    }

}
