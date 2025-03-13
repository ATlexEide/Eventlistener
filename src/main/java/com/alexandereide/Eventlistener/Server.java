package com.alexandereide.Eventlistener;
import org.bukkit.Bukkit;
import java.io.IOException;
import java.net.URI;



public class Server {
    static HttpClient client = HttpClient.newHttpClient();

    static void send(String data){
        try {
            Bukkit.getServer().getLogger().info(data);

            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:3000/events"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();
            HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:3000/server/chat"))
                    .build();

    }catch(IOException | InterruptedException e){
        Bukkit.getLogger().severe("Error sending data, discord bot possibly not running");
    }
}
}