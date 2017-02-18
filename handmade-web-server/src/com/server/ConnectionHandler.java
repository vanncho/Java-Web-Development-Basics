package com.server;

import com.server.exception.BadRequestException;
import com.server.handler.HttpHandler;
import com.server.http.HttpContext;
import com.server.http.HttpContextImpl;
import com.server.http.HttpSession;
import com.server.routing.ServerRouteConfig;
import com.server.util.Constants;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ConnectionHandler implements Runnable {

    private final Socket socket;
    private final BufferedReader bufferedReader;
    private final PrintWriter printWriter;
    private final ServerRouteConfig serverRouteConfig;
    private final Map<String, HttpSession> sessionMap;

    public ConnectionHandler(Socket clientSocket, ServerRouteConfig serverRouteConfig, Map<String, HttpSession> sessionMap) throws IOException {

        this.socket = clientSocket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.printWriter = new PrintWriter(this.socket.getOutputStream());
        this.serverRouteConfig = serverRouteConfig;
        this.sessionMap = sessionMap;
    }

    @Override
    public void run() {

        StringBuilder stringBuilder = new StringBuilder();

        try {
            while (this.bufferedReader.ready() || stringBuilder.length() == 0) {

                stringBuilder.append((char)this.bufferedReader.read());
            }

            try {
                HttpContext httpContext = new HttpContextImpl(stringBuilder.toString(), this.sessionMap);
                new HttpHandler(this.serverRouteConfig, this.printWriter).handle(httpContext);

                HttpSession session = httpContext.getHttpRequest().getHttpSession();
                this.sessionMap.put(session.getId(), session);

            } catch (BadRequestException e) {
                this.printWriter.write(Constants.BAD_REQUEST_MESSAGE);
            } catch (FileNotFoundException e) {
                this.printWriter.write(Constants.FILE_NOT_FOUND_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                this.printWriter.write(Constants.INTERNAL_SERVER_ERROR_MESSAGE);
            }

            this.printWriter.close();
            this.bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
