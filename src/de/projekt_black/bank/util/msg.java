package de.projekt_black.bank.util;

import org.bukkit.ChatColor;

public class msg {
	private static String prefix = ChatColor.GREEN + "[bank] ";
	public static String NO_PERMISSIONS = prefix + ChatColor.RED + "Du hast keine Rechte";
	public static String NO_CONSOLE = prefix + ChatColor.RED + "Dieser Befehel geht nur ingame.";
	public static String FALSE_SYNTAX = prefix + ChatColor.RED + "Du hast den Command falsch eingegeben.";
	
	
	public static String money(double money, String owner){
		return prefix + owner + "'s Geld: " + money;
	}

}