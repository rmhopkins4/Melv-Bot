package com.rmhopkins4.discordbot.commands;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class SayCommand {

	public static void runCommand(@NotNull SlashCommandInteractionEvent event) {
		OptionMapping messageOption = event.getOption("message");
		String message = messageOption.getAsString();
			
		MessageChannel channel = null;
		OptionMapping channelOption = event.getOption("channel");
		if(channelOption != null) {
			channel = channelOption.getAsChannel().asGuildMessageChannel();
		} else {
			channel = event.getChannel();
		}
		channel.sendMessage(message).queue();
		event.reply("Your message was sent!").setEphemeral(true).queue();
	}
}
