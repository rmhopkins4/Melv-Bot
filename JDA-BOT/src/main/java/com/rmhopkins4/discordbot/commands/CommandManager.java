package com.rmhopkins4.discordbot.commands;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

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
		String command = event.getName(); // /hello, etc.
		
		if(command.equals("say")) {
			SayCommand.runCommand(event);
		} else if(command.equals("maow")) {			
			MaowCatCommand.runCommand(event);
		} else if(command.equals("randomcat")) {
			RandomCatCommand.runCommand(event);
		}
	}
	
	// Guild command -- instantly updated (max 100)
	@Override
	public void onGuildReady(@NotNull GuildReadyEvent event) {
		List<CommandData> commandData = new ArrayList<>();
		
		// Command: /say <message> [channel]
		OptionData option1 = new OptionData(OptionType.STRING, "message", "The message you want Melv to say", true);
		OptionData option2 = new OptionData(OptionType.CHANNEL, "channel", "The channel you want Melv to send the message in", false)
				.setChannelTypes(ChannelType.TEXT, ChannelType.NEWS, ChannelType.GUILD_PUBLIC_THREAD);
		commandData.add(Commands.slash("say", "Make the bot say a message.").addOptions(option1, option2));
		
		// Command: /maowcat
		commandData.add(Commands.slash("maow", "Send maowcat video!"));
		
		// Command: /randomcat
		commandData.add(Commands.slash("randomcat", "Sends a random cat."));
		
		
		
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
