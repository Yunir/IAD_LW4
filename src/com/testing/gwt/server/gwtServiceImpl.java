package com.testing.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.testing.gwt.client.gwtService;

public class gwtServiceImpl extends RemoteServiceServlet implements gwtService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}