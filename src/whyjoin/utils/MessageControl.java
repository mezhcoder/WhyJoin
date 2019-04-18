package whyjoin.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageControl {

    public static String replaceColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public static void sendMessage(CommandSender commandSender, String message) { commandSender.sendMessage(replaceColor(message)); }
    public static void sendTitle(Player player, String first, String second) { player.sendTitle(replaceColor(first), replaceColor(second)); }

}
