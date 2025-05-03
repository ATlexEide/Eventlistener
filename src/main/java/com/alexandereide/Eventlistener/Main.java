package com.alexandereide.Eventlistener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.javalin.Javalin;


public final class Main extends JavaPlugin  {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
   public void start() {
   	try {
		Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {
                    it.anyHost();
                });
            });
        }).start("127.0.0.1", 3001);
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
	    Bukkit.getLogger().info("HTTP SERVER RUNNING");

	} catch (Exception e) {
		// TODO: handle exception
	}
   }

	@Override
	public void onEnable() {
        Data.ip = Bukkit.getIp();
        Data.port = String.valueOf(Bukkit.getPort());
        Data.version = Bukkit.getVersion();
        Data.motd = Bukkit.getMotd();
        Data serverInfo = new Data();
        serverInfo.event = "ServerStart";
//         Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        String json = gson.toJson(serverInfo);

		start();
        Server.send(json);
        Bukkit.getLogger().info("Plugin Running OWO");
    }

    @Override
    public void onDisable() {
        Data serverInfo = new Data();
        serverInfo.event = "ServerStop";
        String json = gson.toJson(serverInfo);

        Server.send(json);
        Bukkit.getLogger().info("Plugin Brutally Killed");
    }
}
