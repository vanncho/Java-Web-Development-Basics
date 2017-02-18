package com.server.util;

public interface Constants {

    int SOCKET_PORT = 8080;

    int SOCKET_TIMEOUT = 20_000;

    String HTTP_VERSION = "http/1.1";

    String SERVER_STARTED_MESSAGE = "Server started";

    String INVALID_REQUEST_LINE_MESSAGE = "Invalid request line";

    String INVALID_HEADERS_MESSAGE = "Invalid headers";

    String BAD_REQUEST_MESSAGE = "HTTP/1.1 400 Bad Request\n\n";

    String FILE_NOT_FOUND_MESSAGE = "HTTP/1.1 404 File Not Found\n\n";

    String INTERNAL_SERVER_ERROR_MESSAGE = "HTTP/1.1 500 Internal Server Error\n\n";

    String INTERNAL_APP_STRING = System.getProperty("user.dir") + "/out/production/handmade-web-server/com/app/";

}
