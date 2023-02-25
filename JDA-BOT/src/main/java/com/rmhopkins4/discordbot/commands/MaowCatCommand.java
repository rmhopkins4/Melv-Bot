package com.rmhopkins4.discordbot.commands;

import java.io.File;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.FileUpload;

public class MaowCatCommand {
	
	public static void runCommand(@NotNull SlashCommandInteractionEvent event) {
		String memberName = event.getMember().getEffectiveName();
		event.reply("**" + memberName + "** has maowed!").addFiles(FileUpload.fromData(new File("cat.mp4"))).queue();
	}
}
