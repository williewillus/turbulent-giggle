package turbulentgiggle.game.tetris;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.util.Point;

public class Piece {
    boolean[][] piece;
    public Color color;
    public Piece(Color color, boolean[][] piece)
    {
        this.piece = piece;
        this.color = color;
    }
    public List<Point> getPoints()
    {
        List<Point> l = new ArrayList<Point>();
        for(int y = 0; y < piece.length; y++)
        {
            for (int x = 0; x < piece[0].length; x++)
            {
                if (piece[y][x])
                    l.add(new Point(x, y));
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
    }
    public boolean getPointAt(int x, int y)
    {
        if (x < getWidth() && y < getHeight())
        {
            return piece[y][x];
        }
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
