package de.projekt_black.bank;

import java.util.ArrayList;
import java.util.List;

public class bank {
	
	List<konto> konten = new ArrayList<konto>();	
	
	public bank(){
		konto a = new konto("blacknightwulf", 1.2);
		konto b = new konto("Da_Vida", 9876.54);
		konto c = new konto("lukas123405");
		konten.add(a);
		konten.add(b);
		konten.add(c);
	}
	
	
	public double getMoney(String owner){
		for(konto k : konten){
			if(k.getOwner().equalsIgnoreCase(owner)){
				return k.getMoney();
			}
		}
		return 0;
	}
	
	
	public String[] getTop(){
		for(int i = 0; i < konten.size(); i++){
			for(int j = 0; j < konten.size()-1; j++){
				if(konten.get(j).getMoney() < konten.get(j + 1).getMoney()){
					konto tmp = konten.get(j);
					konten.set(j, konten.get(j+1));
					konten.set(j+1, tmp);
				}
			}
		}
		int size = 10;
		if(konten.size() < 10){
			size = konten.size();
		}
		String[] top = new String[size];
		for(int i = 0; i < konten.size(); i++){
			if(i < 10){
				top[i] = ((int)i+1) + " " + konten.get(i).getOwner()+": " + konten.get(i).getMoney();
			}
			else{
				return top;
			}
		}
		
		return top;
	}
}
