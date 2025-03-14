package com.alexandereide.Eventlistener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;




public final class Main extends JavaPlugin {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public void onEnable() {

        Data serverInfo = new Data();
        serverInfo.ip = Bukkit.getIp();
        serverInfo.port = String.valueOf(Bukkit.getPort());
        serverInfo.event = "ServerStart";
        serverInfo.motd = Bukkit.getMotd();
//         Plugin startup logic
        Bukkit.getLogger().info("Plugin Brutally Forced To Work");
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        String json = gson.toJson(serverInfo);
        Server.send(json);
    	Bukkit.getLogger().info("YIPPIEEEE");
        Bukkit.getLogger().info("Plugin Running Yipie");
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
