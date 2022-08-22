import java.io.IOException;
import java.util.*;

public class Main {

    protected static String input;
    protected static boolean player1 = true;
    protected static String[][] gridPlayer1 = {
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}
    };
    protected static String[][] hiddenGridPlayer1 = {
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}
    };
    protected static String[][] gridPlayer2 = {
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}
    };
    protected static String[][] hiddenGridPlayer2 = {
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}
    };
    protected static Scanner scanner = new Scanner(System.in);
    protected static String[][] ships = {
            {"Aircraft Carrier","5"},
            {"Battleship", "4"},
            {"Submarine","3"},
            {"Cruiser","3"},
            {"Destroyer","2"}
    };
    protected static boolean gameOver = false;

    public static void showGrid(String[][] grid) {
        String[] firstColumnArray = {"A","B","C","D","E","F","G","H","I","J"};
        System.out.println("  1 2 3 4 5 6 7 8 9 10");

        for (int i = 0; i < grid.length; i++) {
            System.out.printf("%s ",firstColumnArray[i]);
            for (String column : grid[i]) {
                System.out.printf("%s ", column);
            }
            System.out.println();
        }
    }

    public static boolean validateInput(String input,String[] ship) {
        String[][] grid = player1 ? gridPlayer1 : gridPlayer2;
        int shipLength   = Integer.parseInt(ship[1]);
        String[] splited = input.split("\\s+");

        if (splited.length != 2) {
            System.out.println("Error! Wrong input");
            return false;
        }

        if (
                splited[0].charAt(0) < 'A' && splited[0].charAt(0) > 'J'
                        || splited[1].charAt(0) < 'A' && splited[1].charAt(0) > 'J'
        ) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }

        try {
            int firstRow = splited[0].toLowerCase().charAt(0) - 'a' + 1;
            int secondRow = splited[1].toLowerCase().charAt(0) - 'a' + 1;
            int firstColumn = Integer.parseInt(splited[0].substring(1));
            int secondColumn = Integer.parseInt(splited[1].substring(1));

            int firstRowIndex = Math.max(splited[0].toLowerCase().charAt(0) - 'a', 0);
            int secondRowIndex =  Math.max(splited[1].toLowerCase().charAt(0) - 'a', 0);

            int firstColumnIndex = Integer.parseInt(splited[0].substring(1)) == 10 ? 9 : Integer.parseInt(splited[0].substring(1));
            int secondColumnIndex = Integer.parseInt(splited[1].substring(1)) == 10 ? 9 : Integer.parseInt(splited[1].substring(1));

            if (firstRow == secondRow) {
                if ( Math.abs(Math.max(firstColumn, secondColumn) - Math.min(firstColumn, secondColumn)) != shipLength - 1) {
                    System.out.printf("Error! Wrong length of the %s! Try again:",ship[0]);
                    return false;
                }

                int startingIndex = Math.min(firstColumnIndex, secondColumnIndex) == 0 ? 0 : Math.min(firstColumnIndex, secondColumnIndex);
                int endIndex      = Math.max(firstColumnIndex, secondColumnIndex) == grid[firstRowIndex].length - 1 ? Math.max(firstColumnIndex, secondColumnIndex) : Math.max(firstColumnIndex, secondColumnIndex) + 1;

                for (int i = startingIndex; i <= endIndex; i++) {
                    if (Objects.equals(grid[firstRowIndex][i], "O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                }

                return true;
            } else if (firstColumn == secondColumn) {
                if (Math.abs(secondRow - firstRow) != shipLength - 1) {
                    System.out.printf("Error! Wrong length of the %s! Try again:",ship[0]);
                    return false;
                } else {
                    int startingIndex = Math.min(firstRowIndex, secondRowIndex) == 0 ? 0 : Math.min(firstRowIndex, secondRowIndex) - 1;
                    int endIndex      = Math.max(firstRowIndex, secondRowIndex) == 9 ? 9 : (Math.max(firstRowIndex, secondRowIndex)) + 1;

                    for (int i = startingIndex; i <= endIndex; i++) {
                        if (Objects.equals(grid[i][firstColumnIndex],"O")) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    }
                }

                return true;
            } else {
                System.out.println("Error! Wrong ship location! Try again:");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error! Wrong input");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean validateHit(String shot){
        int row = shot.toLowerCase().charAt(0) - 'a' + 1;

        if (row > 10 || row < 1) {
            System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            return false;
        }

        int column = Integer.parseInt(shot.substring(1));

        if (column > 10 || column < 1) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            return false;
        }

        return true;
    }

    public static boolean checkPosition(int row, int column) {
        String[][] grid = player1 ? gridPlayer1 : gridPlayer2;
        return grid[row][column].equals("O");
    }

    public static boolean isSank(String shot){
        int row = shot.toLowerCase().charAt(0) - 'a';
        int column = Integer.parseInt(shot.substring(1)) - 1;
        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;

        // up
        if (row != 0) {
            up = checkPosition(row - 1, column);
        }

        // down
        if (row != 9) {
            down = checkPosition(row + 1, column);
        }

        // left
        if (column != 0) {
            left = checkPosition(row, column - 1);
        }

        // right
        if (column != 9) {
            right = checkPosition(row, column + 1);
        }

        return !up && !down && !left && !right;
    }
    public static void hitTarget(String shot) {
        try {
            String[][] grid = player1 ? gridPlayer2 : gridPlayer1;
            String[][] hiddenGrid = player1 ? hiddenGridPlayer2 : hiddenGridPlayer1;
            int row = shot.toLowerCase().charAt(0) - 'a';
            int column = Integer.parseInt(shot.substring(1)) - 1;

            if (Objects.equals(grid[row][column], "O") || Objects.equals(grid[row][column], "X")) {
                grid[row][column] = "X";
                hiddenGrid[row][column] = "X";

                //showGrid(hiddenGrid);

                if (isSank(shot)) {
                    System.out.println("You sank a ship!");
                } else {
                    System.out.println("You hit a ship!");
                }
            } else {
                grid[row][column] = "M";
                hiddenGrid[row][column] = "M";
                //showGrid(hiddenGrid);
                System.out.println("\nYou missed!");
            }

        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateGrid(String input) {
        String[] splited = input.split("\\s+");

        try {
            String[][] grid = player1 ? gridPlayer1 : gridPlayer2;
            int firstRow = (splited[0].toLowerCase().charAt(0) - 'a' + 1 >= 10) ? 9 : splited[0].toLowerCase().charAt(0) - 'a';
            int secondRow = (splited[1].toLowerCase().charAt(0) - 'a' + 1 >= 10) ? 9 : splited[1].toLowerCase().charAt(0) - 'a';
            int firstColumn = Integer.parseInt(splited[0].substring(1)) == 10 ? 9 : Integer.parseInt(splited[0].substring(1)) - 1;
            int secondColumn = Integer.parseInt(splited[1].substring(1)) == 10 ? 9 : Integer.parseInt(splited[1].substring(1)) - 1;

            if (firstRow == secondRow) {
                for (int i = Math.min(firstColumn, secondColumn); i <= Math.max(firstColumn, secondColumn); i++) {
                    grid[firstRow][i] = "O";
                }
            } else if (firstColumn == secondColumn) {
                for (int i = Math.min(firstRow, secondRow); i <= Math.max(firstRow, secondRow); i++) {
                    grid[i][firstColumn] = "O";
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void remainingShips() {
        String[][] grid = player1 ? gridPlayer2 : gridPlayer1;
        for (String[] strings : grid) {
            for (int j = 0; j < strings.length; j++) {
                List<String> myStringList = new ArrayList<>(Arrays.asList(strings));

                if (myStringList.contains("O")) {
                    return;
                }
            }
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
        gameOver = true;
    }

    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Write your code here
        for (int player = 1; player <= 2; player++) {

            String[][] grid = player == 1 ? gridPlayer1 : gridPlayer2;
            System.out.printf("Player %d, place your ships on the game field\n", player1 ? 1 : 2);
            showGrid(grid);

            for (int i = 0; i < ships.length; i++) {
                System.out.println();
                System.out.printf("Enter the coordinates of the %s (%s cells):",ships[i][0], ships[i][1]);
                System.out.println();

                while (true) {
                    String input = scanner.nextLine();

                    if (validateInput(input, ships[i])) {
                        updateGrid(input);
                        showGrid(grid);
                        break;
                    }
                }
            }
            promptEnterKey();
            player1 = !player1;
        }

        while (!gameOver) {
            String[][] myGrid = player1 ? gridPlayer1 : gridPlayer2;
            String[][] opponentGrid = !player1 ? hiddenGridPlayer1 : hiddenGridPlayer2;

            showGrid(opponentGrid);
            System.out.println("---------------------");
            showGrid(myGrid);
            System.out.printf("\nPlayer %d, it's your turn:", player1 ? 1 : 2);

            String shot = scanner.next();

            if(validateHit(shot)) {
                hitTarget(shot);
                remainingShips();
            }
            promptEnterKey();
            player1 = !player1;
        }
    }
}
