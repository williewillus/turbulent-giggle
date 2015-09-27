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
        addPiece("Z");
    }

    public void addPiece(String type)
    {
        currentPiece = pieceDefinitions.get(type);
        currentPiece.currentPosition = new Point(board[0].length/2, board.length - 1);
    }
    public boolean moveRight()
    {
        Point oldPosition = new Point(currentPiece.currentPosition.getX(), currentPiece.currentPosition.getY());
        currentPiece.currentPosition.setX(oldPosition.getX() + 1);
        if (!isCurrentLocationValid(currentPiece))
        {
            currentPiece.currentPosition = oldPosition;
            return false;
        }
        return true;
    }

    public boolean moveLeft()
    {
        Point oldPosition = new Point(currentPiece.currentPosition.getX(), currentPiece.currentPosition.getY());
        currentPiece.currentPosition.setX(oldPosition.getX() - 1);
        if (!isCurrentLocationValid(currentPiece))
        {
            currentPiece.currentPosition = oldPosition;
            return false;
        }
        return true;
    }

    public boolean isCurrentLocationValid(Piece p) {
        for(Point point : p.getPoints())
        {
            if (point.getX() > board[0].length || point.getY() > board.length || point.getX() < 0 || point.getY() < 0)
                return false;
            if (board[point.getY()][point.getX()] != null)
                return false;
        }
        return true;
    }
    private void dropRows()
    {
        for (int y = board.length - 1; y >= 0; y--)
        {
            boolean totallyFilled = true;
            for(Color c: board[y])
            {
                if (c == null)
                {
                    totallyFilled = false;
                    break;
                }
            }
            if (totallyFilled)
            {
                for (int k = y-1; k >= 1; k-- )
                {
                    board[k+1] = board[k];
                }
                board[0] = new Color[board[0].length];
            }
            y += 1;
        }
    }
    public void tick()
    {
        Point oldPosition = new Point(currentPiece.currentPosition.getX(), currentPiece.currentPosition.getY());
        currentPiece.currentPosition.setY(currentPiece.currentPosition.getY() - 1);
        if (!isCurrentLocationValid(currentPiece))
        {
            currentPiece.currentPosition = oldPosition;
            for(Point point : currentPiece.getPoints())
            {
                board[point.getY()][point.getX()] = currentPiece.color;
            }
            System.out.println(1);
            dropRows();
            System.out.println(2);
            addPiece((new String[] {"O", "I", "S", "Z", "J", "T", "L"})[(int)(Math.random() * 7)]);
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

    // This method sucks but it should work
    private void fixOffset()
    {
        if (isCurrentLocationValid(currentPiece))
        {
            return;
        }
        Point oldPosition = new Point(currentPiece.currentPosition.getX(), currentPiece.currentPosition.getY());
        double shortestDistance = Double.MAX_VALUE;
        Point optimalPosition = oldPosition;
        for (int y = 0; y < board.length; y++)
        {
            for (int x = 0; x < board[0].length; x++)
            {
                currentPiece.currentPosition = new Point(x,y);
                if (isCurrentLocationValid(currentPiece) && Math.sqrt((y-oldPosition.getY())^2+(x-oldPosition.getX())^2) < shortestDistance)
                {
                    shortestDistance = Math.sqrt((y-oldPosition.getY())^2+(x-oldPosition.getX())^2);
                    optimalPosition = new Point(x,y);
                }
            }
        }
        currentPiece.currentPosition = optimalPosition;
    }

}
