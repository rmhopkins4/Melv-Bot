package com.rmhopkins4.discordbot.commands.roulette;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import com.rmhopkins4.discordbot.commands.creditscore.CreditManager;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class RouletteCommand {
	
	public static void runCommand(@NotNull SlashCommandInteractionEvent event) {
		
		Guild guild = event.getGuild();
		Member member = event.getMember();
		RouletteGame game = new RouletteGame(); 
		
		CreditManager cm = new CreditManager(event.getGuild());
		
		OptionMapping betOption = event.getOption("bet");
		int bet = betOption.getAsInt();
		OptionMapping positionOption = event.getOption("position");
		String position = positionOption.getAsString();
		
		try {
			if(bet >= cm.getScore(member.getId())) {
				event.reply("You do not have the required score!").queue();
				return;
			} 
			cm.changeScore(member, -1 * bet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//vet position
		
		
		
		game.addBet(new RouletteBet(bet, position, member), cm);
		int result = game.gameEnd();
		
		event.reply(result + " has been rolled!").queue();
	}
	
}
