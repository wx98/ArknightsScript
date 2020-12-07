package com.qqmsg.listener;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.GroupCommand;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.user.Group;
import cc.moecraft.icq.user.GroupUser;

import java.util.ArrayList;

public class CmdGroupAction implements GroupCommand {
    @Override
    public String groupMessage(EventGroupMessage event, GroupUser sender, Group group, String command, ArrayList<String> args) {
        return null;
    }

    @Override
    public CommandProperties properties() {
       return new CommandProperties("全部信息", "all", "a", "-a", "全部信息");
    }
}
