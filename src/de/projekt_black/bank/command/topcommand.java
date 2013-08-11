package de.projekt_black.bank.command;

import org.bukkit.command.CommandSender;

import de.projekt_black.bank.bank;
import de.projekt_black.bank.main;
import de.projekt_black.bank.util.msg;

public class topcommand {


	private main main;
	private CommandSender sender;
	private String[] args;
	private bank b;
	
	public topcommand(main main, CommandSender sender, String[] args, bank b){
		this.main = main;
		this.sender = sender;
		this.args = args;
		this.b = b;
	}

	public void execute() {
		if(args.length != 0)	{
			sender.sendMessage(msg.FALSE_SYNTAX);
			return;
		}
		String[] top = b.getTop();
		sender.sendMessage(top);
		
	}

}
