package com.util;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AdbActionUtil {

    private volatile static AdbActionUtil adbActionUtil;

    private AdbActionUtil() {
    }

    public static AdbActionUtil getSingleton() {
        if (adbActionUtil == null) {
            synchronized (AdbActionUtil.class) {
                if (adbActionUtil == null) {
                    adbActionUtil = new AdbActionUtil();
                }
            }
        }
        return adbActionUtil;
    }


    /**
     * 脚本流文件
     *
     * @param taskFlow
     * @throws IOException
     * @throws InterruptedException
     */
    public void taskFlow(String taskFlow) throws IOException, InterruptedException {
        ArrayList<String> tasks = readTxtUtil.toArrayByFileReader1(taskFlow);
        for (String task : tasks) {
            adbActionUtil.branch(task);
        }
    }

    /**
     * 命令分支
     *
     * @param task
     * @throws IOException
     * @throws InterruptedException
     */
    public void branch(String task) throws IOException, InterruptedException {
        String[] operating = task.split(":");
        switch (operating[0]) {
            case "tap":
                adbActionUtil.tap(task);
                break;
            case "swipe":
                adbActionUtil.swipe(task);
                break;
            case "lop":
                adbActionUtil.loop(task);
                break;
            case "slp":
                adbActionUtil.slp(task);
                break;
            case "cmd":
                adbActionUtil.cmd(task);
                break;
            case "con":
                adbActionUtil.con();
                break;

        }
    }


    /**
     * 关机定时
     *
     * @param time
     * @throws IOException
     * @throws InterruptedException
     */
    public void shd(int time) throws IOException, InterruptedException {
        CmdUtil cmdUtil = new CmdUtil();
        cmdUtil.shutdown(time);

    }

    /**
     * 连接Adb
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void con() throws IOException, InterruptedException {
        CmdUtil cmdUtil = new CmdUtil();
        cmdUtil.exec("adb connect 127.0.0.1:7555", "连接adb");

    }

    /**
     * 点击
     *
     * @param task
     * @throws IOException
     * @throws InterruptedException
     */
    public void tap(String task) throws IOException, InterruptedException {
        CmdUtil cmdUtil = new CmdUtil();
        task = task.replace("tap:", "").replace(".", " ");
        cmdUtil.exec("adb shell input tap " + task, "");

    }

    /**
     * 执行Cmd
     *
     * @param task
     * @throws IOException
     * @throws InterruptedException
     */
    public void cmd(String task) throws IOException, InterruptedException {
        CmdUtil cmdUtil = new CmdUtil();
        task = task.replace("cmd:", "");
        cmdUtil.exec(task, "");
    }

    /**
     * 滑动
     *
     * @param task
     * @throws IOException
     * @throws InterruptedException
     */
    public void swipe(String task) throws IOException, InterruptedException {

    }

    /**
     * 循环
     *
     * @param task
     * @throws IOException
     * @throws InterruptedException
     */
    public void loop(String task) throws IOException, InterruptedException {
        String[] loop = task.split(":");
        int lopCount = Integer.parseInt(loop[1].split("/")[0]);
        ArrayList<String> looptasks = new ArrayList<String>(Arrays.asList(task.split("/")));
        for (int i = 0; i < lopCount; i++) {
            for (String looptask : looptasks.subList(1, looptasks.size())) {
                branch(looptask);
            }
        }

    }

    /**
     * 休眠
     *
     * @param task
     * @throws IOException
     * @throws InterruptedException
     */
    public void slp(String task) throws IOException, InterruptedException {
        CmdUtil cmdUtil = new CmdUtil();
        task = task.replace("slp:", "");
        cmdUtil.sleep(Integer.parseInt(task));
    }


    /**
     * 休眠
     *
     * @param task
     * @throws IOException
     * @throws InterruptedException
     */
    public void slp(long task) throws IOException, InterruptedException {
        CmdUtil cmdUtil = new CmdUtil();
        cmdUtil.sleep(task);
    }


    /**
     * 启动模拟器
     */
    static startMumu  mumu = new startMumu();
    Thread thread;
    public void startMumu() {
        thread = new Thread(mumu, "子线程");
        thread.start();
    }

    /**
     * 关闭模拟器
     *
     * @throws IOException
     */
    public void shutdownMumu() throws IOException, InterruptedException {
        thread.stop();
        new CmdUtil().exec("taskkill /f /im NemuPlayer.exe","");
    }

    /**
     * 启动舟游
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void startArknights() throws IOException, InterruptedException {
        CmdUtil cmdUtil = new CmdUtil();
        cmdUtil.exec("adb shell input tap 212 161", "点舟游图标");
        this.slp("10");
        cmdUtil.exec("adb shell input tap 721 757", "点启动");
        this.slp("5");
        cmdUtil.exec("adb shell input tap 460 570", "账号登录");
        this.slp("2");
        cmdUtil.exec("adb shell input tap 1034 768", "账号管理");
        this.slp("2");
        if (screenshotComparison("src\\main\\resources\\imgTemp\\denglu.png")){
            cmdUtil.exec("adb shell input tap 460 570", "点击账号登录");
            this.slp("2");
            cmdUtil.exec("adb shell input tap 677 488", "点账号框");
            this.slp("2");
            cmdUtil.exec("adb shell input text \"17629230423\"", "输入账号");
            this.slp("2");
            cmdUtil.exec("adb shell input tap 700 540", "点密码框");
            this.slp("2");
            cmdUtil.exec("adb shell input tap 700 540", "点密码框");
            this.slp("2");
            cmdUtil.exec("adb shell input text \"wx1262148122\"", "输入密码");
            this.slp("2");
            cmdUtil.exec("adb shell input tap 710 650", "登录");
            this.slp("2");
            cmdUtil.exec("adb shell input tap 710 650", "登录");
            this.slp("10");
            cmdUtil.exec("adb shell input tap 1380 40", "关公告");
            this.slp(500);
            cmdUtil.exec("adb shell input tap 1380 40", "关公告");
            this.slp(500);
            cmdUtil.exec("adb shell input tap 1380 40", "关公告");
        }
    }


    /**
     * 截图模拟器
     */
    public void screenshotMumu() throws IOException, InterruptedException {
        CmdUtil cmdUtil = new CmdUtil();
        cmdUtil.exec("adb shell screencap -p /sdcard/adbTemps.png", "截图");
        cmdUtil.exec("adb pull /sdcard/adbTemps.png D:\\workspace\\IDEAProject\\ArknightsScript\\src\\main\\resources\\imgTemp", "");
    }

    /**
     * 截图比较
     *
     * @param path
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean screenshotComparison(String path) throws IOException, InterruptedException {
        CmdUtil cmdUtil = new CmdUtil();
        cmdUtil.exec("adb shell screencap -p /sdcard/temp.png", "截图");
        cmdUtil.exec("adb pull /sdcard/temp.png D:\\workspace\\IDEAProject\\ArknightsScript\\src\\main\\resources\\imgTemp", "传输");
        BufferedImage image1 = ImageIO.read(new FileInputStream(path));
        BufferedImage image2 = ImageIO.read(new FileInputStream("src\\main\\resources\\imgTemp\\temp.png"));
        return SimilarityComparer.compareImage(image1, image2);
    }

}

class startMumu implements Runnable {

    @SneakyThrows
    @Override
    public void run() {
        CmdUtil cmdUtil = new CmdUtil();
        cmdUtil.exec("D:\\Program Files (x86)\\MuMu\\emulator\\nemu\\EmulatorShell\\NemuPlayer.exe","");
    }

}


