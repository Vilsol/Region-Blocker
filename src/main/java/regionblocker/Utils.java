package regionblocker;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class Utils {

	public static String prefix = ChatColor.GOLD + "[R] " + ChatColor.AQUA;
	public static String prefixe = ChatColor.GOLD + "[R] " + ChatColor.DARK_RED;
	
	public static void sendHelp(CommandSender sender) {
		sender.sendMessage(prefix + "Available commands:");
		sender.sendMessage(prefix + "/lb <add> <region> - Add region to system.");
		sender.sendMessage(prefix + "/lb <setspawn> <region> - Set the login spawn.");
		sender.sendMessage(prefix + "/rc <region> - Clear the region from players.");
		sender.sendMessage(prefix + "/rc <setspawn> <region> - Set the spawn point.");
		sender.sendMessage(prefix + "/rwo <add> <region> - Add region to system.");
		sender.sendMessage(prefix + "/rwo <setspawn> <region> - Set the warpout spawn.");
		sender.sendMessage(prefix + "If a region is added to system, but has no spawnpoint, players will be teleported to world spawn!");
	}

	public static boolean doesRegionExist(String region, Player plr) {
		ProtectedRegion reg = WGBukkit.getRegionManager(plr.getWorld()).getRegion(region);
		return (reg != null);
	}

	public static ProtectedRegion isPlayerInRegion(Player plr) {
		if(RegionBlocker.plugin.getConfig().isSet("Worlds." + plr.getWorld().getName())){
			Set<String> regions = RegionBlocker.plugin.getConfig().getConfigurationSection("Worlds." + plr.getWorld().getName() + ".").getKeys(false);
			for(String s : regions){
				ProtectedRegion reg = WGBukkit.getRegionManager(plr.getWorld()).getRegion(s);
				if(reg.contains(plr.getLocation().getBlockX(), plr.getLocation().getBlockY(), plr.getLocation().getBlockZ())){
					return reg;
				}
			}
		}
		return null;
	}

}
