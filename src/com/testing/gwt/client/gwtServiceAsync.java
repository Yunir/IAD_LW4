package com.testing.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface gwtServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
