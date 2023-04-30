package com.rmhopkins4.discordbot.commands;

import java.awt.Color;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class RandomCatCommand {
	
	/*
	 * Special thanks to Adam Mangoroban, UMBC '26 for helping with the JSON situation!
	 */
	public static void runCommand(@NotNull SlashCommandInteractionEvent event) {
		
		try {
			
			URL url = new URL("https://cataas.com/cat?json=true");
			
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responseCode = conn.getResponseCode();
			// 200 = OK!
			if(responseCode != 200) {
				throw new IOException("response code invalid");
			} else {
				
				StringBuilder infoBuilder = new StringBuilder();
				Scanner scanner = new Scanner(url.openStream());
				while(scanner.hasNext()) {
					infoBuilder.append(scanner.nextLine());
				}
				scanner.close();
				
				JSONObject obj = (JSONObject)new JSONParser().parse(infoBuilder.toString());
				
				String catURL = (String) obj.get("url");
				
				EmbedBuilder eb = new EmbedBuilder();
				eb.setImage("https://cataas.com" + catURL);
				eb.setTitle("Random cat!");
				eb.setColor(new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)));
				
				event.deferReply();
				event.replyEmbeds(eb.build()).queue();
			}
			
		} catch(IOException e) {
			event.reply(e.toString());
		} catch (ParseException e) {
			event.reply(e.toString());
		}
		
	}
}
