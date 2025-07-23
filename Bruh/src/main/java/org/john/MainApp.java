package org.john;

import javafx.application.Application;
import org.john.ui.GUI;

import java.io.IOException;

public class MainApp {
    public static void main(String[] args) throws IOException {
        Application.launch(GUI.class, args);
    }
}
