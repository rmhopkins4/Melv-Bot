package com.rmhopkins4.discordbot.commands.creditscore;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class RetrieveScoreCommand {

	public static void runCommand(@NotNull SlashCommandInteractionEvent event) {
		
		User user;
		OptionMapping userOption = event.getOption("user");
		if(userOption != null) {
			user = userOption.getAsUser();
		} else {
			user = event.getUser();
		}
		
		
		CreditManager cm = new CreditManager();
		int socialScore = 0;
		try {
			socialScore = cm.getScore(user.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		event.reply(user.getAsMention() + "'s score is: " + socialScore).queue();
	}
}
