package com.server;

import com.server.http.HttpSession;
import com.server.provider.ClassProvider;
import com.server.routing.ServerRouteConfigImpl;
import com.server.util.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.FutureTask;

public class ServerImpl implements Server {

    private ServerRouteConfigImpl serverRouteConfig;
    private boolean isRunning;
    private final ServerSocket serverSocket;
    private Map<String, HttpSession> sessionMap;

    public ServerImpl(ServerSocket serverSocket, ClassProvider classProvider) throws InstantiationException, IllegalAccessException {

        this.sessionMap = new HashMap<>();
        this.serverSocket = serverSocket;
        this.serverRouteConfig = new ServerRouteConfigImpl(classProvider);
    }

    @Override
    public void runServer() throws SocketException {

        System.out.println(Constants.SERVER_STARTED_MESSAGE);

        this.isRunning = true;
        this.serverSocket.setSoTimeout(Constants.SOCKET_TIMEOUT);
        this.acceptRequest();
    }

    private void acceptRequest() {

        while (isRunning) {

            try (
                    Socket clientSocket = this.serverSocket.accept()
            ) {

                clientSocket.setSoTimeout(Constants.SOCKET_TIMEOUT);
                ConnectionHandler connectionHandler = new ConnectionHandler(clientSocket, this.serverRouteConfig, this.sessionMap);
                FutureTask<?> futureTask = new FutureTask<>(connectionHandler, null);
                futureTask.run();

            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }
}
