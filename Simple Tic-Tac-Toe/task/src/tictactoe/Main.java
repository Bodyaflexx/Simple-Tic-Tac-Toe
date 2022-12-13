package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] field = new char[3][3];
        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = '_';
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

        char[] array = findArray(field);
        int index = 0;
        while (true) {
            takeCoordinates(field, index++);
            print(field);
            if (isImpossible(array, field)) {
                System.out.println("Impossible");
                break;
            } else {
                if (checkerOnWin(field) == 'X') {
                    System.out.println("X wins");
                    break;
                } else if (checkerOnWin(field) == 'O') {
                    System.out.println("O wins");
                    break;
                } else {
                    if (checkerOnDraw(array)) {
                        System.out.println("Draw");
                        break;
                    }
                }
            }
        }
    }

    private static char[] findArray(char[][] field) {
        char[] result = new char[9];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[index] = field[i][j];
                index++;
            }
        }
        return result;
    }

    private static void print(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static void takeCoordinates(char[][] field, int index) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, tell coordinates > ");
        String firstIndex = scanner.next();
        String secondIndex = scanner.next();
        if (firstIndex.matches("\\d") && secondIndex.matches("\\d")) {
            if (Integer.parseInt(firstIndex) < 1 || Integer.parseInt(firstIndex) > 3 || Integer.parseInt(secondIndex) < 1 || Integer.parseInt(secondIndex) > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                takeCoordinates(field, index);
            } else if (field[Integer.parseInt(firstIndex) - 1][Integer.parseInt(secondIndex) - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                takeCoordinates(field, index);
            } else {
                if (index % 2 == 0) {
                    field[Integer.parseInt(firstIndex) - 1][Integer.parseInt(secondIndex) - 1] = 'X';
                } else {
                    field[Integer.parseInt(firstIndex) - 1][Integer.parseInt(secondIndex) - 1] = 'O';
                }
            }
        } else {
            System.out.println("You should enter numbers!");
            takeCoordinates(field, index);
        }
    }

    private static char checkerOnWin(char[][] field) {
        for (char[] chars : field) {
            int countOfX = 0;
            int countOfO = 0;
            for (char aChar : chars) {
                if (aChar == 'X') {
                    countOfX++;
                }
                if (aChar == 'O') {
                    countOfO++;
                }
            }
            if (countOfX == 3) {
                return 'X';
            } else if (countOfO == 3) {
                return 'O';
            }
        }
        for (int i = 0; i < field.length; i++) {
            int countOfX = 0;
            int countOfO = 0;
            for (int j = 0; j < field[i].length; j++) {
                if (field[j][i] == 'X') {
                    countOfX++;
                }
                if (field[j][i] == 'O') {
                    countOfO++;
                }
            }
            if (countOfX == 3) {
                return 'X';
            } else if (countOfO == 3) {
                return 'O';
            }
        }
        int countOfX = 0;
        int countOfO = 0;
        for (int i = 0; i < field.length; i++) {
            if (field[i][i] == 'X') {
                countOfX++;
            }
            if (field[i][i] == 'O') {
                countOfO++;
            }
            if (countOfX == 3) {
                return 'X';
            } else if (countOfO == 3) {
                return 'O';
            }
        }
        countOfX = 0;
        countOfO = 0;
        for (int i = 0; i < field.length; i++) {
            if (field[i][field.length - 1 - i] == 'X') {
                countOfX++;
            }
            if (field[i][i] == 'O') {
                countOfO++;
            }
            if (countOfX == 3) {
                return 'X';
            } else if (countOfO == 3) {
                return 'O';
            }
        }
        return '_';
    }

    private static boolean isImpossible(char[] array, char[][] field) {
        int resultX = 0;
        int resultO = 0;
        for (char[] chars : field) {
            int countOfX = 0;
            int countOfO = 0;
            for (char aChar : chars) {
                if (aChar == 'X') {
                    countOfX++;
                }
                if (aChar == 'O') {
                    countOfO++;
                }
            }
            if (countOfX == 3) {
                resultX = 3;
            } else if (countOfO == 3) {
                resultO = 3;
            }
        }
        for (int i = 0; i < field.length; i++) {
            int countOfX = 0;
            int countOfO = 0;
            for (int j = 0; j < field[i].length; j++) {
                if (field[j][i] == 'X') {
                    countOfX++;
                }
                if (field[j][i] == 'O') {
                    countOfO++;
                }
            }
            if (countOfX == 3) {
                resultX = 3;
            } else if (countOfO == 3) {
                resultO = 3;
            }
        }
        if (resultO == 3 && resultX == 3) {
            return true;
        }
        int countOfXInArray = 0;
        int countOfOInArray = 0;
        for (char c : array) {
            if (c == 'X') {
                countOfXInArray++;
            }
            if (c == 'O') {
                countOfOInArray++;
            }
        }
        return Math.abs(countOfXInArray - countOfOInArray) > 1;
    }

    private static boolean checkerOnDraw(char[] array) {
        int countOfX = 0;
        int countOfO = 0;
        for (char c : array) {
            if (c == 'X') {
                countOfX++;
            }
            if (c == 'O') {
                countOfO++;
            }
        }
        return countOfX + countOfO == 9;
    }
}
