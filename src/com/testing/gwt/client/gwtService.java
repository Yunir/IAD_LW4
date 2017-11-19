package com.testing.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwtService")
public interface gwtService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use gwtService.App.getInstance() to access static instance of gwtServiceAsync
     */
    public static class App {
        private static gwtServiceAsync ourInstance = GWT.create(gwtService.class);

        public static synchronized gwtServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
