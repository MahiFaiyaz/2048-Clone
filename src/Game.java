/**
 * Author: Mahi Faiyaz
 * macID: faiyazm
 * Description: Game class
 */

package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

/**
 * @brief An ADT Game that extends JPanel class and implements a KeyListener
 * @details The main user interface class, extends JPanel and implements a key listener.
 * Creates a new game board, as well as a new JFrame, where the board is displayed.
 */
public class Game extends JPanel implements KeyListener {

    GameBoard gb = new GameBoard();
    static Game game = new Game();
    static JFrame frame = new JFrame("2048");
    private static final Color COLOR_BACKGROUND = new Color(187,173,160);
    private static final Color FRAME_BACKGROUND = new Color(250,248,239);
    private static final Color Color_SCORE = new Color(238,222,198);
    private static final Color COLOR_EMPTY = new Color (205, 193, 180);
    private static final Color COLOR_2 = new Color (238, 228, 218);
    private static final Color COLOR_4 = new Color (238, 225, 201);
    private static final Color COLOR_8 = new Color (243, 178, 122);
    private static final Color COLOR_16 = new Color (245, 149, 99);
    private static final Color COLOR_32 = new Color (247, 124, 95);
    private static final Color COLOR_64 = new Color (246, 94, 59);
    private static final Color COLOR_128 = new Color (237, 207, 114);
    private static final Color COLOR_256 = new Color (237, 204, 97);
    private static final Color COLOR_512 = new Color (237, 200, 80);
    private static final Color COLOR_1024 = new Color (237, 197, 63);
    private static final Color COLOR_2048 = new Color (237, 194, 46);
    private static final Color COLOR_4096 = new Color (118, 213, 68);
    private static final Color COLOR_LIGHT = new Color (249, 246, 242);
    private static final Color COLOR_DARK = new Color (119, 110, 101);
    private static boolean Start = false;

    /**
     * @brief Method that sets up the GUI
     * @details Initializes frame content pane and dimensions.
     */
    public static void GUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.addKeyListener(game);
        frame.getContentPane().add(game, BorderLayout.CENTER);

