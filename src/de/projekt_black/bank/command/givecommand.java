package de.projekt_black.bank.command;

import org.bukkit.command.CommandSender;

import de.projekt_black.bank.bank;
import de.projekt_black.bank.util.msg;

public class givecommand {


	private CommandSender sender;
	private String[] args;
	private bank b;
	
	public givecommand(CommandSender sender, String[] args, bank b){
		this.sender = sender;
		this.args = args;
		this.b = b;
	}

	public void execute() {
		if(args.length != 2){
			sender.sendMessage(msg.prefixred + "Du hast den Command falsch eingegeben.");
			return;
		}
		
		String username = args[0];
		double money = 0;
		try{
		money = Double.parseDouble(args[1]);
		}catch(NumberFormatException e){
			sender.sendMessage(msg.prefixred + "Der Geldbetrag muss eine Zahl sein.");
			return;
		}
		
		b.setMoney(b.getMoney(username) + money, username);
		sender.sendMessage(msg.prefixgreen + "Der Geldbetrag wurde gesetzt.");
	}

}
