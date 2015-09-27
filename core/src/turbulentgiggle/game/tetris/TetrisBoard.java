package turbulentgiggle.game.tetris;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.lwjgl.util.Point;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Quang on 9/26/2015.
 */
public class TetrisBoard {

    private Color[][] board;
    private int xOffset, yOffset;
    private static final int BLOCK_SIZE = 32;
    private Piece currentPiece;
    private HashMap<String, Piece> pieceDefinitions = new HashMap<String, Piece>()
        {{
            put("O", new Piece(Color.YELLOW, new boolean[][]
                    {
                            {true, true},
                            {true, true},
                    }));
            put("I", new Piece(Color.CYAN, new boolean[][]
                    {
                            {true, true, true, true},
                            {false, false, false, false},
                            {false, false, false, false},
                            {false, false, false, false}
                    }));
            put("S", new Piece(Color.GREEN, new boolean[][]
                    {
                            {false, true, true},
                            {true, true, false},
                            {false, false, false}
                    }));
            put("Z", new Piece(Color.RED, new boolean[][]
                    {
                            {true, true, false},
                            {false, true, true},
                            {false, false, false}
                    }));
            put("J", new Piece(Color.ORANGE, new boolean[][]
                    {
                            {true, true, true},
                            {false, false, true},
                            {false, false, false}
                    }));
            put("T", new Piece(Color.MAGENTA, new boolean[][]
                    {
                            {true, true, true},
                            {false, true, false},
                            {false, false, false}
                    }));
            put("L", new Piece(Color.BLUE, new boolean[][]
                    {
                            {true, true, true},
                            {true, false, false},
                            {false, false, false}
                    }));
        }};

    public TetrisBoard(int xOffset, int yOffset, int width, int height) {
        board = new Color[height][width];
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public boolean isCurrentLocationValid() {
        for(Point point : currentPiece.getPoints())
        {
            if (point.getX() > board[0].length || point.getY() > board.length || point.getX() < 0 || point.getY() < 0)
                return false;
        }
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(xOffset, yOffset, BLOCK_SIZE * board.length, BLOCK_SIZE * board[0].length - 1);
        for(int y = 0; y < board.length; y++) {
            for(int x = 0; x < board[y].length; x++) {
                if(board[y][x] != null) {
                    shapeRenderer.setColor(board[y][x]);
                    shapeRenderer.rect(xOffset + x * 32 + 2, yOffset + y * 32 + 2, 28, 28);
                }
            }
        }
        for (Point p: currentPiece.getPoints())
        {
            shapeRenderer.setColor(currentPiece.color);
            shapeRenderer.rect(xOffset + p.getX() * 32 + 2, yOffset + p.getY() * 32 + 2, 28, 28);
        }
    }

    public void rotateCurrentBlockClockwise()
    {
        currentPiece.rotateClockwise();
    }

    public void rotateCurrentBlockCounterclockwise()
    {
        currentPiece.rotateCounterclockwise();
    }

    public void addPiece(List<Point> points, Color color) {
        for(Point point : points) {
            setPosition(point, color);
        }
    }

    public void setPosition(Point point, Color color) {
        board[point.getX()][point.getY()] = color;
    }

}
