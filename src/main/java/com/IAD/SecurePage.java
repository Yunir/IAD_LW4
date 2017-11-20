package com.IAD;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.hezamu.canvas.Canvas;

public class SecurePage extends VerticalLayout implements View {
    private Canvas canvas;
    private static final long serialVersionUID = 1L;
    private Label secure;
    private Label currentUser;
    private Button otherSecure;
    private Button logout;
    public static final String NAME = "Secure";

    public SecurePage() {

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
        addComponent(body);

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