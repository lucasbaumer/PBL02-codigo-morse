package com.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MorseBST {

    private Node root;

    public MorseBST() {
        root = null;
    }

    // insere uma letra e o código Morse
    public void insert(char letter, String morseCode) {
        root = insertRec(root, letter, morseCode, 0);
    }

    // Método recursivo para inserir no BST
    private Node insertRec(Node root, char letter, String morseCode, int index) {
        // ve se percorreu todo o nó
        if (index >= morseCode.length()) {
            if (root == null) {
                root = new Node(letter, morseCode);
            } else {
                root.letter = letter;
            }
            return root;
        }

        // Se o nó atual é nulo, cria um nó intermediário com letra ' ' (espaço)
        if (root == null) {
            root = new Node(' ', "");
        }

        char morseChar = morseCode.charAt(index);
        if (morseChar == '.') {
            root.left = insertRec(root.left, letter, morseCode, index + 1);
        } else if (morseChar == '-') {
            root.right = insertRec(root.right, letter, morseCode, index + 1);
        }
        return root;
    }

    // Calcula a altura da árvore
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    // desenha a árvore
    public void drawTree(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        // Começa o desenho da árvore na raiz
        drawNode(gc, root, canvas.getWidth() / 2, 40, canvas.getWidth() / 6, 1);
    }

    // Desenha nó e os filhos
    private void drawNode(GraphicsContext gc, Node node, double x, double y, double xOffset, int level) {
        if (node == null) {
            return;
        }

        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - 15, y - 15, 30, 30); // Desenha o círculo com raio 15
        gc.strokeText(String.valueOf(node.letter == ' ' ? ' ' : node.letter), x - 5, y + 5);

        if (node.left != null) {
            double newX = x - xOffset;
            double newY = y + 100;
            gc.strokeLine(x, y + 15, newX, newY - 15); // Linha para filho à esquerda
            drawNode(gc, node.left, newX, newY, xOffset / 2, level + 1);
        }

        if (node.right != null) {
            double newX = x + xOffset;
            double newY = y + 100;
            gc.strokeLine(x, y + 15, newX, newY - 15); // Linha para filho à direita
            drawNode(gc, node.right, newX, newY, xOffset / 2, level + 1);
        }
    }

    // decodifica o código Morse em uma palavra
    public String decode(String morseCode) {
        StringBuilder decodedMessage = new StringBuilder();
        Node currentNode = root;

        for (char morseChar : morseCode.toCharArray()) {
            if (morseChar == '.') {
                currentNode = currentNode.left;  // Mover para a esquerda
            } else if (morseChar == '-') {
                currentNode = currentNode.right; // Mover para a direita
            }

            // Se alcançamos um nó final (letra), adiciona a letra e reinicia a busca para o próximo caractere Morse
            if (currentNode != null && currentNode.left == null && currentNode.right == null) {
                decodedMessage.append(currentNode.letter);
                currentNode = root;  // Reinicia a busca a partir da raiz
            }
        }

        return decodedMessage.toString();
    }
}
