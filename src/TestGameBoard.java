/**
 * Author: Mahi Faiyaz
 * macID: faiyazm
 * Description: TestGameBoard class
 */
package src;
import org.junit.*;
import static org.junit.Assert.*;

public class TestGameBoard {

    private GameBoard gameBoard0;
    private GameBoard gameBoard1;
    private GameBoard gameBoard2;
    private GameBoard gameBoard3;
    private GameBoard gameBoard4;

    @Before
    public void setUp() {
        int[][] layout0 = { {0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
        int[][] layout1 = { {2,4,0,4}, {0,2,0,4}, {2,2,2,0}, {4,2,4,0}};
        int[][] layout2 = { {2,4,8,16}, {16,4,32,8}, {128,64,4,16}, {4,256,8,32}};
        int[][] layout3 = { {4,4,8,16}, {16,4,32,8}, {128,64,4,16}, {4,256,8,32}};
        int[][] layout4 = { {2,32,8,16}, {16,4,32,8}, {128,64,4,16}, {512,256,8,32}};
        gameBoard0 = new GameBoard(layout0);
        gameBoard1 = new GameBoard(layout1);
        gameBoard2 = new GameBoard(layout2);
        gameBoard3 = new GameBoard(layout3);
        gameBoard4 = new GameBoard(layout4);
    }

    @After
    public void tearDown() {
        gameBoard0 = null;
        gameBoard1 = null;
        gameBoard2 = null;
        gameBoard3 = null;
        gameBoard4 = null;
    }

    @Test
    public void testAddNewNum() {
        gameBoard1.addNewNum(16,0,2);
        assertEquals(16, gameBoard1.getBoard()[0][2]);
        assertEquals(12, gameBoard1.getCount());
        gameBoard1.addNewNum();
        assertEquals(13, gameBoard1.getCount());
        gameBoard1.addNewNum();
        assertEquals(14, gameBoard1.getCount());
        gameBoard2.addNewNum();
        assertEquals(16, gameBoard2.getCount());
    }

    @Test
    public void testGetScore() {
        assertEquals(0, gameBoard1.getScore());
        gameBoard1.moveUp();
        assertEquals(16, gameBoard1.getScore());
        gameBoard1.moveLeft();
        assertEquals(32, gameBoard1.getScore());
        gameBoard1.moveUp();
        assertEquals(48, gameBoard1.getScore());
    }

    @Test
    public void testGetHighestNum() {
        assertEquals(4, gameBoard1.getHighestNum());
        assertEquals(256, gameBoard2.getHighestNum());
    }

    @Test
    public void testGetBoard() {
        int[][] expected1 = { {2,4,0,4}, {0,2,0,4}, {2,2,2,0}, {4,2,4,0}};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected1[i][j], gameBoard1.getBoard()[i][j]);
            }
        }
        int[][] expected2 = { {2,32,8,16}, {16,4,32,8}, {128,64,4,16}, {512,256,8,32}};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected2[i][j], gameBoard4.getBoard()[i][j]);
            }
        }
    }

    @Test
    public void testGetCount() {
        assertEquals(0, gameBoard0.getCount());
        assertEquals(11, gameBoard1.getCount());
        assertEquals(16, gameBoard4.getCount());
    }

    @Test
    public void testMoveUp() {
        int[][] expected = { {4,4,2,8}, {4,4,4,0}, {0,2,0,0}, {0,0,0,0}};
        gameBoard1.moveUp();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected[i][j], gameBoard1.getBoard()[i][j]);
            }
        }
        int[][] expected1 = { {2,8,8,16}, {16,64,32,8}, {128,256,4,16}, {4,0,8,32}};
        gameBoard2.moveUp();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected1[i][j], gameBoard2.getBoard()[i][j]);
            }
        }
    }

    @Test
    public void testMoveDown() {
        int[][] expected = {{0,0,0,0}, {0,4,0,0},{4,2,2,0}, {4,4,4,8}};
        gameBoard1.moveDown();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected[i][j], gameBoard1.getBoard()[i][j]);
            }
        }
        int[][] expected1 = { {2,0,8,16}, {16,8,32,8}, {128,64,4,16}, {4,256,8,32}};
        gameBoard2.moveDown();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected1[i][j], gameBoard2.getBoard()[i][j]);
            }
        }
    }

    @Test
    public void testMoveLeft() {
        int[][] expected = {{2,8,0,0}, {2,4,0,0},{4,2,0,0}, {4,2,4,0}};
        gameBoard1.moveLeft();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected[i][j], gameBoard1.getBoard()[i][j]);
            }
        }
        int[][] expected1 = { {8,8,16,0}, {16,4,32,8}, {128,64,4,16}, {4,256,8,32}};
        gameBoard3.moveLeft();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected1[i][j], gameBoard3.getBoard()[i][j]);
            }
        }
    }

    @Test
    public void testMoveRight() {
        int[][] expected = {{0,0,2,8}, {0,0,2,4},{0,0,2,4}, {0,4,2,4}};
        gameBoard1.moveRight();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected[i][j], gameBoard1.getBoard()[i][j]);
            }
        }
        int[][] expected1 = { {0,8,8,16}, {16,4,32,8}, {128,64,4,16}, {4,256,8,32}} ;
        gameBoard3.moveRight();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expected1[i][j], gameBoard3.getBoard()[i][j]);
            }
        }
    }


    @Test
    public void testGameOver() {
        assertFalse(gameBoard0.gameOver());
        assertFalse(gameBoard1.gameOver());
        assertFalse(gameBoard2.gameOver());
        assertFalse(gameBoard3.gameOver());
        assertTrue(gameBoard4.gameOver());
    }





}
