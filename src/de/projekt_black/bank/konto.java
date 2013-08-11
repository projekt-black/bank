package de.projekt_black.bank;

public class konto {

	private String owner;
	private double money;
	
	public konto(String owner, double money){
		this.owner = owner;
		this.money = money;
	}
	
	public konto(String owner){
		this.owner = owner;
		this.money = 0;
	}
	
	public String getOwner(){
		return owner;
	}
	
	public double getMoney(){
		return money;
	}
	
	public void setMoney(double money){
		this.money = money;
	}
	

}
