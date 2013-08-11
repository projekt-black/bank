package de.projekt_black.bank.util;

public class util {

	public static String[] changeArgs(String[] args){
		
		int length = args.length-1;
		if(length < 0){
			length = 0;
		}
		String[] args2 = new String[length];
		
		for(int i = 1; i< args.length;i++){
			args2[i-1] = args[i];
			}
		return args2;
		}
	
	
	
	
}
