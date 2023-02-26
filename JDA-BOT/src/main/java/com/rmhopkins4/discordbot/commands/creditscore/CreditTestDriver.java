package com.rmhopkins4.discordbot.commands.creditscore;
import net.dv8tion.jda.api.entities.Member;

public class CreditTestDriver {
	
	public static void main(String[] args) {
		CreditManager cm = new CreditManager();
		cm.changeScore("robbb#1754", 100);
		
		try {
			System.out.println(cm.getScore("robbb#1754"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
