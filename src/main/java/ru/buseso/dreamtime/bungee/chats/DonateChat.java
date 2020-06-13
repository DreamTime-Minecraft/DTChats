package ru.buseso.dreamtime.bungee.chats;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class DonateChat extends Command {
    public DonateChat(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("dreamtime.rank.chat")) {
            sender.sendMessage(TextComponent.fromLegacyText("§cУ Вас недостаточно прав для данной команды!"));
        } else {
            if(!(sender instanceof ProxiedPlayer)) {
                sender.sendMessage(TextComponent.fromLegacyText("§cВы должны быть игроком!"));
            } else {
                ProxiedPlayer p = (ProxiedPlayer)sender;
                if(args.length == 0) {
                    sender.sendMessage(TextComponent.fromLegacyText("§cУкажите сообщение!"));
                } else {
                    if (args[0].equalsIgnoreCase("toggle")) {
                        if (Chats.offDonate.contains(p)) {
                            Chats.offDonate.remove(p);
                            sender.sendMessage(TextComponent.fromLegacyText("§aВы вновь видите все сообщения из Донат-чата!"));
                        } else {
                            Chats.offDonate.add(p);
                            sender.sendMessage(TextComponent.fromLegacyText("§cВы скрыли сообщения из Донат-чата!"));
                        }
                    } else {
                        if(Chats.offDonate.contains(p)) {
                            sender.sendMessage(TextComponent.fromLegacyText("§cУ Вас выключены сообщения! Включите их через /dc toggle"));
                            return;
                        }
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        String msg = sb.toString().replaceAll("&k", "");

                        if(p.hasPermission("dreamtime.chats.colors")) {
                            msg = msg.replaceAll("&","§");
                        }

                        String format = "§8[§6DC§8] §7%prefix% §7%nick% §e→ §7%msg%";
                        format = format.replaceAll("%msg%", msg).replaceAll("%nick%", p.getName());
                        if (p.hasPermission("group.owner")) {
                            format = format.replaceAll("%prefix%", "§4§lＯＷＮＥＲ§7");
                        } else if (p.hasPermission("group.admin")) {
                            format = format.replaceAll("%prefix%", "§c§lＡＤＭＩＮ§7");
                        } else if (p.hasPermission("group.godlike")) {
                            format = format.replaceAll("%prefix%", "§e§lＧＯＤ§6§lＬＩＫＥ§7");
                        } else if (p.hasPermission("group.titan")) {
                            format = format.replaceAll("%prefix%", "§c§lＴＩＴＡＮ§7");
                        } else if (p.hasPermission("group.mvpp")) {
                            format = format.replaceAll("%prefix%", "§d§lＭＶＰ§e§l+§7");
                        } else if (p.hasPermission("group.yt")) {
                            format = format.replaceAll("%prefix%", "§c§lＹＯＵ§f§lＴＵＢＥ§7");
                        } else if (p.hasPermission("group.ultra")) {
                            format = format.replaceAll("%prefix%", "§b§lＵＬＴＲＡ§7");
                        } else if (p.hasPermission("group.mvp")) {
                            format = format.replaceAll("%prefix%", "§d§lＭＶＰ§7");
                        } else if (p.hasPermission("group.gold")) {
                            format = format.replaceAll("%prefix%", "§6§lＧＯＬＤ§7");
                        } else if (p.hasPermission("group.vipp")) {
                            format = format.replaceAll("%prefix%", "§a§lＶＩＰ§e§l+§7");
                        } else if (p.hasPermission("group.iron")) {
                            format = format.replaceAll("%prefix%", "§f§lＩＲＯＮ§7");
                        } else {
                            format = format.replaceAll("%prefix%", "§6Чубака");
                        }

                        for (ProxiedPlayer pp : ProxyServer.getInstance().getPlayers()) {
                            if (pp.hasPermission("dreamtime.rank.chat")) {
                                if (!Chats.offDonate.contains(pp)) {
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
