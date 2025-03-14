package com.alexandereide.Eventlistener;
import org.bukkit.Bukkit;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Server {
	
    static HttpClient client = HttpClient.newHttpClient();

    static void send(String data){
    	
        try {
            Bukkit.getServer().getLogger().info(data);

            HttpRequest outgoingReq = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:3000/events"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();
            
            client.send(outgoingReq, HttpResponse.BodyHandlers.ofString());
            
    }catch(IOException | InterruptedException e){
        Bukkit.getLogger().severe("Error sending data, discord bot possibly not running");
    }
}
}