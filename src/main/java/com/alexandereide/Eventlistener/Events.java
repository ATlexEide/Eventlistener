package com.alexandereide.Eventlistener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {
	
	public void SendData(Data data) 
	{
		   String json = gson.toJson(data);
	        Bukkit.getServer().getLogger().info("JSON:");
	        Bukkit.getServer().getLogger().info(json);
	        Server.send(json);
	}
	

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @EventHandler
    public void onPLayerJoin(PlayerJoinEvent event) {
        Data.onlinePlayers++;
        Data JoinData = new Data();
        JoinData.player = event.getPlayer().getName();
        JoinData.event = event.getEventName();

        SendData(JoinData);
}
@EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
    Data.onlinePlayers--;
    Data LeaveData = new Data();
    LeaveData.player = event.getPlayer().getName();
    LeaveData.event = event.getEventName();
    
    SendData(LeaveData);
}
@EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

/*
    System.out.println(event.isAsynchronous());
    System.out.println(Bukkit.isPrimaryThread());
*/

    Data chatData = new Data();
        chatData.player = event.getPlayer().getName();
        chatData.event = "ChatEvent";
        chatData.message = event.getMessage();
        
        SendData(chatData);
}
@EventHandler
    public void onPlayerChangeGameMode(PlayerGameModeChangeEvent event) {
    Data GameModeChangeData = new Data();
    GameModeChangeData.player = event.getPlayer().getDisplayName();
    GameModeChangeData.event = event.getEventName();
    GameModeChangeData.gameMode = event.getPlayer().getGameMode().toString();
    GameModeChangeData.newGameMode = event.getNewGameMode().toString();
    
    SendData(GameModeChangeData);
}
}
