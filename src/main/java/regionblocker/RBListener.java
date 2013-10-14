package regionblocker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class RBListener implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent evt){
		ProtectedRegion reg = Utils.isPlayerInRegion(evt.getPlayer());
		if(reg != null){
			MyRegion my = new MyRegion(reg, evt.getPlayer().getWorld());
			my.checkWarp(evt.getPlayer());
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent evt){
		ProtectedRegion reg = Utils.isPlayerInRegion(evt.getPlayer());
		if(reg != null){
			MyRegion my = new MyRegion(reg, evt.getPlayer().getWorld());
			my.checkLog(evt.getPlayer());
		}
	}
	
}
