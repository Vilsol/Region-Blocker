package regionblocker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class RegionBlocker extends JavaPlugin {

	public static RegionBlocker plugin;
	
	public void onEnable(){
		plugin = this;
	}
	
	public boolean onCommand(final CommandSender sender, Command cmd, String label, final String[] args) {
		if(cmd.getLabel().equalsIgnoreCase("loginblock")){
			if(args.length > 0){
				
			}else{
				Utils.sendHelp(sender);
			}
		}else if(cmd.getLabel().equalsIgnoreCase("regionclear")){
			
		}else if(cmd.getLabel().equalsIgnoreCase("regionwarpout")){
			
		}else{
			Utils.sendHelp(sender);
		}
		return true;
	}
	
}
