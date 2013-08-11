package de.projekt_black.bank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import de.projekt_black.bank.command.bankcommand;

public class main extends JavaPlugin{
	
	bank b;
	
	@Override
	public void onEnable(){
		b = new bank();
		System.out.println("[Bank] Das Plugin wurde geladen.");
	}
	
	@Override
	public void onDisable(){
		System.out.println("[Bank] Das Plugin wurde deaktiviert.");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("bank")){
			new bankcommand(this, sender, args,b).execute();
			return true;
			}
		
		return false;
	}
		
		

}
