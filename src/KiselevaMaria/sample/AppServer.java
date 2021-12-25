package KiselevaMaria.sample;

import KiselevaMaria.server.GameSession;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AppServer {
    private final ServerSocket serverSocket;
    private final Map<UUID, GameSession> started = new ConcurrentHashMap<>();


    public AppServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);

    }

    public static void main(String[] args) {

        try {
            int port = 9999;
            AppServer server = new AppServer(port);

            server.start();
        } catch (IOException e) {
            throw new IllegalStateException("Cannot start this KiselevaMaria.server", e);
        }
    }

    public void start() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            InputStream inputStream = clientSocket.getInputStream();
            System.out.println(Arrays.toString(inputStream.readAllBytes()));
            GameSession gameSession = new GameSession(clientSocket);
            Thread t = new Thread(gameSession);
            t.start();
        }
    }
}
