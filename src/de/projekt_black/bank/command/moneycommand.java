package de.projekt_black.bank.command;

import org.bukkit.command.CommandSender;

import de.projekt_black.bank.bank;
import de.projekt_black.bank.main;
import de.projekt_black.bank.util.msg;

public class moneycommand {


	private main main;
	private CommandSender sender;
	private String[] args;
	private bank b;
	
	public moneycommand(main main, CommandSender sender, String[] args, bank b){
		this.main = main;
		this.sender = sender;
		this.args = args;
		this.b = b;
	}

	public void execute() {
		if(args.length != 1)	{
			sender.sendMessage(msg.FALSE_SYNTAX);
			return;
		}
		
		sender.sendMessage(msg.money(b.getMoney(args[0]), args[0]));
		
	}

}
