package de.projekt_black.bank.command;

import org.bukkit.command.CommandSender;

import de.projekt_black.bank.bank;
import de.projekt_black.bank.util.msg;

public class topcommand {


	private CommandSender sender;
	private String[] args;
	private bank b;
	
	public topcommand(CommandSender sender, String[] args, bank b){
		this.sender = sender;
		this.args = args;
		this.b = b;
	}

	public void execute() {
		if(args.length != 0)	{
			sender.sendMessage(msg.prefixred + "Du hast den Command falsch eingegeben.");
			return;
		}
		if(sender.hasPermission("bank.top.money")){
		String[] top = b.getTop(true);
		sender.sendMessage(top);
		return;
		}
		if(sender.hasPermission("bank.top.nomoney")){
			String[] top = b.getTop(false);
			sender.sendMessage(top);
			return;
		}
		
	}

}
