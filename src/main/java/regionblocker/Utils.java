package regionblocker;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Utils {

	public static String prefix = ChatColor.GOLD + "[R] " + ChatColor.AQUA;
	public static String prefixe = ChatColor.GOLD + "[R] " + ChatColor.DARK_RED;
	
	public static void sendHelp(CommandSender sender) {
		sender.sendMessage(prefix + "Available commands:");
		sender.sendMessage(prefix + "/loginblock <add> <region> - Add region to system.");
		sender.sendMessage(prefix + "/loginblock <setspawn> <region> - Set the login spawn for a region.");
		sender.sendMessage(prefix + "/regionclear <region> - Clear the region from players.");
		sender.sendMessage(prefix + "/regionclear <setspawn> <region> - Set the spawn point for region.");
		sender.sendMessage(prefix + "/regionwarpout <setspawn> <region> - Set the warpout spawn for a region.");
		sender.sendMessage(prefix + "If a region is added to system, but has no spawnpoint, players will be teleported to world spawn!");
	}

}
