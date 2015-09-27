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
        fixRotation();
    }
    private void fixRotation()
    {
        boolean topRowIsEmpty = true;
        boolean bottomRowIsEmpty = true;
        for (boolean b: piece[piece.length -1])
        {
            if (b)
            {
                bottomRowIsEmpty =false;
                break;
            }
        }
        for (boolean b: piece[0])
        {
            if (b)
            {
                topRowIsEmpty = false;
                break;
            }
        }
        if (topRowIsEmpty)
        {
            for (int y = 0; y < piece.length; y++)
            {
                boolean rowIsEmpty = true;
                for (boolean b: piece[y])
                {
                    if (b)
                    {
                        rowIsEmpty = false;
                        break;
                    }
                }
                if (rowIsEmpty)
                {
                    for (int k = y; k < piece.length; k++ )
                    {
                        piece[k] = piece[k+1];
                    }
                    piece[piece.length -1] = new boolean[piece[0].length];
                    y--;
                }
            }
        }
        else if (bottomRowIsEmpty)
        {
            for (int y = piece.length -1; y >= 0; y--)
            {
                boolean rowIsEmpty = true;
                for (boolean b: piece[y])
                {
                    if (b)
                    {
                        rowIsEmpty = false;
                        break;
                    }
                }
                if (rowIsEmpty)
                {
                    for (int k = y; k >= 0; k-- )
                    {
                        piece[k] = piece[k-1];
                    }
                    piece[0] = new boolean[piece[0].length];
                    y++;
                }
            }
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
