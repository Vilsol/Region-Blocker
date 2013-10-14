package regionblocker;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class MyRegion {

	public World wld;
	public ProtectedRegion reg;
	
	public MyRegion(String region, World wld){
		this.wld = wld;
		reg = WGBukkit.getRegionManager(wld).getRegion(region);
	}

	public MyRegion(ProtectedRegion reg, World wld){
		this.reg = reg;
		this.wld = wld;
	}

	public void clearOut() {
		for(Player p : wld.getPlayers()){
			if(reg.contains(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ())){
				if(hasClearSpawn()){
					p.teleport(getClearSpawn());
				}else{
					p.teleport(wld.getSpawnLocation());
				}
			}
		}
	}

	public void setClearSpawn(Location loc) {
		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".ClearSpawn.X", loc.getX());
		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".ClearSpawn.Y", loc.getY());
		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".ClearSpawn.Z", loc.getZ());

		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".ClearSpawn.Pitch", loc.getPitch());
		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".ClearSpawn.Yaw", loc.getYaw());
		RegionBlocker.plugin.saveConfig();
	}
	
	public boolean hasClearSpawn(){
		return RegionBlocker.plugin.getConfig().isSet("Worlds." + wld.getName() + "." + reg.getId() + ".ClearSpawn");
	}
	
	public Location getClearSpawn(){
		if(hasClearSpawn()){
			FileConfiguration c = RegionBlocker.plugin.getConfig();
			Location loc = new Location(wld, c.getDouble("Worlds." + wld.getName() + "." + reg.getId() + ".ClearSpawn.X"), c.getDouble("Worlds." + wld.getName() + "." + reg.getId() + ".ClearSpawn.Y"), c.getDouble("Worlds." + wld.getName() + "." + reg.getId() + ".ClearSpawn.Z"));
			loc.setPitch((float) c.getDouble("Worlds." + wld.getName() + "." + reg.getId() + ".WarpSpawn.Pitch"));
			loc.setYaw((float) c.getDouble("Worlds." + wld.getName() + "." + reg.getId() + ".WarpSpawn.Yaw"));
			return loc;
		}
		return null;
	}

	public void setLogSpawn(Location loc) {
		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".LogSpawn.X", loc.getX());
		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".LogSpawn.Y", loc.getY());
		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".LogSpawn.Z", loc.getZ());

		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".LogSpawn.Pitch", loc.getPitch());
		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".LogSpawn.Yaw", loc.getYaw());
		RegionBlocker.plugin.saveConfig();
	}

	public void addToLog(Location loc) {
		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".Log", true);
		RegionBlocker.plugin.saveConfig();
	}

	public boolean isLog(){
		return RegionBlocker.plugin.getConfig().isSet("Worlds." + wld.getName() + "." + reg.getId() + ".Log");
	}
	
	public boolean hasLogSpawn(){
		return RegionBlocker.plugin.getConfig().isSet("Worlds." + wld.getName() + "." + reg.getId() + ".LogSpawn");
	}
	
	public Location getLogSpawn(){
		if(hasLogSpawn()){
			FileConfiguration c = RegionBlocker.plugin.getConfig();
			Location loc = new Location(wld, c.getDouble("Worlds." + wld.getName() + "." + reg.getId() + ".LogSpawn.X"), c.getDouble("Worlds." + wld.getName() + "." + reg.getId() + ".LogSpawn.Y"), c.getDouble("Worlds." + wld.getName() + "." + reg.getId() + ".LogSpawn.Z"));
			loc.setPitch((float) c.getDouble("Worlds." + wld.getName() + "." + reg.getId() + ".WarpSpawn.Pitch"));
			loc.setYaw((float) c.getDouble("Worlds." + wld.getName() + "." + reg.getId() + ".WarpSpawn.Yaw"));
			return loc;
		}
		return null;
	}
	
	public void checkLog(Player plr){
		if(isLog()){
			if(hasLogSpawn()){
				plr.teleport(getLogSpawn());
			}else{
				plr.teleport(wld.getSpawnLocation());
			}
		}
	}

	public void setWarpSpawn(Location loc) {
		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".WarpSpawn.X", loc.getX());
		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".WarpSpawn.Y", loc.getY());
		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".WarpSpawn.Z", loc.getZ());

		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".WarpSpawn.Pitch", loc.getPitch());
		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".WarpSpawn.Yaw", loc.getYaw());
		RegionBlocker.plugin.saveConfig();
	}

	public void addToWarp(Location loc) {
		RegionBlocker.plugin.getConfig().set("Worlds." + wld.getName() + "." + reg.getId() + ".Warp", true);
		RegionBlocker.plugin.saveConfig();
	}

	public boolean isWarp(){
		return RegionBlocker.plugin.getConfig().isSet("Worlds." + wld.getName() + "." + reg.getId() + ".Warp");
	}
	
	public boolean hasWarpSpawn(){
		return RegionBlocker.plugin.getConfig().isSet("Worlds." + wld.getName() + "." + reg.getId() + ".WarpSpawn");
	}
	
	public Location getWarpSpawn(){
		if(hasWarpSpawn()){
			FileConfiguration c = RegionBlocker.plugin.getConfig();
			Location loc = new Location(wld, c.getDouble("Worlds." + wld.getName() + "." + reg.getId() + ".WarpSpawn.X"), c.getDouble("Worlds." + wld.getName() + "." + reg.getId() + ".WarpSpawn.Y"), c.getDouble("Worlds." + wld.getName() + "." + reg.getId() + ".WarpSpawn.Z"));
			loc.setPitch((float) c.getDouble("Worlds." + wld.getName() + "." + reg.getId() + ".WarpSpawn.Pitch"));
			loc.setYaw((float) c.getDouble("Worlds." + wld.getName() + "." + reg.getId() + ".WarpSpawn.Yaw"));
			return loc;
		}
		return null;
	}
	
	public void checkWarp(Player plr) {
		if(isWarp()){
			if(hasWarpSpawn()){
				plr.teleport(getWarpSpawn());
			}else{
				plr.teleport(plr.getWorld().getSpawnLocation());
			}
		}
	}
	
}
