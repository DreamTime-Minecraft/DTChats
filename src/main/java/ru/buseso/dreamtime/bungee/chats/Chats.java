package ru.buseso.dreamtime.bungee.chats;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public final class Chats extends Plugin {

    static List<ProxiedPlayer> offStaff = new ArrayList<>();
    static List<ProxiedPlayer> offDonate = new ArrayList<>();

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerCommand(this, new StaffChat("sc"));
        getProxy().getPluginManager().registerCommand(this, new DonateChat("dc"));
    }
}
