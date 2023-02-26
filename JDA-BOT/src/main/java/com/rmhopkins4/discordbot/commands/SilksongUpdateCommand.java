package com.rmhopkins4.discordbot.commands;

import javax.swing.JProgressBar;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

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
		
		event.reply("It has been " + sinceAnnouncement + " days since Silksong was announced on " + announcementDate.toString() + "."  
				+ "\nTeam Cherry has " + untilLatest + " days remaining to release Silksong before " + updateDate.toString() + ".\n"
				+ "(" + progressBar.getString() + ")").queue();
	}
}
