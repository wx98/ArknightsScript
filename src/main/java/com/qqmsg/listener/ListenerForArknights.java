package com.qqmsg.listener;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventPrivateMessage;
import cc.moecraft.icq.user.User;
import com.util.AdbActionUtil;
import com.util.ScreenSave;

import java.io.File;
import java.io.IOException;

public class ListenerForArknights extends IcqListener {
    AdbActionUtil adbActionUtil = AdbActionUtil.getSingleton();

    String startArknights = "启动舟游";
    String startMumu = "启动模拟器";
    String shutdownMumu = "关闭模拟器";
    String adbCon = "连接adb";
    String screenshotMuMu = "截屏adb";
    String screenshot = "截屏";
    String dailyTasks = "日常任务";
    String task = "开始";

    /**
     * 自动任务的主流程
     *
     * @param event
     * @return
     */
    @EventHandler
    public boolean autoStart(EventPrivateMessage event) {

        return true;
    }


    @EventHandler
    public boolean task(EventPrivateMessage event) {
        if (event.getSender().getId() != 1262148122L) {
            return false;
        }
        if (!task.equals(event.getMessage().split(" ")[0])) {
            try {
                for (int i = 0; i < Integer.parseInt(event.getMessage().split(" ")[1])/6; i++) {
                    adbActionUtil.taskFlow("src\\main\\resources\\Script\\1-7.txt");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * 日常
     *
     * @param event
     * @return
     */
    @EventHandler
    public boolean dailyTasks(EventPrivateMessage event) {
        if (event.getSender().getId() != 1262148122L) {
            return false;
        }
        if (!dailyTasks.equals(event.getMessage())) {
            try {
                adbActionUtil.taskFlow("src\\main\\resources\\Script\\日常任务.txt");
                adbActionUtil.taskFlow("src\\main\\resources\\Script\\基建.txt");
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * 连接Adb
     */
    @EventHandler
    public boolean adbCon(EventPrivateMessage event) {
        if (event.getSender().getId() != 1262148122L) {
            return false;
        }
        if (!adbCon.equals(event.getMessage())) {
            return false;
        }
        try {
            adbActionUtil.con();
        } catch (IOException e) {
            e.printStackTrace();
            event.respond(e.toString());
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            event.respond(e.toString());
            return false;
        }
        event.respond("成功");
        return true;
    }

    /**
     * adb 截屏发送
     *
     * @param event
     * @return
     */
    @EventHandler
    public boolean screenshotMuMu(EventPrivateMessage event) {
        if (event.getSender().getId() != 1262148122L) {
            return false;
        }
        if (!screenshotMuMu.equals(event.getMessage())) {
            return false;
        }
        try {
            new File("CQP\\data\\image\\success.png").delete();
            adbActionUtil.screenshotMumu();
        } catch (IOException e) {
            e.printStackTrace();
            event.respond(e.toString());
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            event.respond(e.toString());
            return false;
        }
        event.respond("[CQ:image,file=adbTemps.png]");
        return true;

    }

    /**
     * adb 截屏发送
     *
     * @param event
     * @return
     */
    @EventHandler
    public boolean screenshot(EventPrivateMessage event) {
        if (event.getSender().getId() != 1262148122L) {
            return false;
        }
        if (!screenshot.equals(event.getMessage())) {
            return false;
        }

        new File("CQP\\data\\image\\success.png").delete();
        ScreenSave.Screen("CQP\\data\\image\\success.png");
        event.respond("[CQ:image,file=adbTemps.png]");
        return true;
    }

    /**
     * adb 启动舟游截屏发送
     *
     * @param event
     * @return
     */
    @EventHandler
    public boolean startArknights(EventPrivateMessage event) {
        if (event.getSender().getId() != 1262148122L) {
            return false;
        }
        if (!startArknights.equals(event.getMessage())) {
            return false;
        }
        try {
            adbActionUtil.startArknights();
            adbActionUtil.screenshotMumu();
        } catch (IOException e) {
            e.printStackTrace();
            event.respond(e.toString());
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            event.respond(e.toString());
            return false;
        }
        event.respond("[CQ:image,file=adbTemps.png]");
        return true;
    }

    /**
     * adb 启动模拟器
     *
     * @param event
     * @return
     */
    @EventHandler
    public boolean startMumu(EventPrivateMessage event) throws IOException, InterruptedException {
        if (event.getSender().getId() != 1262148122L) {
            return false;
        }
        if (!startMumu.equals(event.getMessage())) {
            return false;
        }
        adbActionUtil.startMumu();
        adbActionUtil.con();
        event.respond("已启动");
        return true;
    }

    /**
     * adb 关闭模拟器
     *
     * @param event
     * @return
     */
    @EventHandler
    public boolean shutdownMumu(EventPrivateMessage event) throws IOException, InterruptedException {
        if (event.getSender().getId() != 1262148122L) {
            return false;
        }
        if (!shutdownMumu.equals(event.getMessage())) {
            return false;
        }
        adbActionUtil.shutdownMumu();
        event.respond("已关闭");
        return true;
    }
}
