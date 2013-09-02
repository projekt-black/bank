package de.projekt_black.bank.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import org.bukkit.command.CommandSender;

import de.projekt_black.bank.bank;
import de.projekt_black.bank.main;

public class importcommand {


	private main main;
	private CommandSender sender;
	private bank b;
	
	public importcommand(main main, CommandSender sender, bank b){
		this.main = main;
		this.sender = sender;
		this.b = b;
	}

	public void execute() {
		File[] files = new File(main.getDataFolder().getAbsoluteFile() + "/import").listFiles();
		if(files == null){
			sender.sendMessage("null");
			return;
		}
		int j = 0;
			for (int i = 0; i < files.length; i++) {
				if(!files[i].isDirectory() && files[i].getName().contains(".data")){
					try {
					FileReader fr = new FileReader(files[i]);
					 BufferedReader br = new BufferedReader(fr);
					 String data = br.readLine().substring(2);
					 String name = files[i].getName().substring(0, files[i].getName().length() - 5);
					 Double money = parseDouble(data);
					
					if(money > 0){
					b.createKonto(name,money);
					j++;
					}
					 br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		sender.sendMessage("Es wurden " + j + " Accounts importiert.");
	}
	
	public Double parseDouble(String data){
		DecimalFormat df = new DecimalFormat();
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(symbols);
		try {
			return df.parse(data).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0.0;
	}

}
