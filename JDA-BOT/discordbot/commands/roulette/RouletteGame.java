package com.rmhopkins4.discordbot.commands.roulette;

import java.util.ArrayList;
import java.util.Timer;

import com.rmhopkins4.discordbot.commands.creditscore.CreditManager;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class RouletteGame {
	
	int timeElapsed;
	int rolledNumber;
	
	int maxNum = 36;
	int minNum = 0;
	int range = maxNum + 1;
	
	Guild guild;
	ArrayList<RouletteBet> betList = new ArrayList<>();
	
	CreditManager cm;
	
	public void addBet(RouletteBet bet, CreditManager cm) {
		betList.add(bet);
		this.cm = cm;
	}
	
	public int gameEnd() {
		
		rolledNumber = (int)(Math.random() * range);
		
		for(RouletteBet bet : betList) {
			if(Integer.valueOf(bet.getPosition()) == rolledNumber) {
				cm.changeScore(bet.getMember(), bet.getPoints() * 35);
			}
		}
		
		return rolledNumber;
	}
	
}
