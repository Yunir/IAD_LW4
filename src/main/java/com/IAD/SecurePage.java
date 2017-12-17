package com.IAD;

import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import javafx.util.Pair;
import org.vaadin.hezamu.canvas.Canvas;

import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@CDIView("secure")
public class SecurePage extends VerticalLayout implements View {
    @Inject
    private CDIViewProvider viewProvider;
    private Map<Double, ArrayList<Double>> xy_values;
    MainBean mb;
    private int R = 0;
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
         xy_values = new HashMap<Double, ArrayList<Double>>();
        Context compEnv = (Context) new InitialContext().lookup("java:comp/env");
        mb = (MainBean) new InitialContext().lookup("java:global/IAD_Vaadin-1.3-SNAPSHOT/MainBean!com.IAD.MainBean");
        title = new Label("Web-service developed by Promotorov Vlad and Salimzyanov Yunir from P3211.\nVariant 482");

        Label c = new Label("Here be text");
        c.addStyleName("myresponsive");
        addComponent(c);



        // Enable Responsive CSS selectors for the component
        Responsive.makeResponsive(c);

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
        canvas.stroke();
        canvas.closePath();
        canvas.beginPath();
        canvas.moveTo(150, 150);
        canvas.stroke();
        canvas.closePath();
        canvas.addMouseDownListener(new Canvas.CanvasMouseDownListener() {
            @Override
            public void onMouseDown(MouseEventDetails mouseEvent) {
                if(getR() == 0) {
                    Notification.show("Alert:", "You should select the R value", Notification.Type.WARNING_MESSAGE);
                    return;
                }
                double x = mouseEvent.getRelativeX();
                double y = mouseEvent.getRelativeY();
                addValues(x, y);
                x -= 150;
                y -= 150;
                y *= -1;
                x = x/100*5;
                y = y/100*5;
                System.out.println("Mouse clicked to "
                        + x + ","
                        + y
                );
                canvas.setFillStyle(checkArea(x, y, getR())?"white":"gray");
                canvas.fillRect(mouseEvent.getRelativeX()-2, mouseEvent.getRelativeY()-2, 4, 4);
                mb.x_values.add(x);
                mb.y_values.add(y);
                mb.r_values.add(getR());
                mb.res_values.add(checkArea(x, y, getR()));
                mb.saveDataToDB(x, y, getR(), checkArea(x, y, getR()));
            }
        });

        /*Implementation of choosers*/
        ChooserForm chooserForm = new ChooserForm(this);
        chooserForm.getR_chooser().addSelectionListener(singleSelectionEvent -> {
                    int mult = Integer.parseInt(chooserForm.getR_chooser().getSelectedItem().orElse("0"));
                    setR(mult);
                    System.out.println(getR());
                    canvas.clear();

                    canvas.beginPath();
                    canvas.arc(150, 150, 10*mult, Math.PI, 3*Math.PI/2, true);
                    canvas.lineTo(150,150);
                    canvas.closePath();
                    canvas.rect(150, 100+10*(5-mult), 20*mult, 10*mult);
                    canvas.setFillStyle(59, 164, 199);
                    canvas.fill();
                    canvas.beginPath();
                    canvas.moveTo(150,150);
                    canvas.lineTo(150, 200-10*(5-mult));
                    canvas.lineTo(200-10*(5-mult), 150);
                    canvas.lineTo(150, 150);
                    canvas.closePath();
                    canvas.setFillStyle(59, 164, 199);
                    canvas.fill();

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
                    canvas.stroke();
                    canvas.closePath();

                    Iterator it = xy_values.keySet().iterator();
                    ArrayList<Double> tempList = null;

                    while (it.hasNext()) {
                        Double x = (Double) it.next();
                        tempList = xy_values.get(x);
                        if (tempList != null) {
                            for (Double y: tempList) {
                                double sx = x, sy = y;
                                x -= 150;
                                y -= 150;
                                y *= -1;
                                x = x/100*5;
                                y = y/100*5;
                                canvas.setFillStyle(checkArea(x, y, getR())?"white":"gray");
                                canvas.fillRect(sx-2, sy-2, 4, 4);
                            }
                        }
                    }


                    for (int i = 0; i < mb.x_values.size(); i++) {
                        addComponent(new Label("x = " + mb.x_values.get(i) + "; y = " + mb.y_values.get(i)));
                    }
                }
            );
        HorizontalLayout body = new HorizontalLayout(canvas, chooserForm);
        addComponents(title, body);

        chooserForm.getB_checkHit().addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {
                double x, y, r;
                if (!chooserForm.getBinder().validate().isOk()) {
                    Notification.show("Alert:", "You should select all the fields", Notification.Type.WARNING_MESSAGE);
                } else {
                    x = Double.parseDouble(chooserForm.getX_chooser().getValue());
                    y = Double.parseDouble(chooserForm.getY_chooser().getValue());
                    r = Double.parseDouble(chooserForm.getR_chooser().getValue());
                    mb.saveDataToDB(x, y, r, checkArea(x, y, r));
                    canvas.setFillStyle(checkArea(x, y, getR())?"white":"gray");
                    x = x*20;
                    y = -1*y*20;
                    x += 150;
                    y += 150;
                    addValues(x, y);
                    canvas.fillRect(x-2, y-2, 4, 4);

                }
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

    private boolean checkArea(double x, double y, double r){
        if (r == 0) return false;
        System.out.println("Get values " + x + ", " + y + ", " + r);
        if((x >= 0 && y >= 0) && (x <= r) && (y <= (r/2))){
            System.out.println("rect");
            return true;
        } else if(x >= 0 && y <= 0 && y >= (x-(r/2))){
            System.out.println("triangle");
            return true;
        } else if (x <= 0 && y >= 0 && (r/2) >= Math.sqrt(y*y+x*x)){
            System.out.println("circle");
            return true;
        } else {
            System.out.println("Not hitted");
            return false;
        }
    }

    private void addValues(Double key, Double value) {
        ArrayList tempList = null;
        if (xy_values.containsKey(key)) {
            tempList = xy_values.get(key);
            if(tempList == null)
                tempList = new ArrayList();
            tempList.add(value);
        } else {
            tempList = new ArrayList();
            tempList.add(value);
        }
        xy_values.put(key,tempList);
    }

    public void setR(int r) {
        R = r;
    }
    public int getR() {
        return R;
    }
}