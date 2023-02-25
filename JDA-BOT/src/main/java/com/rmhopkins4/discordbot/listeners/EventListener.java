package com.rmhopkins4.discordbot.listeners;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {
	
	@Override
	public void onMessageReceived(@NotNull MessageReceivedEvent event) {
		String message = event.getMessage().getContentRaw();
		if(message.contains("ping")) {
			event.getChannel().sendMessage("pong").queue();
		}
	}
	
	@Override
	public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
		String userMention = event.getUser().getAsMention();
		String guildName = event.getGuild().getName();
		event.getGuild().getDefaultChannel().asStandardGuildMessageChannel()
		.sendMessage("MelvBot welcomes you to " + guildName + ", **" + userMention + "**!").queue();
	}
}
