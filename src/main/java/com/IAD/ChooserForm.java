package com.IAD;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class ChooserForm extends FormLayout {
    RadioButtonGroup<String> x_chooser = new RadioButtonGroup<>("Choose the X value:");
    TextField y_chooser = new TextField();

    RadioButtonGroup<String> r_chooser = new RadioButtonGroup<>("Choose the R value:");

    Button b_checkHit = new Button("I'm lucky!");

    private MyUI myUI;

    public ChooserForm(MyUI myUI) {
        this.myUI = myUI;

        setSizeUndefined();

        x_chooser.setItems("-3","-2","-1","0","1","2","3","4","5");
        x_chooser.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);

        y_chooser.setCaption("Write the Y value:");
        y_chooser.setPlaceholder("from -3 to 5");

        r_chooser.setItems("-3","-2","-1","0","1","2","3","4","5");
        r_chooser.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);

        addComponents(x_chooser, y_chooser, r_chooser, b_checkHit);
    }
}
