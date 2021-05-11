/**
 * Author: Mahi Faiyaz
 * macID: faiyazm
 * Description: GameBoard class
 */

package src;
import java.util.ArrayList;

/**
 * @brief An ADT that represents the game board.
 * @details The game board is represented by a 2D 4x4 array of the grid,
 * as well as a variable for the score and the highest number currently on the board.
 */
public class GameBoard {

    private int[][] board;
    private int score = 0;
    private int highestNum;

    /**
     * @brief Initializes a GameBoard.
     * @details Creates a blank game board, and adds 2 numbers in a random location on the board.
     * Each number has 90% chance of being a 2 and a 10% chance of being a 4.
     */
    protected GameBoard() {
        board = new int[4][4];
        addNewNum();
        addNewNum();
    }

    /**
     * @brief Initializes a GameBoard given a 2D 4x4 array (made for testing purposes).
     * @details Creates a game board that represents the given 2D array.
     * @param layout A 2D int array.
     */
    protected GameBoard(int[][] layout) {
        board = layout;
        for (int[] row : board) {
            for (int value : row) {
                if (value > highestNum) {
                    highestNum = value;
                }
            }
        }
    }


    /**
     * @brief Method to add a 2 (with a 90% chance) or a 4 (with a 10% chance) into a randomly
     * chosen empty location in the board.
     */
    protected void addNewNum() {

        int newNum;
        ArrayList<int[]> validLocations = new ArrayList<>();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == 0) {
                    validLocations.add(new int[]{row, col});
                }
            }
        }

        if (validLocations.size() == 0) {return;}

        int randomIndex = (int) Math.round((Math.random() * (validLocations.size() - 1)));

        int TwoOrFour = (int) Math.round((Math.random() * 9));
        if (TwoOrFour == 4) {
            newNum = 4;
        }
        else {
            newNum = 2;
        }
        int[] numLocation = validLocations.get(randomIndex);
        board[numLocation[0]][numLocation[1]] = newNum;

        if (newNum > highestNum) {
            highestNum = newNum;
        }
    }

    /**
     * @brief Method to add value into the desired location in the board (for testing purposes).
     * @param value int Value to be added.
     * @param row int row to insert value in.
     * @param col int col to insert value in.
     */
    protected void addNewNum(int value, int row, int col) {
        board[row][col] = value;
        if (value > highestNum) {
            highestNum = value;
        }
    }

    /**
     * @brief Method to get current score.
     * @return The current score.
     */
    protected int getScore(){
        return score;
    }

    /**
     * @brief Method to get the highest number on the board.
     * @return The current highest number on the board.
     */
    protected int getHighestNum() {
        return highestNum;
    }

    /**
     * @brief Method to get the 2D 4x4 int array that represents the current board.
     * @return A 2D 4x4 int array of the current board.
     */
    protected int[][] getBoard() {
        return board;
    }

    /**
     * @brief Method to get number of elements on the board.
     * @return The number of elements on the board.
     */
    protected int getCount() {
        int count = 0;
        for (int[] row : board) {
            for (int col : row) {
                if (col > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * @brief Private method to shift all elements up to fill in blank spaces.
     */
    private void shiftUp() {
        for (int col = 0; col < 4; col++) {
            for (int row = 1; row < 4; row++){
                if (board[row][col] != 0) {
                    int value = board[row][col];
                    int x = row - 1;
                    while (x >= 0 && board[x][col] == 0 ) {
                        x--;
                    }
                    if (x == -1) {
                        board[0][col] = value;
                        board[row][col] = 0;
                    }
                    else if (x + 1 != row) {
                        board[x+1][col] = value;
                        board[row][col] = 0;
                    }
                }
            }
        }
    }

    /**
     * @brief Private method that combines adjacent values upwards.
     */
    private void combineUp() {
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 3; row++) {
                if (board[row][col] == board[row + 1][col] && board[row][col] != 0) {
                    board[row][col] *= 2;
                    score += board[row][col];
                    board[row + 1][col] = 0;
                    if (board[row][col] > highestNum) {
                        highestNum = board[row][col];
                    }
                }
            }
        }
    }

    /**
     * @brief Method to shift all values up, combine, and shift up one more time.
     */
    protected void moveUp() {
        shiftUp();
        combineUp();
        shiftUp();
    }

    /**
     * @brief Private method to shift all elements down to fill in blank spaces.
     */
    private void shiftDown() {
        for (int col = 0; col < 4; col++) {
            for (int row = 2; row >= 0; row--){
                if (board[row][col] != 0) {
                    int value = board[row][col];
                    int x = row + 1;
                    while (x < 4 && board[x][col] == 0 ) {
                        x++;
                    }
                    if (x == 4) {
                        board[3][col] = value;
                        board[row][col] = 0;
                    }
                    else if (x - 1 != row) {
                        board[x-1][col] = value;
                        board[row][col] = 0;
                    }
                }
            }
        }
    }

    /**
     * @brief Private method that combines adjacent values downwards.
     */
    private void combineDown() {
        for (int col = 0; col < 4; col++) {
            for (int row = 3; row > 0; row--) {
                if (board[row][col] == board[row - 1][col] && board[row][col] != 0) {
                    board[row][col] *= 2;
                    score += board[row][col];
                    board[row - 1][col] = 0;
                    if (board[row][col] > highestNum) {
                        highestNum = board[row][col];
                    }
                }
            }
        }
    }

    /**
     * @brief Method to shift all values down, combine, and shift down one more time.
     */
    protected void moveDown() {
        shiftDown();
        combineDown();
        shiftDown();
    }

    /**
     * @brief Private method to shift all elements left to fill in blank spaces.
     */
    private void shiftLeft() {
        for (int row = 0; row < 4; row++) {
            for (int col = 1; col < 4; col++){
                if (board[row][col] != 0) {
                    int value = board[row][col];
                    int y = col - 1;
                    while (y >= 0 && board[row][y] == 0 ) {
                        y--;
                    }
                    if (y == -1) {
                        board[row][0] = value;
                        board[row][col] = 0;
                    }
                    else if (y + 1 != col) {
                        board[row][y+1] = value;
                        board[row][col] = 0;
                    }
                }
            }
        }
    }

    /**
     * @brief Private method that combines adjacent values to the left.
     */
    private void combineLeft() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == board[row][col + 1] && board[row][col] != 0) {
                    board[row][col] *= 2;
                    score += board[row][col];
                    board[row][col+1] = 0;
                    if (board[row][col] > highestNum) {
                        highestNum = board[row][col];
                    }
                }
            }
        }
    }

    /**
     * @brief Method to shift all values left, combine, and shift left one more time.
     */
    protected void moveLeft() {
        shiftLeft();
        combineLeft();
        shiftLeft();
    }

    /**
     * @brief Private method to shift all elements right to fill in blank spaces.
     */
    private void shiftRight() {
        for (int row = 0; row < 4; row++) {
            for (int col = 2; col >= 0; col--){
                if (board[row][col] != 0) {
                    int value = board[row][col];
                    int y = col + 1;
                    while (y < 4 && board[row][y] == 0 ) {
                        y++;
                    }
                    if (y == 4) {
                        board[row][3] = value;
                        board[row][col] = 0;
                    }
                    else if (y - 1 != col) {
                        board[row][y-1] = value;
                        board[row][col] = 0;
                    }
                }
            }
        }
    }

    /**
     * @brief Private method that combines adjacent values to the right.
     */
    private void combineRight() {
        for (int row = 0; row < 4; row++) {
            for (int col = 3; col > 0; col--) {
                if (board[row][col] == board[row][col-1] && board[row][col] != 0) {
                    board[row][col] *= 2;
                    score += board[row][col];
                    board[row][col-1] = 0;
                    if (board[row][col] > highestNum) {
                        highestNum = board[row][col];
                    }
                }
            }
        }
    }

    /**
     * @brief Method to shift all values right, combine, and shift right one more time.
     */
    protected void moveRight() {
        shiftRight();
        combineRight();
        shiftRight();
    }

    /**
     * @brief Checks to see if game is over.
     * @return True if game is over, otherwise false.
     */
    protected boolean gameOver() {
        if (this.getCount() != 16) {
            return false;
        }
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col ++) {
                if (col != 3 && board[row][col] == board[row][col+1]) {
                    return false;
                }
                else if (row != 3 && board[row][col] == board[row + 1][col]) {
                    return false;
                }
            }
        }
        return true;
    }

}
