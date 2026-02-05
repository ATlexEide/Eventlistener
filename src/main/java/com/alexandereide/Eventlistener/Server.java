package com.alexandereide.Eventlistener;
import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;
import org.bukkit.Bukkit;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.scheduler.BukkitRunnable;


public class Server {
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();


	static void start(String ip, int port){
		try {
			Javalin app = Javalin.create(config -> {
				config.bundledPlugins.enableCors(cors -> {
					cors.addRule(CorsPluginConfig.CorsRule::anyHost);
				});
			}).start(ip, port);

			app.get("/status", ctx -> {
				String json = gson.toJson(new Data());
				ctx.result(json);
			});

			app.post("/chat", ctx -> {
				StringBuilder sb = new StringBuilder(ctx.body());
				String resultString = sb.deleteCharAt(0).deleteCharAt(sb.length()-1).toString().replace("\\","");
				Bukkit.broadcastMessage(resultString);
				ctx.status();
			});

//			app.post("/cmd", ctx -> {
//				Bukkit.getLogger().info(ctx.body());
//
//				Bukkit.getScheduler().runTaskLater(Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "say hi"));
//				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "say hi");
//				//Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), ctx.body());
//				ctx.status();
//			});
			Bukkit.getLogger().info("HTTP SERVER RUNNING");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
    static void send(String data){
        try {
			String discord_uri = String.format("%s/chat", Data.discordIp);
			Bukkit.getLogger().info("DISCORD URI");
			Bukkit.getLogger().info(discord_uri);
            Bukkit.getServer().getLogger().info(data);
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()

					.uri(URI.create(discord_uri))
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(data))
					.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

	    }catch(Exception e){
    	    Bukkit.getLogger().severe("Error sending data, discord bot possibly not running");
    	}
	}
}