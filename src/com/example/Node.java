package com.example;

public class Node {
    public char character;
    public Node left;
    public Node right;

    public Node(char character) {
        this.character = character;
        this.left = null;
        this.right = null;
    }
}