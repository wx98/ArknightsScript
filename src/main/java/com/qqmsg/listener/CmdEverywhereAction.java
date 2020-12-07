package com.qqmsg.listener;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;

import java.util.ArrayList;

public class CmdEverywhereAction implements EverywhereCommand {
    @Override
    public String run(EventMessage event, User sender, String command, ArrayList<String> args) {
        return null;
    }

    @Override
    public CommandProperties properties() {
        return new CommandProperties("全部信息", "all", "a", "-a", "全部信息");
    }
}
