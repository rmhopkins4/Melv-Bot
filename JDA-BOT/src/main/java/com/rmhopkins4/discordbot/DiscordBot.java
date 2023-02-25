package com.rmhopkins4.discordbot;

import javax.security.auth.login.LoginException;

import com.rmhopkins4.discordbot.commands.CommandManager;
import com.rmhopkins4.discordbot.listeners.EventListener;

import io.github.cdimascio.dotenv.Dotenv;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class DiscordBot {
	
	private final Dotenv config;
	private final ShardManager shardManager;
	
	public DiscordBot() throws LoginException {
		config = Dotenv.configure().ignoreIfMissing().load();
		String token = config.get("TOKEN");
		
		DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
		builder.setStatus(OnlineStatus.ONLINE);
		builder.setActivity(Activity.competing("Super Auto Pets"));
		builder.enableIntents(GatewayIntent.GUILD_MEMBERS, 
				GatewayIntent.MESSAGE_CONTENT, 
				GatewayIntent.GUILD_PRESENCES);
		
		// cache'ing users is very expensive and should be avoided if possible
		//builder.setMemberCachePolicy(MemberCachePolicy.ALL); //caches all members in every server
		//builder.setChunkingFilter(ChunkingFilter.ALL); //caches all members on startup
		//builder.enableCache(CacheFlag.ONLINE_STATUS); //specifically caches users' online status'
		
		shardManager = builder.build();
		
		// Register listeners
		shardManager.addEventListener(new EventListener());
		shardManager.addEventListener(new CommandManager());
	}
	
	public ShardManager getShardManager() {
		return shardManager;
	}
	
	public Dotenv getConfig() {
		return config;
	}
	
	public static void main(String[] args) {
		try {
			DiscordBot bot = new DiscordBot();
		} catch(LoginException e) {
			System.out.println("ERROR: Provided bot token is invalid!");
		}
	}
}
