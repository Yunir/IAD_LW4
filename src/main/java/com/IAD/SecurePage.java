package com.IAD;

import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import org.apache.deltaspike.core.api.resourceloader.InjectableResource;
import org.vaadin.hezamu.canvas.Canvas;
import sun.applet.Main;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Optional;

import static elemental.dom.Document.Events.UI;

@CDIView("secure")
public class SecurePage extends VerticalLayout implements View {
    @Inject
    private CDIViewProvider viewProvider;

    MainBean mb;

    private Canvas canvas;
    private static final long serialVersionUID = 1L;
    private Label secure;
    private Label currentUser;
    private Label title;
    private Button otherSecure;
    private Button logout;
    public static final String NAME = "Secure";
    public SecurePage() throws NamingException {
        /*mb.simple();*/
        Context compEnv = (Context) new InitialContext().lookup("java:comp/env");
        mb = (MainBean) new InitialContext().lookup("java:global/IAD_Vaadin-1.3-SNAPSHOT/MainBean!com.IAD.MainBean");
        title = new Label("Web-service developed by Promotorov Vlad and Salimzyanov Yunir from P3211.\nVariant 482");
        canvas = new Canvas();
        canvas.setWidth("300px");
        canvas.setHeight("300px");
        canvas.beginPath();
        canvas.arc(150, 150, 50, Math.PI, 3*Math.PI/2, true);
        canvas.lineTo(150,150);
        canvas.closePath();
        canvas.rect(150, 100, 100, 50);
        canvas.setFillStyle(59, 164, 199);
        canvas.fill();
        canvas.beginPath();
        canvas.moveTo(150,150);
        canvas.lineTo(150, 200);
        canvas.lineTo(200, 150);
        canvas.lineTo(150, 150);
        canvas.closePath();
        canvas.fill();
        /*canvas.restoreContext();*/

        canvas.beginPath();

        //Ox
        canvas.moveTo(30, 150);
        canvas.lineTo(270, 150);
        canvas.lineTo(260, 140);
        canvas.moveTo(270, 150);
        canvas.lineTo(260, 160);

        //Oy
        canvas.moveTo(150, 30);
        canvas.lineTo(140, 40);
        canvas.moveTo(150, 30);
        canvas.lineTo(160, 40);
        canvas.moveTo(150, 30);
        canvas.lineTo(150, 270);
        canvas.moveTo(30, 150);
        canvas.closePath();
        canvas.stroke();


        /*Implementation of choosers*/
        ChooserForm chooserForm = new ChooserForm(this);
        HorizontalLayout body = new HorizontalLayout(canvas, chooserForm);
        addComponents(title, body);

        chooserForm.getB_checkHit().addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {
                if (!chooserForm.getBinder().validate().isOk())
                    Notification.show("Alert:", "You should select all the fields", Notification.Type.WARNING_MESSAGE);
                //Notification.show("Alert:", "sss", Notification.Type.WARNING_MESSAGE);
                mb.simple();
            }
        });


        otherSecure = new Button("OtherSecure");
        otherSecure.addClickListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                Page.getCurrent().setUriFragment("!"+OtherSecurePage.NAME);
            }
        });

        logout = new Button("Logout");
        logout.addClickListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                getUI().getNavigator().removeView(SecurePage.NAME);
                getUI().getNavigator().removeView(OtherSecurePage.NAME);
                VaadinSession.getCurrent().setAttribute("user", null);
                Page.getCurrent().setUriFragment("");
            }
        });

        secure = new Label("secure");
        currentUser = new Label("Current User");
        addComponent(secure);
        addComponent(currentUser);
        addComponent(otherSecure);
        addComponent(logout);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        currentUser.setCaption("Current user : " + VaadinSession.getCurrent().getAttribute("user").toString());
    }

}