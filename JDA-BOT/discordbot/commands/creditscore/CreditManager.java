package com.rmhopkins4.discordbot.commands.creditscore;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class CreditManager {
	
	
	private File creditData;
	ArrayList<String> currentMemberIDs;
	ArrayList<Integer> currentMemberScores;
	
	private final static String DEFAULT_FILE = "socialCreditData.txt";
	private final static int DEFAULTSCORE = 1000;
	
	public CreditManager(Guild g) {
		this.creditData = new File("guildScores/" + g.getId());
		try {
			if(creditData.createNewFile()) {
				System.out.println("made!");
				g.getDefaultChannel().asStandardGuildMessageChannel()
				.sendMessage("Social Credit Score initialized! Every member will begin with " + DEFAULTSCORE + " points.").queue();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//this(g.getId());
	}
	
	private void readFile() {
		currentMemberIDs = new ArrayList<>();
		currentMemberScores = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(creditData);
			String currentLine;
			while(scanner.hasNextLine()) {
				
				currentLine = scanner.nextLine();
				String currentID = currentLine.substring(0, currentLine.indexOf('='));
				int currentScore = Integer.valueOf(currentLine.substring(currentLine.indexOf('=') + 1, currentLine.length()));
				
				currentMemberIDs.add(currentID);
				currentMemberScores.add(currentScore);
				
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeFile() {
		clearFile(creditData);
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(creditData));
			String currentLine;
			for(int i = 0; i < currentMemberIDs.size(); i++) {
				currentLine = currentMemberIDs.get(i) + "=" + currentMemberScores.get(i);
				out.write(currentLine);
				out.newLine();
			}
			
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void clearFile(File file) {
		try {
			FileWriter fw = new FileWriter(file, false);
			PrintWriter pw = new PrintWriter(fw, false);
			pw.flush();
			pw.close();
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void changeScore(Member member, int deltaScore) {
		changeScore(member.getId(), deltaScore);
	}
	
	public void changeScore(String memberID, int deltaScore) {
		readFile();
		
		if(currentMemberIDs.contains(memberID)) {
			int index = currentMemberIDs.indexOf(memberID);
			currentMemberScores.set(index, currentMemberScores.get(index) + deltaScore);
		} else {
			currentMemberIDs.add(memberID);
			currentMemberScores.add(deltaScore + DEFAULTSCORE);
		}
		writeFile();
	}
	
	public int getScore(String memberID) throws Exception {
		changeScore(memberID, 0);
		
		readFile();
		return currentMemberScores.get(currentMemberIDs.indexOf(memberID));
	}
	
	public ArrayList<String> getHighest() {
		readFile();
		int maxScore = 0;
		ArrayList<String> highestScorers = new ArrayList<>();
		for(int i = 0; i < currentMemberScores.size(); i++) { // get highest score
			if(currentMemberScores.get(i) > maxScore) {
				maxScore = currentMemberScores.get(i);
			}
		}
		for(int i = 0; i < currentMemberScores.size(); i++) { // get users with highest score
			if(currentMemberScores.get(i) == maxScore) {
				highestScorers.add(currentMemberIDs.get(i));
			}
		}
		return highestScorers;
	}
	
	public ArrayList<String> getLowest() {
		readFile();
		int minScore = currentMemberScores.get(0);
		ArrayList<String> highestScorers = new ArrayList<>();
		for(int i = 0; i < currentMemberScores.size(); i++) { // get lowest score
			if(currentMemberScores.get(i) < minScore) {
				minScore = currentMemberScores.get(i);
			}
		}
		for(int i = 0; i < currentMemberScores.size(); i++) { // get users with lowest score
			if(currentMemberScores.get(i) == minScore) {
				highestScorers.add(currentMemberIDs.get(i));
			}
		}
		return highestScorers;
	}
}
