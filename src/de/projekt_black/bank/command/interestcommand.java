package de.projekt_black.bank.command;

import de.projekt_black.bank.bank;

public class interestcommand {

	private bank b;
	
	public interestcommand(bank b){

		this.b = b;
	}

	public void execute() {
		b.interests();
	}

}
