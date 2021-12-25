package KiselevaMaria.sample;

import KiselevaMaria.server.GameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AppServer {
    private final ServerSocket serverSocket;
    private final Map<UUID, GameServer> started = new ConcurrentHashMap<>();

    public AppServer(int port) throws IOException{
        serverSocket = new ServerSocket(port);
    }

    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            AppServer server = new AppServer(port);
            server.start();
        }catch (IOException e){
            throw new IllegalStateException("Cannot start this KiselevaMaria.server", e);
        }
    }

    public void start() throws IOException {
       while (true){
           Socket clientSocket = serverSocket.accept();
           GameServer gameServer = new GameServer(clientSocket);
           Thread t = new Thread(gameServer);
           t.start();
       }
    }
}
