package com.alexandereide.Eventlistener;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Events implements Listener {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
@EventHandler
public void onPLayerJoin(PlayerJoinEvent event) {
    Data.onlinePlayers++;
    Data JoinData = new Data();
    JoinData.player = event.getPlayer().getName();
    JoinData.event = event.getEventName();

    String json = gson.toJson(JoinData);
    Server.send(json);
}
@EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
    Data.onlinePlayers--;
    Data LeaveData = new Data();
    LeaveData.player = event.getPlayer().getName();
    LeaveData.event = event.getEventName();

    String json = gson.toJson(LeaveData);
    Server.send(json);
}
@EventHandler
    public void onChat(AsyncPlayerChatEvent event) throws IOException, InterruptedException {
    Data chatData = new Data();
    chatData.player = event.getPlayer().getName();
    chatData.event = "ChatEvent";
    chatData.message = event.getMessage();

    String json = gson.toJson(chatData);
    Server.send(json);

}
@EventHandler
    public void onPlayerChangeGameMode(PlayerGameModeChangeEvent event) {
    Data GameModeChangeData = new Data();
    GameModeChangeData.player = event.getPlayer().getDisplayName();
    GameModeChangeData.event = event.getEventName();
    GameModeChangeData.gameMode = event.getPlayer().getGameMode().toString();
    GameModeChangeData.newGameMode = event.getNewGameMode().toString();

    String json = gson.toJson(GameModeChangeData);
    Server.send(json);
}

@EventHandler
public void onBlockBreak(BlockBreakEvent event) {
    String block = event.getBlock().getRelative(0,-1,0).getType().toString();
    if(block.equals("CHEST")){
        Bukkit.broadcastMessage(event.getPlayer().getDisplayName() + " found treasure!");
    };
    Bukkit.getLogger().info("BOTTOM " + event.getBlock().getRelative(0,-1,0).getType().toString());
//    Bukkit.broadcastMessage("BOTTOM " + event.getBlock().getRelative(0,-1,0).getType().toString());



//    Bukkit.broadcastMessage(event.getBlock().getLocation().toString());
    Bukkit.getLogger().info(event.getBlock().getLocation().toString());
}
}
