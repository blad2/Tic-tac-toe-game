package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Board board = new Board('X');
        boolean gameResult;
        do {
            System.out.print("Enter the coordinate: ");
            board.move(input);
            board.printPlays();
            gameResult = board.gameResult().equals("Game not finished");
        } while (gameResult);
        System.out.println(board.gameResult());
    }
}

class Board {
    char[] cells = new char[9];
    private boolean xPlaying;

    Board(char startingPlayer) {
    this.xPlaying = startingPlayer == 'X';
    System.out.println("---------");
    for (int i = 0; i < cells.length; i++) {
        if ((i + 1) % 3 == 0 ) {
            char cell = cells[i] != 0 ? cells[i] : ' ';
            System.out.printf("%s |\n", cell);
        } else if (i % 3 == 0){
            char cell = cells[i] != 0 ? cells[i] : ' ';
            System.out.printf("| %s ", cell);
        } else {
            char cell = cells[i] != 0 ? cells[i] : ' ';
            System.out.printf("%s ", cell);
        }
    }
    System.out.println("---------");
}

    public void printPlays() {
         System.out.println("---------");
         for (int i = 0; i < cells.length; i++) {
             if ((i + 1) % 3 == 0 ) {
                 char cell = cells[i] != 0 ? cells[i] : ' ';
                 System.out.printf("%s |\n", cell);
             } else if (i % 3 == 0){
                 char cell = cells[i] != 0 ? cells[i] : ' ';
                 System.out.printf("| %s ", cell);
             } else {
                 char cell = cells[i] != 0 ? cells[i] : ' ';
                 System.out.printf("%s ", cell);
             }
         }
         System.out.println("---------");
     }

    public void move(Scanner input) {
        char player = xPlaying ? 'X' : 'O';
        try {
            int row = input.nextInt();
            int col = input.nextInt();
            int index = row < 4 && col < 4 ? getIndex(row, col) : -1;
            if (index == -1){
                System.out.println("Coordinates should be from 1 to 3");
            } else {
                if (cells[index] == 0) {
                    cells[index] = player;
                    xPlaying = !xPlaying;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }
        } catch (NumberFormatException n) {
            System.out.println("You should enter a number!");
        }
    }

    public static int getIndex(int row, int col) {
    int index = 0;
    switch (row) {
        case 1:
            index = col - row;
            break;
        case 2:
            index = col + row;
            break;
        case 3:
            index = col + row + 2;
            break;
    }
    return index;
}

    public String gameResult() {
        String state;
        Player playerX = new Player(this.getPlays(),'X');
        Player playerO = new Player(this.getPlays(),'O');
        int quantityX = playerX.movesCount(cells, 'X');
        int quantityO = playerO.movesCount(cells, 'O');
        if (playerX.winningPlayer() && playerO.winningPlayer() || Math.abs(quantityO - quantityX) > 1 ) {
            state = "Impossible";
        } else if (playerX.winningPlayer()) {
            state = "X wins";
        } else if (playerO.winningPlayer()) {
            state = "O wins";
        } else if (quantityO + quantityX == 9) {
            state = "Draw";
        }  else {
            state = "Game not finished";
        }
        return state;
    }

    public String getPlays() {
        StringBuilder plays = new StringBuilder();
        for (char cell : cells) {
            plays.append(cell);
        }
        return plays.toString();
    }
}

class Player {
    boolean cell;
    boolean cell1;
    boolean cell2;
    boolean cell3;
    boolean cell4;
    boolean cell5;
    boolean cell6;
    boolean cell7;
    boolean cell8;


    Player(String plays, char player) {
        cell = plays.charAt(0) == player;
        cell1 = plays.charAt(1) == player;
        cell2 = plays.charAt(2) == player;
        cell3 = plays.charAt(3) == player;
        cell4 = plays.charAt(4) == player;
        cell5 = plays.charAt(5) == player;
        cell6 = plays.charAt(6) == player;
        cell7 = plays.charAt(7) == player;
        cell8 = plays.charAt(8) == player;
    }

    public boolean winningPlayer() {
        return cell && cell1 && cell2
                || cell3 && cell4 && cell5
                || cell6 && cell7 && cell8
                || cell && cell4 && cell8
                || cell && cell3 && cell6
                || cell1 && cell4 && cell7
                || cell2 && cell4 && cell6
                || cell2 && cell5 && cell8;
    }

    public int movesCount(char[] plays, char player) {
         int moves = 0;
         for (char play : plays) {
             if (play == player) {
                 moves++;
             }
         }
         return moves;
     }
}