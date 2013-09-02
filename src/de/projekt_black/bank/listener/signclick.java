package de.projekt_black.bank.listener;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import de.projekt_black.bank.bank;
import de.projekt_black.bank.main;
import de.projekt_black.bank.util.msg;

public class signclick implements Listener{

	main main;
	bank b;
	
	public signclick(main main, bank b){
		this.main = main;
		this.b = b;
	}
	
	@EventHandler
	public void signclickListener(PlayerInteractEvent e){
		if(e.isCancelled()){
			return;
		}
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK)
		{
			return;
		}
		Block b = e.getClickedBlock();
		
		Player p = e.getPlayer();
		if(!(b.getType() == Material.WALL_SIGN || b.getType() == Material.SIGN_POST))
		{
			return;
		}
		Sign s = (Sign)b.getState();
		if(!s.getLine(0).equalsIgnoreCase(ChatColor.AQUA + "[Bank]")){
			return;
		}
		double money = this.b.getMoney(p.getName());
		
		if(s.getLine(1).equalsIgnoreCase("Kontostand")){
			if(!p.hasPermission("bank.sign.use.kontostand")){
				p.sendMessage(msg.prefixred + "Du hast keine Rechte aus dieses Schild zu klicken.");
				return;
			}
			p.sendMessage(msg.prefixgreen + "Du hast " + money + " Blatien auf deinem Konto.");
		}
		Economy economy;
		RegisteredServiceProvider<Economy> economyProvider = main.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider == null) {
        	p.sendMessage(msg.prefixred + "Es ist ein Fehler aufgetreten. Informiere bitte ein Teammitglied");
			System.err.print("Bei Vault ist ein Fehler aufgetreten.");
			return;
        }
        economy = economyProvider.getProvider();
        if (economy == null) {
        	p.sendMessage(msg.prefixred + "Es ist ein Fehler aufgetreten. Informiere bitte ein Teammitglied");
			System.err.print("Bei Vault ist ein Fehler aufgetreten.");
			return;
        }
		if(s.getLine(1).equalsIgnoreCase("Einzahlung")){
			if(!p.hasPermission("bank.sign.use.einzahlung")){
				p.sendMessage(msg.prefixred + "Du hast keine Rechte auf dieses Schild zu klicken.");
				return;
			}
			double amount = 0;
			try{
			amount = Double.parseDouble(s.getLine(2));
			}catch(NumberFormatException ex){
				p.sendMessage(msg.prefixred + "Es ist ein Fehler aufgetreten. Informiere bitte ein Teammitglied");
				System.err.print("Bei den Koordinaten X: " + b.getX() + " Y: " + b.getY() + " Z: " + b.getZ() + " ist ein Fehler aufgetreten.");
				return;
			}
			if(economy.getBalance(p.getName())<amount){
				p.sendMessage(msg.prefixred + "Du hast zu wenig Geld.");
				return;
			}
			economy.bankWithdraw(p.getName(), amount);
			this.b.setMoney(money + amount, p.getName());
			System.out.println(p.getName() + " hat " + amount + "BL auf sein Konto eingezahlt.");
			p.sendMessage(msg.prefixgreen + "Du hast " + amount + " Blatien eingezahlt.");
		}
		if(s.getLine(1).equalsIgnoreCase("Auszahlung")){
			if(!p.hasPermission("bank.sign.use.auszahlung")){
				p.sendMessage(msg.prefixred + "Du hast keine Rechte auf dieses Schild zu klicken.");
				return;
			}
			double amount = 0;
			try{
			amount = Double.parseDouble(s.getLine(2));
			}catch(NumberFormatException ex){
				p.sendMessage(msg.prefixred + "Es ist ein Fehler aufgetreten. Informiere bitte ein Teammitglied");
				System.err.print("Bei den Koordinaten X: " + b.getX() + " Y: " + b.getY() + " Z: " + b.getZ() + " ist ein Fehler aufgetreten.");
				return;
			}
			if(this.b.getMoney(p.getName())<amount){
				p.sendMessage(msg.prefixred + "Du hast zu wenig Geld.");
				return;
			}
			System.out.println("[Bank] " + p.getName() + " hat " + amount + "BL von seinem Bankkonto abgehoben.");
			economy.bankDeposit(p.getName(), amount);
			this.b.setMoney(money - amount, p.getName());
			p.sendMessage(msg.prefixgreen + "Du hast " + amount + " Blatien von deinem Konto abgehoben.");
		}
		

	}
	
}
