package com.rmhopkins4.discordbot.commands.roulette;

import net.dv8tion.jda.api.entities.Member;

public class RouletteBet {

	private int points;
	private String position;
	private Member member;
	
	public RouletteBet(int points, String position, Member member) {
		this.points = points;
		this.position = position;
		this.member = member;
	}
	
	int getPoints() { // package visibility
		return points;
	}
	
	String getPosition() { // package visibility
		return position;
	}
	
	Member getMember() { // package visibility
		return member;
	}
	
}
