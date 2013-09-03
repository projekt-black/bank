package de.projekt_black.bank;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.projekt_black.bank.command.bankcommand;
import de.projekt_black.bank.listener.signchange;
import de.projekt_black.bank.listener.signclick;

public class main extends JavaPlugin{
	
	bank b;
	FileConfiguration fcg;
	
	@Override
	public void onEnable(){
		fcg = this.getConfig();
		loadConfig();
		b = new bank(fcg,this);
		this.getServer().getPluginManager().registerEvents(new signclick(this, b), this);
		this.getServer().getPluginManager().registerEvents(new signchange(this, b), this);
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
	
	public void loadConfig(){
		fcg.addDefault("config.maxmoneyforinterest", 40000.0);
		fcg.addDefault("config.onlineinterest", 0.5);
		fcg.addDefault("config.offlineinterest", 0.5);
		List<String> hidetop = new ArrayList<String>();
		hidetop.add("admin1");
		hidetop.add("admin2");
		fcg.addDefault("config.hidetop", hidetop);
		List<String> times = new ArrayList<String>();
		times.add("0600");
		times.add("1630");
		fcg.addDefault("config.interesttimes", times);
		fcg.options().copyDefaults(true);
		this.saveConfig();
	}
	
	public FileConfiguration getFcg(){
		return fcg;
	}
		
		

}
