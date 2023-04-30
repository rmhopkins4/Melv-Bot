package com.rmhopkins4.discordbot.listeners;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.rmhopkins4.discordbot.commands.creditscore.CreditManager;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.RichPresence;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
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
	
	
	@Override
	public void onUserActivityStart(@NotNull UserActivityStartEvent event) {
		if(!event.getNewActivity().isRich()) { //check if activity is Rich Presence
			return;
		} 
		RichPresence act = event.getNewActivity().asRichPresence();
		String memberMention = event.getMember().getAsMention();
		String actName = act.getName();
		if(actName == null) {
			actName = "";
		}
		actName = actName.toLowerCase();
		
		String actDetails = act.getDetails();
		if(actDetails == null) {
			actDetails = "";
		}
		actDetails = actDetails.toLowerCase();
		
		String actState = act.getState();
		if(actState == null) {
			actState = "";
		}
		actState = actState.toLowerCase();
		
		System.out.println(event.getMember().getEffectiveName() + " is doing " + actName + " : " + actDetails + " : " + actState + " in " + event.getGuild().getName());
		
		if(actDetails.contains("in game") && actName.contains("league of legends")) {
			new CreditManager(event.getGuild()).changeScore(event.getMember(), -100);
			event.getGuild().getDefaultChannel().asStandardGuildMessageChannel().sendMessage(memberMention + " has started playing League! EW!:face_vomiting:").queue();
		}
		
	}
}
