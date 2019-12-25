package com.vaadin.ui.components;

import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;


public class MyHeader extends Label {

    public MyHeader() {
        setValue("YAŞAM VAR MI OYUNU");
        addStyleName(ValoTheme.LABEL_H2);
        addStyleName(ValoTheme.LABEL_COLORED);
        addStyleName(ValoTheme.TEXTAREA_ALIGN_CENTER);
    }
}
