package com.qqmsg;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import cc.moecraft.icq.command.interfaces.IcqCommand;
import cc.moecraft.icq.event.IcqListener;
import com.qqmsg.listener.*;
import com.util.AdbActionUtil;


public class QQMonitor {
    int socketPort = 40001;
    int postPort = 40000;
    String postHost = "127.0.0.1";
    PicqBotX bot = null;

    private static IcqCommand[] commands;
    private static IcqListener[] listeners;

    private QQMonitor() {
    }

    private volatile static QQMonitor qqMonitor;

    public static QQMonitor getSingleton() {
        if (qqMonitor == null) {
            synchronized (AdbActionUtil.class) {
                if (qqMonitor == null) {
                    qqMonitor = new QQMonitor();
                }
            }
        }
        return qqMonitor;
    }


    public static void main(String[] args) {
        QQMonitor.getSingleton().core();
    }

    public boolean run() {
        try {
            bot.startBot(); // 启动机器人, 不会占用主线程
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public QQMonitor core() {
        initListener();
        bot = new PicqBotX(new PicqConfig(socketPort).setDebug(true));

        bot.addAccount("test1", postHost, postPort);
        bot.setUniversalHyExpSupport(true);// 启用HyExp ( 非必要 )
        bot.getConfig().setUseAsyncCommands(true);// 设置异步

        bot.enableCommandManager("-", "!", "/", "~", "！", ".", "，", ",");// 启用指令管理器
        bot.getEventManager().registerListeners(listeners);// 注册事件监听器, 可以注册多个监听器
        bot.getCommandManager().registerCommands(commands);// 注册指令

        bot.getLogger().debug(bot.getCommandManager().getCommands().toString()); // Debug输出所有已注册的指令
        if (!bot.getConfig().isDebug()) {
            bot.getEventManager().registerListener(new SimpleTextLoggingListener());// 在没有Debug的时候加上这个消息日志监听器
        }
        return this;
    }

    private void initListener() {
        //要注册的指令
        commands = new IcqCommand[]{
                new CmdEverywhereAction(),
                new CmdGroupAction()
        };
        //要注册的监听器
        listeners = new IcqListener[]{
                new ListenerForPrivate(),
                new ListenerForGroup(),
//                new ListenerForArknights(),
                new 小沐模式()
        };
    }

    public int getSocketPort() {
        return socketPort;
    }

    public QQMonitor setSocketPort(int socketPort) {
        this.socketPort = socketPort;
        return this;
    }

    public int getPostPort() {
        return postPort;
    }

    public QQMonitor setPostPort(int postPort) {
        this.postPort = postPort;
        return this;
    }

    public String getPostHost() {
        return postHost;
    }

    public QQMonitor setPostHost(String postHost) {
        this.postHost = postHost;
        return this;
    }


}
