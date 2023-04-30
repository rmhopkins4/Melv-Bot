package com.rmhopkins4.discordbot.commands;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.rmhopkins4.discordbot.commands.creditscore.RetrieveHighestScoreCommand;
import com.rmhopkins4.discordbot.commands.creditscore.RetrieveLowestScoreCommand;
import com.rmhopkins4.discordbot.commands.creditscore.RetrieveScoreCommand;
import com.rmhopkins4.discordbot.commands.roulette.RouletteCommand;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class CommandManager extends ListenerAdapter {
	
	@Override
	public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
		String command = event.getName(); // '/say [...] {...}', '/maow', etc.
		
		switch(command) {
		case "say": 
			SayCommand.runCommand(event);
			break;
		case "maow":
			MaowCatCommand.runCommand(event);
			break;
		case "randomcat":
			RandomCatCommand.runCommand(event);
			break;
		case "silksongupdate":
			SilksongUpdateCommand.runCommand(event);
			break;
		case "tearsupdate":
			TOTKUpdateCommand.runCommand(event);
			break;
		case "getscore":
			RetrieveScoreCommand.runCommand(event);
			break;
		case "highestscore":
			RetrieveHighestScoreCommand.runCommand(event);
			break;
		case "lowestscore":
			RetrieveLowestScoreCommand.runCommand(event);
			break;
		case "roulette":
			RouletteCommand.runCommand(event);
			break;
		}
	}
	
	// Guild command -- instantly updated (max 100)
	@Override
	public void onGuildReady(@NotNull GuildReadyEvent event) {
		List<CommandData> commandData = new ArrayList<>();
		
		// Command: '/say <message> [channel]'
		OptionData option1 = new OptionData(OptionType.STRING, "message", "The message you want Melv to say", true);
		OptionData option2 = new OptionData(OptionType.CHANNEL, "channel", "The channel you want Melv to send the message in", false)
				.setChannelTypes(ChannelType.TEXT, ChannelType.NEWS, ChannelType.GUILD_PUBLIC_THREAD);
		
		// Command: '/say [message] {channel}'
		commandData.add(Commands.slash("say", "Make the bot say a message.").addOptions(option1, option2));
		
		// Command: '/maowcat'
		commandData.add(Commands.slash("maow", "Send maowcat video!"));
		
		// Command: '/randomcat'
		commandData.add(Commands.slash("randomcat", "Sends a random cat."));
		
		// Command: '/silksongupdate'
		commandData.add(Commands.slash("silksongupdate", "Updates on Silksong time."));
		
		// Command: '/tearsOfTheKingdomUpdate'
		commandData.add(Commands.slash("tearsupdate", "Updates on TOTK release distance."));
		
		// Command: '/getscore [user]'
		OptionData option3 = new OptionData(OptionType.USER, "user", "The user you want to get the score from", false);
		commandData.add(Commands.slash("getscore", "Get someone's social credit score").addOptions(option3));
		
		// Command: '/highestscore'
		commandData.add(Commands.slash("highestscore", "Get the highest scorer"));
		
		// Command: '/lowestscore'
		commandData.add(Commands.slash("lowestscore", "Get the lowest scorer"));
		
		// Command: '/roulette' 
		OptionData optionBet = new OptionData(OptionType.INTEGER, "bet", "The amount you would like to bet", true);
		OptionData optionPosition = new OptionData(OptionType.STRING, "position", "Where you would like to bet", true);
		commandData.add(Commands.slash("roulette", "Bet your social credit points on roulette!").addOptions(optionBet, optionPosition));
		
		event.getGuild().updateCommands().addCommands(commandData).queue();
	}
	
	
	// Global command -- up to an hour to update (max 100)
	@Override
	public void onReady(@NotNull ReadyEvent event) {
		//List<CommandData> commandData = new ArrayList<>();
		//commandData.add(Commands.slash("welcome", "Get welcomed by MelvBot."));
		//event.getJDA().updateCommands().addCommands(commandData).queue();
	}
}
