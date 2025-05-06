package com.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.Map;

public class MorseBST {
    private Node root;
    private Map<Character, String> charToMorseMap;

    public MorseBST() {
        root = new Node(' ');
        charToMorseMap = new HashMap<>();
        initializeMorseTree();
    }

    private void initializeMorseTree() {
        // Alfabeto Morse completo
        addNode('E', ".");
        addNode('T', "-");
        addNode('I', "..");
        addNode('A', ".-");
        addNode('N', "-.");
        addNode('M', "--");
        addNode('S', "...");
        addNode('U', "..-");
        addNode('R', ".-.");
        addNode('W', ".--");
        addNode('D', "-..");
        addNode('K', "-.-");
        addNode('G', "--.");
        addNode('O', "---");
        addNode('H', "....");
        addNode('V', "...-");
        addNode('F', "..-.");
        addNode('L', ".-..");
        addNode('P', ".--.");
        addNode('J', ".---");
        addNode('B', "-...");
        addNode('X', "-..-");
        addNode('C', "-.-.");
        addNode('Y', "-.--");
        addNode('Z', "--..");
        addNode('Q', "--.-");
        addNode('5', ".....");
        addNode('4', "....-");
        addNode('3', "...--");
        addNode('2', "..---");
        addNode('1', ".----");
        addNode('6', "-....");
        addNode('7', "--...");
        addNode('8', "---..");
        addNode('9', "----.");
        addNode('0', "-----");
        addNode(' ', "/");
    }

    public void addNode(char character, String morseCode) {
        Node current = root;
        for (int i = 0; i < morseCode.length(); i++) {
            if (morseCode.charAt(i) == '.') {
                if (current.left == null) {
                    current.left = new Node(' ');
                }
                current = current.left;
            } else if (morseCode.charAt(i) == '-') {
                if (current.right == null) {
                    current.right = new Node(' ');
                }
                current = current.right;
            }
        }
        current.character = character;
        charToMorseMap.put(character, morseCode);
    }

    public String encode(String word) {
        StringBuilder morse = new StringBuilder();
        for (char c : word.toUpperCase().toCharArray()) {
            if (charToMorseMap.containsKey(c)) {
                morse.append(charToMorseMap.get(c)).append(" ");
            } else if (c == ' ') {
                morse.append("/ ");
            }
        }
        return morse.toString().trim();
    }

    public String decode(String morseCode) {
        StringBuilder decoded = new StringBuilder();
        String[] codes = morseCode.split(" ");
        for (String code : codes) {
            if (code.equals("/")) {
                decoded.append(" ");
            } else {
                decoded.append(findCharacter(root, code, 0));
            }
        }
        return decoded.toString();
    }

    private char findCharacter(Node node, String code, int index) {
        if (index == code.length()) {
            return node.character;
        }
        char direction = code.charAt(index);
        if (direction == '.' && node.left != null) {
            return findCharacter(node.left, code, index + 1);
        } else if (direction == '-' && node.right != null) {
            return findCharacter(node.right, code, index + 1);
        }
        return ' ';
    }

    public void drawTree(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        drawNode(gc, root, canvas.getWidth() / 2, 50, canvas.getWidth() / 4);
    }

    private void drawNode(GraphicsContext gc, Node node, double x, double y, double xOffset) {
        if (node == null) return;

        gc.setFill(Color.WHITE);
        gc.fillOval(x - 15, y - 15, 30, 30);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - 15, y - 15, 30, 30);
        gc.strokeText(String.valueOf(node.character), x - 5, y + 5);

        if (node.left != null) {
            gc.strokeLine(x, y + 15, x - xOffset, y + 65);
            drawNode(gc, node.left, x - xOffset, y + 80, xOffset / 2);
        }
        if (node.right != null) {
            gc.strokeLine(x, y + 15, x + xOffset, y + 65);
            drawNode(gc, node.right, x + xOffset, y + 80, xOffset / 2);
        }
    }

    public int calculateTreeHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(calculateHeight(node.left), calculateHeight(node.right));
    }
}