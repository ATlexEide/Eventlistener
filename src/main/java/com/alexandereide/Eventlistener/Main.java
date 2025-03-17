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
		Javalin app = Javalin.create().start("127.0.0.1", 3000);
		
	    app.get("/", ctx -> ctx.result("Hello, World!"));
	    Bukkit.getLogger().info("HTTP SERVER RUNNING");
	    		
	} catch (Exception e) {
		// TODO: handle exception
	}
   }

	@Override
	public void onEnable() {
		start();
        Data serverInfo = new Data();
        serverInfo.ip = Bukkit.getIp();
        serverInfo.port = String.valueOf(Bukkit.getPort());
        serverInfo.event = "ServerStart";
        serverInfo.motd = Bukkit.getMotd();
//         Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        String json = gson.toJson(serverInfo);
        Server.send(json);
    
        Bukkit.getLogger().info("Plugin Running OWO");
    }

    @Override
    public void onDisable() {
        Data serverInfo = new Data();
        serverInfo.event = "ServerStop";
        String json = gson.toJson(serverInfo);
        Server.send(json);
//         Plugin shutdown logic
        Bukkit.getLogger().info("Plugin Brutally Killed");
    }
}
