package battleship;

import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStreamReader userInput = new InputStreamReader(System.in);
        String[][] gameField = new String[11][11];
        String[][] fogOfWar = new String[11][11];
        String[][] gameField2 = new String[11][11];
        String[][] fogOfWar2 = new String[11][11];

        //populating the Game Field
        initializeGameField(gameField);
        initializeGameField(fogOfWar);
        initializeGameField(gameField2);
        initializeGameField(fogOfWar2);

        /*
        System.out.println("Test 1:");
        parseUserInput(userInput, 2);
        System.out.println("Test 2:");
        parseUserInput(userInput, 2);
        System.out.println("Test 3:");
        parseUserInput(userInput, 2);
        */

        System.out.println("Player 1, place your ships on the game field");
        showGameField(gameField);
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        promptUser(userInput, 5,  "Aircraft Carrier", gameField);
        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        promptUser(userInput, 4, "Battleship", gameField);
        System.out.println("Enter the coordinates of the Submarine (3 cells):");
        promptUser(userInput, 3,"Submarine", gameField);
        System.out.println("Enter the coordinates of the Cruiser (3 cells):");
        promptUser(userInput, 3, "Cruiser", gameField);
        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
        promptUser(userInput, 2,"Destroyer", gameField);
        System.out.println("Press Enter and pass the move to another player");
        userInput.read();

        System.out.println("Player 2, place your ships on the game field");
        showGameField(gameField2);
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        promptUser(userInput, 5,  "Aircraft Carrier", gameField2);
        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        promptUser(userInput, 4, "Battleship", gameField2);
        System.out.println("Enter the coordinates of the Submarine (3 cells):");
        promptUser(userInput, 3,"Submarine", gameField2);
        System.out.println("Enter the coordinates of the Cruiser (3 cells):");
        promptUser(userInput, 3, "Cruiser", gameField2);
        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
        promptUser(userInput, 2,"Destroyer", gameField2);
        System.out.println("Press Enter and pass the move to another player");

        System.out.println();
        System.out.println("The game starts!");
        System.out.println();
        userInput.read();
        while (!isGameOver(gameField) || !isGameOver(gameField2)) {
            showGameField(fogOfWar2);
            System.out.println("---------------------");
            showGameField(gameField);
            System.out.println("Player 1, it's your turn:");
            promptUser(userInput, gameField2, fogOfWar2);
            System.out.println("Press Enter and pass the move to another player");
            userInput.read();
            showGameField(fogOfWar);
            System.out.println("---------------------");
            showGameField(gameField2);
            System.out.println("Player 2, it's your turn:");
            promptUser(userInput,gameField,fogOfWar);
            System.out.println("Press Enter and pass the move to another player");
            userInput.read();
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    private static void initializeGameField(String[][] arr) {
        char alphabet = 'A';
        for (int i = 0; i < arr.length; i++) {
            for (int k = 0; k < arr.length; k++) {
                if (i == 0 && k == 0) {
                    arr[i][k] = " ";
                }else if (i == 0) {
                    arr[i][k] = Integer.toString(k);
                } else if (k == 0) {
                    arr[i][k] = String.valueOf(alphabet);
                    alphabet++;
                } else {
                    arr[i][k] = "~";
                }
            }
        }

    }

    private static void showGameField(String[][] arr) {
        for (String[] strings : arr) {
            for (int k = 0; k < arr.length; k++) {
                System.out.print(strings[k] + " ");
            }
            System.out.println();
        }
    }

    private static int[] parseUserInput(InputStreamReader isr, int numberOfDots) throws IOException {
        int[] coordinates = new int[numberOfDots * 2];
        int index = 0;
        int previous = 48;
        int current = isr.read();
        while (current != -1 && current != 13 && current != 10) {
            //System.out.print(current + " "); //for debugging
            switch (current) {
                case 'A':
                    coordinates[index] = 1;
                    break;
                case 'B':
                    coordinates[index] = 2;
                    break;
                case 'C':
                    coordinates[index] = 3;
                    break;
                case 'D':
                    coordinates[index] = 4;
                    break;
                case 'E':
                    coordinates[index] = 5;
                    break;
                case 'F':
                    coordinates[index] = 6;
                    break;
                case 'G':
                    coordinates[index] = 7;
                    break;
                case 'H':
                    coordinates[index] = 8;
                    break;
                case 'I':
                    coordinates[index] = 9;
                    break;
                case 'J':
                    coordinates[index] = 10;
                    break;
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '0':
                    if (previous > 47 && previous < 58) {
                        coordinates[index - 1] = Character.getNumericValue(previous) * 10 +
                                Character.getNumericValue(current);
                        index--;
                        //System.out.println("Previous: " + coordinates[index - 1]); //for debugging
                    } else {
                        coordinates[index] = Character.getNumericValue(current);
                    }
                    break;
                case ' ':
                    index--;
                    break;
                default:
                    //System.out.println("Invalid coordinates! Index: " + index);
                    //System.out.println("coordinates[]: " + Arrays.toString(coordinates));
                    break;
            }
            //System.out.println("Current: " + coordinates[index]); //for debugging
            if (current > 74) {
                coordinates[index] = 99;
            }
            index++;
            previous = current;
            current = isr.read();
        }

        if (numberOfDots == 2) {
            if (coordinates[0] > coordinates[2]) {
                int temp = coordinates[0];
                coordinates[0] = coordinates[2];
                coordinates[2] = temp;
            }
            if (coordinates[1] > coordinates[3]) {
                int temp = coordinates[1];
                coordinates[1] = coordinates[3];
                coordinates[3] = temp;
            }
        }

        //System.out.println("parseUserInput returned: " + Arrays.toString(coordinates)); //for debugging
        return coordinates;
    }

    private static String placeShip(int[] coordinates, int shipSize, String shipType, String[][] gameField) {
        //checking ship length
        if (coordinates[3] - coordinates[1] != shipSize - 1 && coordinates[2] - coordinates[0] != shipSize - 1 &&
                coordinates[1] - coordinates[3] != shipSize - 1 && coordinates[0] - coordinates[2] != shipSize - 1) {
            return "Error! Wrong length of the " + shipType + "! Try again:";
        }
        //checking is being placed on a single line/column
        if (coordinates[0] != coordinates[2] && coordinates[1] != coordinates[3]) {
            return "Error! Wrong ship location! Try again:";
        }
        //checking neighboring space
        int vMin = Math.min(coordinates[0], coordinates[2]);
        vMin--;
        vMin = Math.max(vMin, 1);
        int vMax = Math.max(coordinates[0], coordinates[2]);
        vMax++;
        vMax = Math.min(vMax, 10);
        int hMin = Math.min(coordinates[1], coordinates[3]);
        hMin--;
        hMin = Math.max(hMin, 1);
        int hMax = Math.max(coordinates[1], coordinates[3]);
        hMax++;
        hMax = Math.min(hMax, 10);

        for ( ; vMin <= vMax; vMin++) {
            for (int k = hMin; k <= hMax; k++) {
                if ("O".equals(gameField[vMin][hMin])) {
                    return "Error! You placed it too close to another one. Try again:";
                }
                //gameField[vMin][k] = "X"; //for debugging
            }
        }

        //placing ship horizontally
        if (coordinates[0] == coordinates[2] && coordinates[3] - coordinates[1] == shipSize - 1) {
            for (; coordinates[1] <= coordinates[3]; coordinates[1]++) {
                gameField[coordinates[0]][coordinates[1]] = "O";
            }
            return "";
        //placing ship vertically
        } else if (coordinates[1] == coordinates[3] && coordinates[2] - coordinates[0] == shipSize - 1) {
            for (; coordinates[0] <= coordinates[2]; coordinates[0]++) {
                gameField[coordinates[0]][coordinates[1]] = "O";
            }
            return "";
        }
        return "UNKNOWN!";
    }

    private static void promptUser(InputStreamReader isr, int shipSize, String shipName, String[][] gameField) throws IOException {
        String result;
        do {
            int[] coordinates = parseUserInput(isr, 2);
            result = placeShip(coordinates, shipSize, shipName, gameField);
            System.out.println(result);
        } while (!"".equals(result));
        showGameField(gameField);
    }

    private static void promptUser(InputStreamReader isr, String[][] gameField, String[][] fogOfWar) throws IOException {
        String result;
        do {
            int[] coordinates = parseUserInput(isr, 1);
            result = takeShot(coordinates, gameField, fogOfWar);
            System.out.println(result);
        } while (!"".equals(result));
    }

    private static String takeShot(int[] coordinates, String[][] gameField, String[][] fogOfWar) {
        if (coordinates[0] < 1 || coordinates[0] > 10 || coordinates[1] < 1 || coordinates[1] > 10) {
            return "Error! You entered the wrong coordinates! Try again:";
        } else if ("O".equals(gameField[coordinates[0]] [coordinates[1]]) ||
                "X".equals(gameField[coordinates[0]] [coordinates[1]])) {
            fogOfWar[coordinates[0]] [coordinates[1]] = "X";
            gameField[coordinates[0]] [coordinates[1]] = "X";
            if (isShipSunk(coordinates, gameField)) {
                if (isGameOver(gameField)) {
                    System.out.println("You sank the last ship. You won. Congratulations!");
                    return "";
                } else {
                    showGameField(fogOfWar);
                    System.out.println("You sank a ship! Specify a new target:");
                    return "";
                }
            } else {
                showGameField(fogOfWar);
                System.out.println("You hit a ship! Try again:");
                return "";
            }
        } else {
            fogOfWar[coordinates[0]] [coordinates[1]] = "M";
            gameField[coordinates[0]] [coordinates[1]] = "M";
            showGameField(fogOfWar);
            System.out.println("You missed! Try again:");
            return "";
        }
    }

    private static boolean isGameOver(String[][] gameField) {
        for (int i = 0; i <= 10; i++) {
            for (int k = 0; k <= 10; k++) {
                if ("O".equals(gameField[i][k])) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isShipSunk(int[] coordinates, String[][] gameField) {
        boolean checkUp = false;
        boolean checkDown = false;
        boolean checkLeft = false;
        boolean checkRight = false;

        if (coordinates[0] != 1) {
            checkUp = "O".equals(gameField[coordinates[0] - 1][coordinates[1]]);
        }
        if (coordinates[0] != 10) {
            checkDown = "O".equals(gameField[coordinates[0] + 1][coordinates[1]]);
        }
        if (coordinates[1] != 1) {
            checkLeft = "O".equals(gameField[coordinates[0]][coordinates[1] - 1]);
        }
        if (coordinates[1] != 10) {
            checkRight = "O".equals(gameField[coordinates[0]][coordinates[1] + 1]);
        }
        return !checkUp && !checkDown && !checkLeft && !checkRight;
    }
}
