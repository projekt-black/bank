package de.projekt_black.bank.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import de.projekt_black.bank.bank;
import de.projekt_black.bank.main;
import de.projekt_black.bank.util.msg;

public class signchange implements Listener{

	main main;
	bank b;
	public signchange(main main, bank b){
		this.main = main;
		this.b = b;
	}
	
	
	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		 if(!event.getLine(0).equalsIgnoreCase("[bank]")){
	        	return;
	        }
		 
	        Player p = (Player)event.getPlayer();
	        if(!p.hasPermission("bank.sign.createsign")){
				p.sendMessage(msg.prefixred + "Du hast keine Rechte dieses Schild zu erstellen.");
				event.setLine(0, ChatColor.RED + "[bank]");
				event.setLine(1, "");
				event.setLine(2, "");
				event.setLine(3, "");
				return;
			}
	        event.setLine(0, ChatColor.AQUA + "[Bank]");	        
		
	}
	
}
