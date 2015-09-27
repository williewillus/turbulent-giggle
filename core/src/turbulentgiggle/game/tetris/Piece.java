package turbulentgiggle.game.tetris;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.util.Point;

public class Piece {
    boolean[][] piece;
    public Color color;
    public Point currentPosition;
    public Piece(Color color, boolean[][] piece)
    {
        this.piece = piece;
        this.color = color;
        fixRotation();
    }

    public List<Point> getPoints()
    {
        List<Point> l = new ArrayList<Point>();
        for(int y = 0; y < piece.length; y++)
        {
            for (int x = 0; x < piece[0].length; x++)
            {
                if (piece[y][x])
                    l.add(new Point(currentPosition.getX() + x, currentPosition.getY() + y));
            }
        }
        return l;
    }
    public void rotateClockwise()
    {
        boolean[][] newPiece = new boolean[piece.length][piece[0].length];
        for (int y = 0; y < piece.length; y++)
        {
            for (int x = 0; x < piece[0].length; x++)
            {
                newPiece[y][x] = piece[piece.length - x - 1][y];
            }
        }
        piece = newPiece;
        fixRotation();
    }
    public void rotateCounterclockwise()
    {
        boolean[][] newPiece = new boolean[piece.length][piece[0].length];
        for (int y = 0; y < piece.length; y++)
        {
            for (int x = 0; x < piece[0].length; x++)
            {
                newPiece[y][x] = piece[x][piece[0].length - y - 1];
            }
        }
        piece = newPiece;
        fixRotation();
    }
    private void fixRotation()
    {
        boolean botRowIsEmpty = true;
        for (boolean b: piece[0])
        {
            if (b)
            {
                botRowIsEmpty = false;
                break;
            }
        }
        if (botRowIsEmpty)
        {
            for (int y = 0; y < piece.length - 1; y++)
            {
                piece[y] = piece[y+1];
            }
            piece[piece.length -1] = new boolean[piece[0].length];
        }
    }
    public boolean getPointAt(int x, int y)
    {
        if (x < getWidth() && y < getHeight())
        {
            return piece[y][x];
        }
        return false;
    }
    public int getWidth()
    {
        return piece[0].length;
    }
    public int getHeight()
    {
        return piece.length;
    }

}
