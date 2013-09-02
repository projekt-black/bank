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
			sender.sendMessage(msg.prefixred + "Du hast den Command falsch eingegeben.");
			return;
		}
		
		if(args[0].equalsIgnoreCase("money")){
			if(!sender.hasPermission("bank.command.money")){
				sender.sendMessage(msg.prefixred + "Du hast keine Rechte diesen Command auszufŸhren.");
				return;
			}
			args = util.changeArgs(args);
			new moneycommand(sender,args,b).execute();
			return;
		}
		
		if(args[0].equalsIgnoreCase("top")){
			if(!sender.hasPermission("bank.command.top")){
				sender.sendMessage(msg.prefixred + "Du hast keine Rechte diesen Command auszufŸhren.");
				return;
			}
			args = util.changeArgs(args);
			new topcommand(sender,args,b).execute();
			return;
		}
		
		if(args[0].equalsIgnoreCase("set")){
			if(!sender.hasPermission("bank.command.set")){
				sender.sendMessage(msg.prefixred + "Du hast keine Rechte diesen Command auszufŸhren.");
				return;
			}
			args = util.changeArgs(args);
			new setcommand(sender,args,b).execute();
			return;
		}
		
		if(args[0].equalsIgnoreCase("give")){
			if(!sender.hasPermission("bank.command.give")){
				sender.sendMessage(msg.prefixred + "Du hast keine Rechte diesen Command auszufŸhren.");
				return;
			}
			args = util.changeArgs(args);
			new givecommand(sender,args,b).execute();
			return;
		}
		
		if(args[0].equalsIgnoreCase("take")){
			if(!sender.hasPermission("bank.command.take")){
				sender.sendMessage(msg.prefixred + "Du hast keine Rechte diesen Command auszufŸhren.");
				return;
			}
			args = util.changeArgs(args);
			new takecommand(sender,args,b).execute();
			return;
		}
		
		if(args[0].equalsIgnoreCase("import")){
			if(!sender.hasPermission("bank.command.import")){
				sender.sendMessage(msg.prefixred + "Du hast keine Rechte diesen Command auszufŸhren.");
				return;
			}
			args = util.changeArgs(args);
			new importcommand(main,sender,b).execute();
			return;
		}
		
		if(args[0].equalsIgnoreCase("sum")){
			if(!sender.hasPermission("bank.command.sum")){
				sender.sendMessage(msg.prefixred + "Du hast keine Rechte diesen Command auszufŸhren.");
				return;
			}
			args = util.changeArgs(args);
			new sumcommand(sender,b).execute();
			return;
		}
		
		if(args[0].equalsIgnoreCase("interest")){
			if(!sender.hasPermission("bank.command.interest")){
				sender.sendMessage(msg.prefixred + "Du hast keine Rechte diesen Command auszufŸhren.");
				return;
			}
			args = util.changeArgs(args);
			new interestcommand(b).execute();
			return;
		}
		
		if(args[0].equalsIgnoreCase("showinterest")){
			if(!sender.hasPermission("bank.command.showinterest")){
				sender.sendMessage(msg.prefixred + "Du hast keine Rechte diesen Command auszufŸhren.");
				return;
			}
			args = util.changeArgs(args);
			new showinterestcommand(main,sender,args,b).execute();
			return;
		}
		
		
		
		
		sender.sendMessage(msg.prefixred + "Du hast den Command falsch eingegeben.");
		
	}
}