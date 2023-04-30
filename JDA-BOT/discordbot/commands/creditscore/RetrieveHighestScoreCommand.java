package com.rmhopkins4.discordbot.commands.creditscore;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class RetrieveHighestScoreCommand {

	public static void runCommand(@NotNull SlashCommandInteractionEvent event) {
		
		CreditManager cm = new CreditManager(event.getGuild());
		ArrayList<String> highestUsers = cm.getHighest();
		int score = 0;
		try {
			score = cm.getScore(highestUsers.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String message = "The highest score belongs to: ";
		for(String str : highestUsers) {
			message += "<@" + str + "> ";
		}
		message += "! They have " + score + " points.";
		
		event.reply(message).queue();
	}
}
