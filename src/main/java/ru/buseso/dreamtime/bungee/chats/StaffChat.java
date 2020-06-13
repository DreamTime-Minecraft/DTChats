package ru.buseso.dreamtime.bungee.chats;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class StaffChat extends Command {
    public StaffChat(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("dreamtime.staff.chat")) {
            sender.sendMessage(TextComponent.fromLegacyText("§cУ Вас недостаточно прав для данной команды!"));
        } else {
            if(!(sender instanceof ProxiedPlayer)) {
                sender.sendMessage(TextComponent.fromLegacyText("§cВы должны быть игроком!"));
            } else {
                ProxiedPlayer p = (ProxiedPlayer)sender;
                if (args.length == 0) {
                    sender.sendMessage(TextComponent.fromLegacyText("§cУкажите сообщение!"));
                } else {
                    if (args[0].equalsIgnoreCase("toggle")) {
                        if (Chats.offStaff.contains(p)) {
                            Chats.offStaff.remove(p);
                            sender.sendMessage(TextComponent.fromLegacyText("§aВы вновь видите все сообщения из Staff-чата!"));
                        } else {
                            Chats.offStaff.add(p);
                            sender.sendMessage(TextComponent.fromLegacyText("§cВы скрыли сообщения из Staff-чата!"));
                        }
                    } else {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        String msg = sb.toString().replaceAll("&k", "");

                        if(p.hasPermission("dreamtime.chats.colors")) {
                            msg = msg.replaceAll("&","§");
                        }

                        String format = "§8[§9SC§8] §7%prefix% §7%nick% §e→ §7%msg%";
                        format = format.replaceAll("%msg%", msg).replaceAll("%nick%", p.getName());
                        if (p.hasPermission("group.owner")) {
                            format = format.replaceAll("%prefix%", "§4§lＯＷＮＥＲ§7");
                        } else if (p.hasPermission("group.admin")) {
                            format = format.replaceAll("%prefix%", "§c§lＡＤＭＩＮ§7");
                        } else if (p.hasPermission("group.dev")) {
                            format = format.replaceAll("%prefix%", "§9§lＤＥＶ§7");
                        } else if (p.hasPermission("group.srmoder")) {
                            format = format.replaceAll("%prefix%", "§5§lＳＲ.ＭＯＤＥＲ§7");
                        } else if (p.hasPermission("group.moder")) {
                            format = format.replaceAll("%prefix%", "§5§lＭＯＤＥＲ§7");
                        } else if (p.hasPermission("group.helper")) {
                            format = format.replaceAll("%prefix%", "§9§lＨＥＬＰＥＲ§7");
                        } else if (p.hasPermission("group.builder")) {
                            format = format.replaceAll("%prefix%", "§2§lＢＵＩＬＤＥＲ§7");
                        } else if (p.hasPermission("group.tester")) {
                            format = format.replaceAll("%prefix%", "§6ＴＥＳＴＥＲ§7");
                        } else {
                            format = format.replaceAll("%prefix%", "§6Чубака");
                        }

                        for (ProxiedPlayer pp : ProxyServer.getInstance().getPlayers()) {
                            if (pp.hasPermission("dreamtime.staff.chat")) {
                                if (!Chats.offStaff.contains(pp)) {
                                    pp.sendMessage(TextComponent.fromLegacyText(format));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
