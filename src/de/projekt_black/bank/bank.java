package de.projekt_black.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import de.projekt_black.bank.util.msg;


public class bank {
	
	List<konto> konten = new ArrayList<konto>();	
	MySQL sql;
	FileConfiguration fcg;
	main plugin;
	List<String> interesttimes = new ArrayList <String>();
	public bank(FileConfiguration fcg, final main plugin){
		this.sql = new MySQL();
		
		/*plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable(){public void run() {
			Random r = new Random();
			for(int i = 0; i < 45000;i++){
				plugin.b.createKonto("testaccount" + i, r.nextDouble()*50000);
				System.out.println("Neues Konto fŸr testaccoutn" + i + " generiert.");
			}
			}
			});*/
		this.plugin = plugin;
		
		this.fcg = fcg;
		downloadDatabase();
		automaticInterests();
	}
	
	
	public double getMoney(String owner){
		for(konto k : konten){
			if(k.getOwner().equalsIgnoreCase(owner)){
				return k.getMoney();
			}
		}
		konten.add(new konto(owner));
		return 0;
	}
	
	public void setMoney(double money, String owner){
		for(konto k : konten){
			if(k.getOwner().equalsIgnoreCase(owner)){
				k.setMoney(money);
				this.updateDatabase(owner);
				return;
			}
		}
		this.createKonto(owner, money);
	}
	
	
	private void sortTop(){
		boolean changed = true;
		for(int i = 0; i < konten.size(); i++){
			if(!changed){
				return;
			}
			changed = false;
			for(int j = 0; j < konten.size()-1; j++){
				if(konten.get(j).getMoney() < konten.get(j + 1).getMoney()){
					changed = true;
					konto tmp = konten.get(j);
					konten.set(j, konten.get(j+1));
					konten.set(j+1, tmp);
				}
			}
		}
	}
	
	public String[] getTop(boolean money){
		sortTop();
		int size = 10;
		if(konten.size() < 10){
			size = konten.size();
		}
		String[] top = new String[size];
		int toplist = 0;
		for(int i = 0; i < konten.size(); i++){
			if(toplist < 10){
				if(!fcg.getStringList("config.hidetop").contains(konten.get(i).getOwner())){
					if(money){
					top[toplist] = ((int)toplist+1) + " " + konten.get(i).getOwner()+": " + konten.get(i).getMoney();
					}
					else
					{
						top[toplist] = ((int)toplist+1) + " " + konten.get(i).getOwner();
					}
					toplist++;
				}
			}
			else{
				return top;
			}
		}
		/*
		try {
			
			PrintWriter writer = new PrintWriter("top.txt", "UTF-8");
			for(String a:top){
			writer.println(a);
			}
			
			writer.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return top;
	}
	
	
	public void createKonto(String owner){
		for(konto k: konten){
			if(k.getOwner().equalsIgnoreCase(owner)){
				return;
			}
		}
		konten.add(new konto(owner));
		this.insertDatabase(owner);
	}
	
	public void createKonto(String owner, double money){
		for(konto k: konten){
			if(k.getOwner().equalsIgnoreCase(owner)){
				return;
			}
		}
		konten.add(new konto(owner, money));
		this.insertDatabase(owner);
	}
	
	public double getSum(){
		double sum = 0;
		for(konto k: konten){
			sum += k.getMoney();
		}
		return sum;
	}
	
	public void interests(){
		double maxmoneyforinterest = fcg.getDouble("config.maxmoneyforinterest");
		double onlineinterest = fcg.getDouble("config.onlineinterest");
		double offlineinterest = fcg.getDouble("config.offlineinterest");
		for(konto k: konten){
			if(Bukkit.getServer().getOfflinePlayer(k.getOwner()).isOnline()){
				double add = k.getMoney()*onlineinterest/100.0;
				if(k.getMoney()+add > maxmoneyforinterest){
					if(k.getMoney() < maxmoneyforinterest){
						k.setMoney(maxmoneyforinterest);
					}
				}
				else{
					k.setMoney(k.getMoney()+add);
					Bukkit.getServer().getPlayer(k.getOwner()).sendMessage(msg.prefixgreen + "Du hast " + add + " bekommen.");
				}
				this.updateDatabase(k.getOwner());
			}
			else{
				double add = k.getMoney()*offlineinterest*100.0;
				if(k.getMoney()+add > maxmoneyforinterest){
					if(k.getMoney() < maxmoneyforinterest){
						k.setMoney(maxmoneyforinterest);
					}
				}
				else{
				k.setMoney(k.getMoney()+add);
				}
				this.updateDatabase(k.getOwner());
			}
		}
	}
	
	private void downloadDatabase(){
		Connection conn = sql.getConnection();
		ResultSet rs = null;
		PreparedStatement st = null;
		konten.clear();
		try {
			st = conn.prepareStatement("SELECT username, money FROM bank");
			rs = st.executeQuery();
			
			while(rs.next()){
			String username = rs.getString("username");
			Double money = rs.getDouble("money");
			konten.add(new konto(username, money));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st);
		}
	}
	
	private void updateDatabase(String owner){
		sql.queryUpdate("UPDATE bank SET money = '" + this.getMoney(owner) + "' WHERE username LIKE '" + owner + "'");
		//System.out.println("UPDATE bank SET money = '" + this.getMoney(owner) + "' WHERE username LIKE '" + owner + "'");
	}
	
	private void insertDatabase(String owner){
		sql.queryUpdate("INSERT INTO bank (username, money) VALUES ('" + owner + "','" + this.getMoney(owner) + "')");
		//System.out.println("INSERT INTO bank (username, money) VALUES ('" + owner + "','" + this.getMoney(owner) + "')");
	}
	
	private void automaticInterests(){
		
		interesttimes = fcg.getStringList("config.interesttimes");
		plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable(){public void run() {
			checkinterest();
		}
	}, 10, 600);
		/*List<String> times = fcg.getStringList("config.interesttimes");
		for(String time : times){
			if(time.length() == 4){
				try{
				int hour = Integer.parseInt(time.substring(0, 2));
				int minute = Integer.parseInt(time.substring(2));
				Calendar cal = Calendar.getInstance();
				Date now = cal.getTime();
				cal.set(now.getYear(), now., date, hourOfDay, minute)
				}catch(NumberFormatException e){
					times.remove(time);
					System.out.println("Die Zeit " + time + " ist keine Uhrzeit.");
				}
				
			}
			else
			{
				times.remove(time);
				System.out.println("Die Zahl " + time + " ist keine Uhrzeit.");
			}
		}*/
	}
	
	
	private void checkinterest(){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		for(final String time: interesttimes){
			if(time.equalsIgnoreCase(sdf.format(now))){
				this.interests();
				interesttimes.remove(time);
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){public void run() {
					interesttimes.add(time);
				}
			}, 6000);
				System.out.println("[Bank] Zinsen wurden ausgezahlt!");
			}
			
			return;
			
		}
		
	}
}
