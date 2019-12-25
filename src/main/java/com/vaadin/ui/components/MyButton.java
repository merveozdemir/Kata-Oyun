package com.vaadin.ui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Random;

public class MyButton extends Button {
    Random rand = new Random();


    public MyButton() {
        super();

        this.setWidth("50px");
        this.setHeight("50px");
        this.setIcon(FontAwesome.CIRCLE_O);

        int buttonState = rand.nextInt(2);
        setData(buttonState);
        setDescription(String.valueOf(buttonState));

    }

    void setButtonColor(){
        if(getData().equals(1)){
            addStyleName(ValoTheme.BUTTON_FRIENDLY);
        }
        else{
            addStyleName(ValoTheme.BUTTON_DANGER);
        }
    }

    @Override
    public void click() {
        super.click();
    }
}
