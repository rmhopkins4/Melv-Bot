package com.rmhopkins4.discordbot.commands.creditscore;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class RetrieveLowestScoreCommand {

	public static void runCommand(@NotNull SlashCommandInteractionEvent event) {
		
		CreditManager cm = new CreditManager(event.getGuild());
		ArrayList<String> lowestUsers = cm.getLowest();
		int score = 0;
		try {
			score = cm.getScore(lowestUsers.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String message = "The lowest score belongs to: ";
		for(String str : lowestUsers) {
			message += "<@" + str + "> ";
		}
		message += "! They have " + score + " points.";
		
		event.reply(message).queue();
	}
}
