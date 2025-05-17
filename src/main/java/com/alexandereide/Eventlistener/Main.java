package com.alexandereide.Eventlistener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;




public final class Main extends JavaPlugin  {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    


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

		Server.start("127.0.0.1",3001);
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
