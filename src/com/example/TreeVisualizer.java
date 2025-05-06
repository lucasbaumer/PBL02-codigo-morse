package com.example;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class TreeVisualizer extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Visualizador de Árvore Binária");

        MorseBST bst = new MorseBST();

        bst.insert('A', ".-");
        bst.insert('B', "-...");
        bst.insert('C', "-.-.");
        bst.insert('D', "-..");
        bst.insert('E', ".");
        bst.insert('F', "..-.");
        bst.insert('G', "--.");
        bst.insert('H', "....");
        bst.insert('I', "..");
        bst.insert('J', ".---");
        bst.insert('K', "-.-");
        bst.insert('L', ".-..");
        bst.insert('M', "--");
        bst.insert('N', "-.");
        bst.insert('O', "---");
        bst.insert('P', ".--.");
        bst.insert('Q', "--.-");
        bst.insert('R', ".-.");
        bst.insert('S', "...");
        bst.insert('T', "-");
        bst.insert('U', "..-");
        bst.insert('V', "...-");
        bst.insert('W', ".--");
        bst.insert('X', "-..-");
        bst.insert('Y', "-.--");
        bst.insert('Z', "--..");
        bst.insert('0', "-----");
        bst.insert('1', ".----");
        bst.insert('2', "..---");
        bst.insert('3', "...--");
        bst.insert('4', "....-");
        bst.insert('5', ".....");
        bst.insert('6', "-....");
        bst.insert('7', "--...");
        bst.insert('8', "---..");
        bst.insert('9', "----.");

        int height = bst.getHeight();
        int canvasHeight = 100 + height * 100;
        int canvasWidth = Math.min((int) Math.pow(2, height) * 40, 1800);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        bst.drawTree(canvas);

        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, canvasWidth, canvasHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
