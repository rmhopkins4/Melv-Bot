package com.rmhopkins4.discordbot.commands;

import java.awt.Color;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import javax.swing.JProgressBar;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class TOTKUpdateCommand {

	public static void runCommand(@NotNull SlashCommandInteractionEvent event) {
		LocalDate announcementDate = LocalDate.of(2019, Month.JUNE, 11);
		LocalDate releaseDate = LocalDate.of(2023, Month.MAY, 12);
		LocalDate nowDate = LocalDate.now();
		
		long sinceAnnouncement = ChronoUnit.DAYS.between(announcementDate, nowDate);
		long untilLatest = ChronoUnit.DAYS.between(nowDate, releaseDate);
		
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
		eb.setTitle("Tears of the Kingdom Update!");
		eb.setThumbnail("https://upload.wikimedia.org/wikipedia/en/thumb/f/fb/The_Legend_of_Zelda_Tears_of_the_Kingdom_cover.jpg/220px-The_Legend_of_Zelda_Tears_of_the_Kingdom_cover.jpg");
		eb.setColor(new Color(73,214,155));
		eb.setDescription("It has been " + sinceAnnouncement + " days since TOTK was announced on " 
				+ announcementDate.toString() + "."  + "\n\nThere are " + untilLatest + " days until TOTK releases on " 
				+ releaseDate.toString());
		eb.addField("Progress Bar", percentBar + progressBar.getString(), false);
		
		event.replyEmbeds(eb.build()).queue();
		
//		event.reply("It has been " + sinceAnnouncement + " days since TOTK was announced on " + announcementDate.toString() + "."  
//				+ "\nThere are " + untilLatest + " days until TOTK releases on " + releaseDate.toString() + ".\n"
//				+ percentBar + progressBar.getString()).queue();
	}
	
}
