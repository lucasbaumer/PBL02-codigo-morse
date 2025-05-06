package com.example;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import java.util.Scanner;

public class TreeVisualizer extends Application {
    @Override
    public void start(Stage primaryStage) {
        MorseBST morseTree = new MorseBST();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite uma palavra para codificar em Morse:");
        String inputWord = scanner.nextLine().toUpperCase();

        String morseCode = morseTree.encode(inputWord);
        System.out.println("Código Morse: " + morseCode);

        String decodedWord = morseTree.decode(morseCode);
        System.out.println("Palavra decodificada: " + decodedWord);

        int treeHeight = morseTree.calculateTreeHeight();
        int canvasWidth = Math.min((int) Math.pow(2, treeHeight) * 40, 1800);
        int canvasHeight = treeHeight * 100 + 100;

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        morseTree.drawTree(canvas);

        Group rootGroup = new Group(canvas);
        Scene scene = new Scene(rootGroup, canvasWidth, canvasHeight);
        primaryStage.setTitle("Árvore Binária de Código Morse");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}