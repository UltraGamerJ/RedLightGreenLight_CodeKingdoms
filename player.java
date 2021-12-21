package space.codekingdoms.ultragamerj.redlightgreenlight;

import com.codekingdoms.nozzle.utils.Random;
import com.codekingdoms.nozzle.utils.Direction;
import java.lang.Math;
import java.lang.Integer;
import java.lang.Float;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.GameMode;
import com.codekingdoms.nozzle.base.BasePlayer;

public class Player extends BasePlayer {
	
	public void onJoin() {
		
		if (getGame().gamePhase == 0) {
			
			teleport(new Location(world, Random.generateInteger(-44, -75), 31, 127));
			setGameMode(GameMode.ADVENTURE);
			canMove = false;
			
		}
		
		else {
			
			setGameMode(GameMode.SPECTATOR);
			
		 }
		
	
	}
	
	public void onMove( Direction direction ) {
		
		if (getLocation().getZ() >= 472) {
			
			sendTitle("Congratulations!", "You have completed the course!");
			getGame().broadcastMessage(name + " completed the course!");
			setGameMode(GameMode.SPECTATOR);
			getGame().checkRestart();
			
		}
		
		if (( ! getGame().greenLight ) && ( getGame().gamePhase == 1 )) {
			
			setGameMode(GameMode.SPECTATOR);
			getGame().broadcastMessage(name + " moved during red light and has failed!");
			sendTitle("You failed!", "No moving during Red Light!");
			getGame().checkRestart();
			
		}
		
	
	}
	
	
}
