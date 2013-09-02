package de.projekt_black.bank.command;

import org.bukkit.command.CommandSender;

import de.projekt_black.bank.bank;

public class sumcommand {


	private CommandSender sender;
	private bank b;
	
	public sumcommand(CommandSender sender, bank b){
		this.sender = sender;
		this.b = b;
	}

	public void execute() {
		sender.sendMessage("Summe: " + b.getSum());
	}

}
