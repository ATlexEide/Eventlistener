package com.alexandereide.Eventlistener;
import org.bukkit.Bukkit;
import java.io.IOException;
import java.net.URI;
//import java.net.http.HttpClient;
//
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import io.javalin.Javalin;

public class Server {
	

    public static void start() {
    	
    	try {
    		Javalin app = Javalin.create().start("127.0.0.1", 3000);
    		
    	    app.get("/", ctx -> ctx.result("Hello, World!"));
    	    Bukkit.getLogger().info("HTTP SERVER RUNNING");
    	    		
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    
    
    
//    static HttpClient client = HttpClien<>t.newHttpClient();
    static void send(String data){
    	
        try {
            Bukkit.getServer().getLogger().info(data);

//            HttpRequest outgoingReq = HttpRequest.newBuilder()
//                    .uri(URI.create("http://localhost:3000/events"))
//                    .header("Content-Type", "application/json")
//                    .POST(HttpRequest.BodyPublishers.ofString(data))
//                    .build();
//            
//            client.send(outgoingReq, HttpResponse.BodyHandlers.ofString());
            
    }catch(){
        Bukkit.getLogger().severe("Error sending data, discord bot possibly not running");
    }
        
}
}