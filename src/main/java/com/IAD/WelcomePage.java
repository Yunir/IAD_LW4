package com.IAD;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("AppWidgetset")
/*@CDIUI("welcome")*/
public class WelcomePage extends UI {
    public static Authentication AUTH;
    @Inject
    private CDIViewProvider viewProvider;
    /*@Inject
    MainBean mb;*/


    private void router(String route){
        Notification.show(route);
        if(getSession().getAttribute("user") != null){
            getNavigator().addView(SecurePage.NAME, SecurePage.class);
            getNavigator().addView(OtherSecurePage.NAME, OtherSecurePage.class);
            if(route.equals("!OtherSecure")){
                getNavigator().navigateTo(OtherSecurePage.NAME);
            }else{
                getNavigator().navigateTo(SecurePage.NAME);
            }
        }else{
            getNavigator().navigateTo(LoginForm.NAME);
        }
    }



    @Override
    protected void init(VaadinRequest vaadinRequest) {

        AUTH = new Authentication();
        new Navigator(this, this);

        getNavigator().addView(LoginForm.NAME, LoginForm.class);
        getNavigator().setErrorView(LoginForm.class);
        /*getNavigator().addProvider(viewProvider);*/
        //mb.simple();
        Page.getCurrent().addUriFragmentChangedListener(new Page.UriFragmentChangedListener() {

            @Override
            public void uriFragmentChanged(Page.UriFragmentChangedEvent event) {
                router(event.getUriFragment());
            }
        });


        router("");
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = WelcomePage.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
