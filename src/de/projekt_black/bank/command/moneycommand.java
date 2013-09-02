package de.projekt_black.bank.command;

import org.bukkit.command.CommandSender;

import de.projekt_black.bank.bank;
import de.projekt_black.bank.util.msg;

public class moneycommand {


	private CommandSender sender;
	private String[] args;
	private bank b;
	
	public moneycommand(CommandSender sender, String[] args, bank b){
		this.sender = sender;
		this.args = args;
		this.b = b;
	}

	public void execute() {
		if(args.length != 1)	{
			sender.sendMessage(msg.prefixred + "Du hast den Command falsch eingegeben.");
			return;
		}
		
		sender.sendMessage(msg.prefixgreen + "Du hast " + b.getMoney(args[0]) + " Blatien auf deinem Konto.");
		
	}

}
