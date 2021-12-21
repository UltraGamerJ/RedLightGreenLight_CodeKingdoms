package space.codekingdoms.ultragamerj.redlightgreenlight;

import org.bukkit.ChatColor;
import java.lang.Math;
import com.codekingdoms.nozzle.utils.Random;
import com.codekingdoms.nozzle.utils.Direction;
import java.lang.Integer;
import java.lang.Float;
import org.bukkit.GameMode;
import com.codekingdoms.nozzle.base.BaseGame;

public class Game extends BaseGame {
	
	public int gamePhase;
	
	public boolean greenLight;
	
	public void onCodeUpdate() {
		
		gamePhase = 0;
		greenLight = false;
		for (Player p : getPlayerList()) {
			
			p.setGameMode(GameMode.ADVENTURE);
			p.onJoin();
			
		}
		
		broadcastTitle("Starting Soon!", ( "Run on " + ( ( ChatColor.GREEN + "GREEN" ) + ChatColor.WHITE ) ) + ( ", stop on " + ( ChatColor.RED + "RED" ) ));
		showCountdownTimer = true;
		startTimer(30);
	
	}
	
	public void onTimerExpire() {
		
		if (gamePhase == 0) {
			
			broadcastTitle("Get ready...", "");
			gamePhase = 1;
			showCountdownTimer = false;
			startTimer(Random.generateInteger(3, 7));
			
		}
		
		else if (gamePhase == 1) {
			
			for (Player p : getPlayerList()) {
				
				p.canMove = true;
				
			}
			
			if (greenLight) {
				
				broadcastTitle(ChatColor.RED + "RED LIGHT!", "Stop moving!");
				setTimeout(
					
					() -> {
						
						greenLight = false;
						
					}
					
					
				, 2);
				startTimer(Random.generateInteger(5, 15));
				
			}
			
			else {
				
				broadcastTitle(ChatColor.GREEN + "GREEN LIGHT!", "Run as far as you can!");
				greenLight = true;
				startTimer(Random.generateInteger(5, 15));
				
			 }
			
			
		}
		
	
	}
	
	public void checkRestart() {
		
		for (Player p : getPlayerList()) {
			
			if (p.getGameMode() == GameMode.ADVENTURE) {
				
				return;
				
			}
			
			
		}
		
		broadcastMessage("All players are in spectator! Restarting...");
		resetGame();
	
	}
	
	
}
