package com;

import com.server.Server;
import com.server.ServerImpl;
import com.server.provider.ClassProvider;
import com.server.provider.ClassProviderImpl;
import com.server.util.Constants;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {

        try {

            ServerSocket serverSocket = new ServerSocket(Constants.SOCKET_PORT);
            ClassProvider classProvider = new ClassProviderImpl();
            Server server = new ServerImpl(serverSocket, classProvider);

            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


//        ServerSocket serverSocket = new ServerSocket(8080);
//        //System.out.println(String.format("Use this post: %d", serverSocket.getLocalPort()));
//        Socket clientSocket = serverSocket.accept();
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//
//        String output = "";
//
//        while (br.ready() || output.length() == 0) {
//
//            output += (char) br.read();
//        }
//
//        System.out.println(output);
//
//        PrintWriter pr = new PrintWriter(clientSocket.getOutputStream());
//
//        pr.write("HTTP/1.1 200 OK");
//        pr.write(String.format("%n%nHello"));
//
//        pr.close();
//        br.close();
//        clientSocket.close();
//        serverSocket.close();
    }
}
