package com.IAD;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class ChooserForm extends FormLayout {
    private RadioButtonGroup<String> x_chooser = new RadioButtonGroup<>("Choose the X value:");
    private TextField y_chooser = new TextField();
    private RadioButtonGroup<String> r_chooser = new RadioButtonGroup<>("Choose the R value:");
    private Button b_checkHit = new Button("I'm lucky!");

    private SecurePage myUI;

    public ChooserForm(SecurePage myUI) {
        this.myUI = myUI;

        setSizeUndefined();

        x_chooser.setItems("-3","-2","-1","0","1","2","3","4","5");
        x_chooser.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);

        y_chooser.setCaption("Write the Y value:");
        y_chooser.setPlaceholder("from -3 to 5");

        r_chooser.setItems("-3","-2","-1","0","1","2","3","4","5");
        r_chooser.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);

        new Binder<Hit>().forField(y_chooser)
                .withValidator(str -> str.length() != 0, "You should fill this field")
                .withValidator(str -> str.indexOf('.') == -1, "You should use como for float numbers")
                .withConverter(new StringToDoubleConverter("Input must be a number"))
                .withValidator(dbl -> dbl > -3, "The number must be greater than -3")
                .withValidator(dbl -> dbl < 5, "The number must be lower than 5")
                .bind(Hit::getY, Hit::setY);

        addComponents(x_chooser, y_chooser, r_chooser, b_checkHit);

    }
}
