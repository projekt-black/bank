package de.projekt_black.bank.command;

import org.bukkit.command.CommandSender;

import de.projekt_black.bank.bank;
import de.projekt_black.bank.main;
import de.projekt_black.bank.util.msg;
import de.projekt_black.bank.util.util;

public class bankcommand {

	private main main;
	private CommandSender sender;
	private String[] args;
	private bank b;
	
	public bankcommand(main main, CommandSender sender, String[] args, bank b){
		this.main = main;
		this.sender = sender;
		this.args = args;
		this.b = b;
	}

	public void execute(){
		if(args.length < 1){
			sender.sendMessage(msg.FALSE_SYNTAX);
			return;
		}
		
		if(args[0].equalsIgnoreCase("money")){
			args = util.changeArgs(args);
			new moneycommand(main,sender,args,b).execute();
			return;
		}
		
		if(args[0].equalsIgnoreCase("top")){
			args = util.changeArgs(args);
			new topcommand(main,sender,args,b).execute();
			return;
		}
		
		
		sender.sendMessage(msg.FALSE_SYNTAX);
		
	}
}