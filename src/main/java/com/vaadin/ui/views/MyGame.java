package com.vaadin.ui.views;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.MyButton;
import com.vaadin.ui.components.MyHeader;
import com.vaadin.ui.themes.ValoTheme;

import javax.xml.bind.ValidationEvent;


public class MyGame extends VerticalLayout {
    GridLayout myGrid;
    MyHeader myHeader;
    MyButton[][] myButtons;
    int rowSize;
    int columnSize;

    public MyGame(int columnSize, int rowSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;

        setMargin(true);

        buildMyHeader();

        buildMyGridLayout();

        createMyButtons();
    }

    private void buildMyHeader() {
        myHeader = new MyHeader();
        addComponent(myHeader);

    }

    private void buildMyGridLayout() {
        myGrid = new GridLayout(columnSize, rowSize);
        myGrid.setSpacing(true);
        addComponent(myGrid);
        setComponentAlignment(myGrid, Alignment.MIDDLE_CENTER);
    }

    private void createMyButtons() {
        myButtons = new MyButton[columnSize][rowSize];

        for (int j = 0; j < rowSize; j++) {
            for (int i = 0; i < columnSize; i++) {
                MyButton myButton = new MyButton();
                int finalI = i;
                int finalJ = j;
                myButton.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        System.out.println(finalI);
                        System.out.println(finalJ);
                        createMyGame(finalI, finalJ);
                    }
                });
                myButtons[i][j] = myButton;
                myGrid.addComponent(myButton, i, j);
            }
        }
    }

    void createMyGame(int columnIndex, int rowIndex) {
        int aliveCounter = 0;
        MyButton myCurrentButton = myButtons[columnIndex][rowIndex];

        if (myCurrentButton.getData().equals(0)) {
            myCurrentButton.addStyleName(ValoTheme.BUTTON_DANGER);
        } else {
            int tempRowIndex = rowIndex;
            int tempColumnIndex = columnIndex;

            if (rowIndex <= 0 || columnIndex <= 0 || rowIndex >= rowSize - 1 || columnIndex >= columnSize - 1) {
                if (rowIndex <= 0) {
                    if (columnIndex <= 0) {
                        aliveCounter = koseKarsilastir(columnIndex, rowIndex, aliveCounter);
                    } else if (columnIndex >= columnSize - 1) {
                        columnIndex--;
                        aliveCounter = koseKarsilastir(columnIndex, rowIndex, aliveCounter);
                    } else {
                        columnIndex--;
                        aliveCounter = yatayKenarKarsilastir(columnIndex, rowIndex, aliveCounter);
                    }
                } else if (rowIndex >= rowSize - 1) {
                    rowIndex--;
                    if (columnIndex <= 0) {
                        aliveCounter = koseKarsilastir(columnIndex, rowIndex, aliveCounter);
                    } else if (columnIndex >= columnSize - 1) {
                        columnIndex--;
                        aliveCounter = koseKarsilastir(columnIndex, rowIndex, aliveCounter);
                    } else {
                        columnIndex--;
                        aliveCounter = yatayKenarKarsilastir(columnIndex, rowIndex, aliveCounter);
                    }
                } else if (columnIndex <= 0) {
                    rowIndex--;
                    aliveCounter = dikeyKenarKarsilastir(columnIndex, rowIndex, aliveCounter);
                } else if (columnIndex >= columnSize - 1) {
                    rowIndex--;
                    columnIndex--;
                    aliveCounter = dikeyKenarKarsilastir(columnIndex, rowIndex, aliveCounter);
                }

            } else {
                aliveCounter = ortaBolumKarsilastir(columnIndex, rowIndex, aliveCounter, tempRowIndex, tempColumnIndex);
            }
        }

        updateButtonState(aliveCounter, myCurrentButton);
    }

    private void updateButtonState(int aliveCounter, MyButton myCurrentButton) {
        if (aliveCounter < 4) {
            myCurrentButton.setData(0);
            myCurrentButton.setDescription(String.valueOf(myCurrentButton.getData()));
            myCurrentButton.addStyleName(ValoTheme.BUTTON_DANGER);
        } else {
            myCurrentButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        }
    }

    private int ortaBolumKarsilastir(int columnIndex, int rowIndex, int aliveCounter, int tempRowIndex, int tempColumnIndex) {
        for (int j = rowIndex - 1; j < tempRowIndex + 2; j++) {
            for (int i = columnIndex - 1; i < tempColumnIndex + 2; i++) {
                if (myButtons[i][j].getData().equals(1)) {
                    aliveCounter++;
                }
            }
        }
        return aliveCounter;
    }

    private int koseKarsilastir(int columnIndex, int rowIndex, int aliveCounter) {
        for (int j = rowIndex; j < rowIndex + 1; j++) {
            for (int i = columnIndex; i < columnIndex + 1; i++) {
                if (myButtons[i][j].getData().equals(1)) {
                    aliveCounter++;
                }
            }
        }
        return aliveCounter;
    }

    private int yatayKenarKarsilastir(int columnIndex, int rowIndex, int aliveCounter) {
        for (int j = rowIndex; j < rowIndex + 2; j++) {
            for (int i = columnIndex; i < columnIndex + 3; i++) {
                if (myButtons[i][j].getData().equals(1)) {
                    aliveCounter++;
                }
            }
        }
        return aliveCounter;
    }

    private int dikeyKenarKarsilastir(int columnIndex, int rowIndex, int aliveCounter) {
        for (int j = rowIndex; j < rowIndex + 3; j++) {
            for (int i = columnIndex; i < columnIndex + 2; i++) {
                if (myButtons[i][j].getData().equals(1)) {
                    aliveCounter++;
                }
            }
        }
        return aliveCounter;
    }


}





