package regionblocker;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class RegionBlocker extends JavaPlugin {

	public static RegionBlocker plugin;
	public static Plugin WorldGuard;
	
	public void onEnable(){
		plugin = this;
		loadWorldGuard();
		getServer().getPluginManager().registerEvents(new RBListener(), this); 
		loadConfig();
	}

	private void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	private void loadWorldGuard() {
		WorldGuard = getServer().getPluginManager().getPlugin("WorldGuard");
	    if (WorldGuard == null || !(WorldGuard instanceof WorldGuardPlugin)){
	    	getLogger().warning("WorldGuard not found!");
	    }
	}
	
	public boolean onCommand(final CommandSender sender, Command cmd, String label, final String[] args) {
		if(sender instanceof Player){
			Player plr = (Player) sender;
			if(cmd.getLabel().equalsIgnoreCase("loginblock")){
				if(args.length > 1){
					if(args[0].equalsIgnoreCase("setspawn")){
						if(Utils.doesRegionExist(args[1], plr)){
							MyRegion region = new MyRegion(args[1], plr.getWorld());
							region.setLogSpawn(plr.getLocation());
							sender.sendMessage(Utils.prefix + "Login position set!");
						}else{
							sender.sendMessage(Utils.prefixe + "Region " + ChatColor.RED + args[0] + ChatColor.DARK_RED + " does not exist in this world!");
						}
					}else if(args[0].equals("add")){
						if(Utils.doesRegionExist(args[1], plr)){
							MyRegion region = new MyRegion(args[1], plr.getWorld());
							region.addToLog(plr.getLocation());
							sender.sendMessage(Utils.prefix + "Region added to login system!");
						}else{
							sender.sendMessage(Utils.prefixe + "Region " + ChatColor.RED + args[0] + ChatColor.DARK_RED + " does not exist in this world!");
						}
					}else{
						Utils.sendHelp(sender);
					}
				}else{
					Utils.sendHelp(sender);
				}
			}else if(cmd.getLabel().equalsIgnoreCase("regionclear")){
				if(args.length > 0){
					if(args[0].equalsIgnoreCase("setspawn")){
						if(Utils.doesRegionExist(args[1], plr)){
							MyRegion region = new MyRegion(args[1], plr.getWorld());
							region.setClearSpawn(plr.getLocation());
							sender.sendMessage(Utils.prefix + "Clear position set!");
						}else{
							sender.sendMessage(Utils.prefixe + "Region " + ChatColor.RED + args[0] + ChatColor.DARK_RED + " does not exist in this world!");
						}
					}else{
						if(Utils.doesRegionExist(args[0], plr)){
							MyRegion region = new MyRegion(args[0], plr.getWorld());
							region.clearOut();
							sender.sendMessage(Utils.prefix + "Region cleared!");
						}else{
							sender.sendMessage(Utils.prefixe + "Region " + ChatColor.RED + args[0] + ChatColor.DARK_RED + " does not exist in this world!");
						}
					}
				}else{
					Utils.sendHelp(sender);
				}
			}else if(cmd.getLabel().equalsIgnoreCase("regionwarpout")){
				if(args.length > 0){
					if(args[0].equalsIgnoreCase("setspawn")){
						if(Utils.doesRegionExist(args[1], plr)){
							MyRegion region = new MyRegion(args[1], plr.getWorld());
							region.setWarpSpawn(plr.getLocation());
							sender.sendMessage(Utils.prefix + "Warpout position set!");
						}else{
							sender.sendMessage(Utils.prefixe + "Region " + ChatColor.RED + args[0] + ChatColor.DARK_RED + " does not exist in this world!");
						}
					}else if(args[0].equalsIgnoreCase("add")){
						if(Utils.doesRegionExist(args[1], plr)){
							MyRegion region = new MyRegion(args[1], plr.getWorld());
							region.addToWarp(plr.getLocation());
							sender.sendMessage(Utils.prefix + "Region added to warp system!");
						}else{
							sender.sendMessage(Utils.prefixe + "Region " + ChatColor.RED + args[0] + ChatColor.DARK_RED + " does not exist in this world!");
						}
					}else{
						Utils.sendHelp(sender);
					}
				}else{
					Utils.sendHelp(sender);
				}
			}else{
				Utils.sendHelp(sender);
			}
		}else{
			sender.sendMessage(Utils.prefixe + "You must be a player to use this plugin!");
		}
		return true;
	}
	
}
