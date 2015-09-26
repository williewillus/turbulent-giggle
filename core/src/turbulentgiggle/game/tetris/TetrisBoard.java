package turbulentgiggle.game.tetris;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Quang on 9/26/2015.
 */
public class TetrisBoard {

    private int[][] board;

    public TetrisBoard(int width, int height) {
        board = new int[width][height];
    }

    public boolean isValid(ArrayList<Vector2> solid) {
        for(Vector2 vec : solid) {
            if(board[(int)vec.x][(int)vec.y] != -1) {

            }
        }
    }

}
