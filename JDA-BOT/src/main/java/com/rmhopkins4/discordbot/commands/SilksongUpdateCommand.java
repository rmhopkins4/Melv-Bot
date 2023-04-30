package com.rmhopkins4.discordbot.commands;

import javax.swing.JProgressBar;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import okhttp3.internal.ws.RealWebSocket.Message;

import java.awt.Color;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class SilksongUpdateCommand {

	public static void runCommand(@NotNull SlashCommandInteractionEvent event) {
		LocalDate announcementDate = LocalDate.of(2019, Month.FEBRUARY, 14);
		LocalDate updateDate = LocalDate.of(2023, Month.JUNE, 12);
		LocalDate nowDate = LocalDate.now();
		
		long sinceAnnouncement = ChronoUnit.DAYS.between(announcementDate, nowDate);
		long untilLatest = ChronoUnit.DAYS.between(nowDate, updateDate);
		
		JProgressBar progressBar = new JProgressBar(0, (int)sinceAnnouncement + (int)untilLatest + 1);
		progressBar.setValue((int)sinceAnnouncement);
		progressBar.setIndeterminate(false);
		progressBar.setStringPainted(true);
		
		int percent = Integer.parseInt(progressBar.getString().substring(0, progressBar.getString().indexOf('%')));
		StringBuffer percentBar = new StringBuffer();
		for(int i = 0; i < 40; i++) {
			if(i <= percent / 2.5d) {
				percentBar.append("▓");
			} else {
				percentBar.append("░");
			}
		}
		
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Silksong Update!");
		eb.setThumbnail("https://m.media-amazon.com/images/M/MV5BMDYyNjc3MDYtMTVkYi00YjBmLWE2ZDctY2ZkMDY4NmQ3MDAwXkEyXkFqcGdeQXVyMTA0MTM5NjI2._V1_.jpg");
		eb.setColor(new Color(235,47,62));
		eb.setDescription("It has been " + sinceAnnouncement + " days since Silksong was announced on " 
				+ announcementDate.toString() + "."  + "\n\nTeam Cherry has " + untilLatest 
				+ " days remaining to release Silksong before " + updateDate.toString());
		eb.addField("Progress Bar", percentBar + "\n" + progressBar.getString(), false);
		
		event.replyEmbeds(eb.build()).queue();
		
//		event.reply("It has been " + sinceAnnouncement + " days since Silksong was announced on " + announcementDate.toString() + "."  
//				+ "\nTeam Cherry has " + untilLatest + " days remaining to release Silksong before " + updateDate.toString() + ".\n"
//				+ percentBar + progressBar.getString()).queue();
		
	}
}
