package turbulentgiggle.game.tetris;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.lwjgl.util.Point;

import java.util.List;

/**
 * Created by Quang on 9/26/2015.
 */
public class TetrisBoard {

    private Color[][] board;
    private int xOffset, yOffset;

    public TetrisBoard(int xOffset, int yOffset, int width, int height) {
        board = new Color[width][height];
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public boolean isValid(List<Point> solid) {
        for(Point point : solid) {
            if(board[point.getX()][point.getY()] != null) {
                return false;
            }
        }
        return true;
    }

    public void render(ShapeRenderer shapeRenderer) {
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y < board[x].length; y++) {
                if(board[x][y] != null) {
                    shapeRenderer.setColor(board[x][y]);
                    shapeRenderer.rect(xOffset + x * 32 + 2, yOffset + y * 32 + 2, 28, 28);
                }
            }
        }
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
