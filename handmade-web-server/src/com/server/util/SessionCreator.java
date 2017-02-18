package com.server.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public class SessionCreator {

    private SecureRandom random;

    private static SessionCreator sessionCreator;

    private SessionCreator() {
        this.random = new SecureRandom();
    }

    public static SessionCreator getInstance() {

//        return sessionCreator == null
//                ? (sessionCreator = new SessionCreator())
//                : sessionCreator;

        if (sessionCreator == null) {

            sessionCreator = new SessionCreator();
        }

        return sessionCreator;
    }

    public String generateSessionId() {

        return new BigInteger(130, this.random).toString(32);
    }
}
