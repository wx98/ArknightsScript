package com.qqmsg.listener;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.event.events.message.EventPrivateMessage;
import cc.moecraft.icq.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class 小沐模式 extends IcqListener {
    long lasttime = 00000000000000;

    @EventHandler
    public void 小沐迷妹(EventGroupMessage event) {

        event.getBot().getAccountManager().refreshCache();
        User sender = event.getSender();
        System.out.println(lasttime);
        if (sender.getId() == 2216635278L || sender.getId() == 1262148122L) {
            if ((new Date().getTime() - lasttime) > (1000 * 60 * 5)) {
                ArrayList<String> img = new ArrayList<>();
                img.add("[CQ:image,file=A7D16AE6B3427611E4FEFD78572B7770.jpg,url=https://gchat.qpic.cn/gchatpic_new/1262148122/4139295238-2531505768-A7D16AE6B3427611E4FEFD78572B7770/0?term=2]");
                img.add("[CQ:image,file=BDD6F9A2A797CC86B6F5379EE549CB34.jpg,url=https://gchat.qpic.cn/gchatpic_new/1262148122/4139295238-2546648821-BDD6F9A2A797CC86B6F5379EE549CB34/0?term=2]");
                String ims = img.get(new Random().nextInt(img.size()));
                event.respond(ims);
                System.out.println("send " + ims);
                lasttime = new Date().getTime();
            }
        }
    }

    @EventHandler
    public void 小沐迷妹(EventPrivateMessage event) {
        User sender = event.getSender();
        if (sender.getId() == 2216635278L || sender.getId() == 1262148122L) {
            if ("失忆".equals(event.getMessage())) {
                lasttime = 00000000000000;
                System.out.println("失忆 " + lasttime);
                event.respond("嗯嗯，我们最近没见过(^_−)☆");
            }
        }

    }
}
