package com.IAD;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class ChooserForm extends FormLayout {
    private RadioButtonGroup<String> x_chooser = new RadioButtonGroup<>("Choose the X value:");
    private TextField y_chooser = new TextField();
    private RadioButtonGroup<String> r_chooser = new RadioButtonGroup<>("Choose the R value:");
    private Button b_checkHit = new Button("I'm lucky!");

    private Binder<Hit> binder;

    private SecurePage myUI;

    public Binder<Hit> getBinder() {
        return binder;
    }
    public void setBinder(Binder<Hit> binder) {
        this.binder = binder;
    }
    public RadioButtonGroup<String> getX_chooser() {
        return x_chooser;
    }
    public void setX_chooser(RadioButtonGroup<String> x_chooser) {
        this.x_chooser = x_chooser;
    }
    public TextField getY_chooser() {
        return y_chooser;
    }
    public void setY_chooser(TextField y_chooser) {
        this.y_chooser = y_chooser;
    }
    public RadioButtonGroup<String> getR_chooser() {
        return r_chooser;
    }
    public void setR_chooser(RadioButtonGroup<String> r_chooser) {
        this.r_chooser = r_chooser;
    }
    public Button getB_checkHit() {
        return b_checkHit;
    }
    public void setB_checkHit(Button b_checkHit) {
        this.b_checkHit = b_checkHit;
    }

    public ChooserForm(SecurePage myUI) {
        this.myUI = myUI;

        setSizeUndefined();

        x_chooser.setItems("-3","-2","-1","0","1","2","3","4","5");
        x_chooser.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);

        y_chooser.setCaption("Write the Y value:");
        y_chooser.setPlaceholder("from -3 to 5");

        r_chooser.setItems("-3","-2","-1","0","1","2","3","4","5");
        r_chooser.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);

        binder = new Binder<>(Hit.class);
        binder.forField(x_chooser)
                .asRequired("Mandatory field")
                .withConverter(new StringToIntegerConverter("Do u a hacker?"))
                .bind(Hit::getX, Hit::setX);
        binder.forField(y_chooser)
                .asRequired("You should fill this field")
                .withValidator(str -> str.indexOf('.') == -1, "You should use como for float numbers")
                .withConverter(new StringToDoubleConverter("Input must be a number"))
                .withValidator(dbl -> dbl > -3, "The number must be greater than -3")
                .withValidator(dbl -> dbl < 5, "The number must be lower than 5")
                .bind(Hit::getY, Hit::setY);
        binder.forField(r_chooser)
                .asRequired("Mandatory field")
                .withConverter(new StringToIntegerConverter("Do u a hacker?"))
                .bind(Hit::getR, Hit::setR);

        addComponents(x_chooser, y_chooser, r_chooser, b_checkHit);


    }
}
