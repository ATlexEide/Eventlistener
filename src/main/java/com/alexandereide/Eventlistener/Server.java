package com.alexandereide.Eventlistener;
import org.bukkit.Bukkit;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Server {
    static void send(String data){
        try {
            Bukkit.getServer().getLogger().info(data);
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("http://127.0.0.1:3000/events"))
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(data))
					.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

	    }catch(Exception e){
    	    Bukkit.getLogger().severe("Error sending data, discord bot possibly not running");
    	}
	}
}