package de.projekt_black.bank.command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import de.projekt_black.bank.bank;
import de.projekt_black.bank.main;

public class showinterestcommand {

		private CommandSender sender;
		private bank b;
		private FileConfiguration fcg;
		
		public showinterestcommand(main main, CommandSender sender, String[] args, bank b){
			this.sender = sender;
			this.b = b;
			fcg = main.getFcg();
		}

		public void execute() {
			
			String username = sender.getName();
			sender.sendMessage("Onlinezinssatz: " + fcg.getDouble("config.onlineinterest") + "%");
			sender.sendMessage("Offlinezinssatz: " + fcg.getDouble("config.offlineinterest") + "%");
			sender.sendMessage("Dein aktueller Kontostand: " + b.getMoney(username));
			
			double add = b.getMoney(username)*fcg.getDouble("config.onlineinterest")/100.0;
			if(b.getMoney(username)+add > fcg.getDouble("config.maxmoneyforinterest")){
				add = fcg.getDouble("config.maxmoneyforinterest") - b.getMoney(username);
			}
			if(add < 0){
				add = 0;
			}
			sender.sendMessage("Zinsen fŸr dich: " + add);
		}

	}

