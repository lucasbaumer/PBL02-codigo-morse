package com.example;

public class Node {

    char letter;
    String morseCode;
    Node left;
    Node right;

    public Node(char letter, String morseCode) {
        this.letter = letter;
        this.morseCode = morseCode;
        this.left = null;
        this.right = null;
    }
}