        frame.getContentPane().setPreferredSize(new Dimension(470, 570));
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * @brief Method from KeyListener.
     * @details Arrow keys calls the respective move function on the game board. Escape resets the game board.
     * Enter resets the game board if game is over.
     * @param e A key press event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!Game.Start) {
                Game.Start = true;
                gb = new GameBoard();
                frame.repaint();
            }
            if (gb.gameOver()) {
                gb = new GameBoard();
                frame.repaint();
            }
        }
        if (!Start) {return;}
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            boolean boardChanged = false;
            int[][] copy = Arrays.stream(gb.getBoard()).map(int[]::clone).toArray(int[][]::new);
            gb.moveUp();
            for (int i = 0; i < 4; i ++) {
                if (!Arrays.equals(gb.getBoard()[i], copy[i])) {
                    boardChanged = true;
                }
            }
            if (boardChanged) {
                gb.addNewNum();
                frame.repaint();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            boolean boardChanged = false;
            int[][] copy = Arrays.stream(gb.getBoard()).map(int[]::clone).toArray(int[][]::new);
            gb.moveDown();
            for (int i = 0; i < 4; i ++) {
                if (!Arrays.equals(gb.getBoard()[i], copy[i])) {
                    boardChanged = true;
                }
            }
            if (boardChanged) {
                gb.addNewNum();
                frame.repaint();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            boolean boardChanged = false;
            int[][] copy = Arrays.stream(gb.getBoard()).map(int[]::clone).toArray(int[][]::new);
            gb.moveLeft();
            for (int i = 0; i < 4; i ++) {
                if (!Arrays.equals(gb.getBoard()[i], copy[i])) {
                    boardChanged = true;
                }
            }
            if (boardChanged) {
                gb.addNewNum();
                frame.repaint();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            boolean boardChanged = false;
            int[][] copy = Arrays.stream(gb.getBoard()).map(int[]::clone).toArray(int[][]::new);
            gb.moveRight();
            for (int i = 0; i < 4; i ++) {
                if (!Arrays.equals(gb.getBoard()[i], copy[i])) {
                    boardChanged = true;
                }
            }
            if (boardChanged) {
                gb.addNewNum();
                frame.repaint();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gb = new GameBoard();
            frame.repaint();
        }
    }

    /**
     * @brief keyReleased method from KeyListener. Not implemented.
     * @param e A key release event.
     */
    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * @brief keyTyped method from KeyListener. Not implemented.
     * @param e A key typed event.
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * @brief Paints the main GUI, and checks for win/loss conditions.
     * @param g a Graphics g object.
     */
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("default", Font.BOLD, 32));
        g2d.setColor(FRAME_BACKGROUND);
        g2d.fillRect(0, 0, 470,570);
        g2d.setColor(COLOR_BACKGROUND);
        g2d.fillRoundRect(110,25,250,50,20,20);
        g2d.fillRoundRect(50,100,370,370,20,20);
        g2d.setColor(Color_SCORE);
        g.drawString("Score: ", 115, 60);
        g2d.setColor(Color.white);
        g.drawString("" + gb.getScore(), 220, 60);

        if (!Game.Start) {
            for (int row = 0; row < 4; row ++) {
                for (int col = 0; col < 4; col ++) {
                    drawTiles(g, (new int[4][4])[row][col], col * 90 + 60, row * 90 + 110);
                }
            }
            g2d.setColor(new Color(204, 192, 179, 175));
            g2d.fillRoundRect(50,100,370,370,20,20);
            g2d.setColor(Color.black);
            g.drawString("Arrow keys to move", 80, 280);
            g.drawString("ENTER to Begin", 115, 320);
        }
        else {
            for (int row = 0; row < 4; row ++) {
                for (int col = 0; col < 4; col ++) {
                    drawTiles(g, gb.getBoard()[row][col], col * 90 + 60, row * 90 + 110);
                }
            }
        }

        if (gb.getHighestNum() == 2048) {
            g2d.setColor(new Color(204, 192, 179, 175));
            g2d.fillRoundRect(160,495,150,50,20,20);
            g2d.setColor(Color.black);
            g.drawString("YOU WIN", 165, 530);
        }
        if (gb.gameOver()) {
            g2d.setColor(new Color(204, 192, 179, 175));
            g2d.fillRoundRect(50,100,370,370,20,20);
            g2d.setColor(Color.black);
            g.drawString("GAME OVER", 135, 280);
            g.drawString("ENTER to restart", 105, 320);
        }
    }

    /**
     * @brief Method to draw the tile with the given value at the given positions.
     * @param g A graphics object.
     * @param value Value of the tile.
     * @param x X-coordinate of location where tile is to be painted.
     * @param y Y-coordinate of location where tile is to be painted.
     */
    public void drawTiles(Graphics g, int value, int x, int y) {
        String v = String.valueOf(value);
        Graphics2D g2d = (Graphics2D)g;
        int length = v.length();
        g2d.setColor(COLOR_EMPTY);
        g2d.fillRoundRect(x,y,80,80,12,12);
        g2d.setColor(COLOR_BACKGROUND);
        g2d.setColor(Color.black);
        if (value > 0) {
            if (value == 2) {g2d.setColor(COLOR_2);}
            if (value == 4) {g2d.setColor(COLOR_4);}
            if (value == 8) {g2d.setColor(COLOR_8);}
            if (value == 16) {g2d.setColor(COLOR_16);}
            if (value == 32) {g2d.setColor(COLOR_32);}
            if (value == 64) {g2d.setColor(COLOR_64);}
            if (value == 128) {g2d.setColor(COLOR_128);}
            if (value == 256) {g2d.setColor(COLOR_256);}
            if (value == 512) {g2d.setColor(COLOR_512);}
            if (value == 1024) {g2d.setColor(COLOR_1024);}
            if (value == 2048) {g2d.setColor(COLOR_2048);}
            if (value > 2048) {g2d.setColor(COLOR_4096);}
            g2d.fillRoundRect(x,y,80,80,12,12);
            if (value < 8) {
                g2d.setColor(COLOR_DARK);
            }
            else {
                g2d.setColor(COLOR_LIGHT);
            }
            g2d.setFont(new Font("default", Font.BOLD, 32));
            g.drawString( "" + value, x + 42 - 10*length, y + 52);
        }
    }

}
