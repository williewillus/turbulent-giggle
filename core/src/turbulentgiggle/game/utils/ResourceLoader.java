package turbulentgiggle.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Quang on 9/26/2015.
 */
public class ResourceLoader {

    private static TextureAtlas game, gui;
    private static Skin skin;
    private static BitmapFont font;//font16, font24;

    public static void load() {
//        loadTextures();
        loadFonts();
//        loadSounds();
    }

    private static void loadTextures() {
//        game = new TextureAtlas(Gdx.files.internal("assets/textures/game.atlas"));
//        gui = new TextureAtlas(Gdx.files.internal("assets/textures/gui.atlas"));
//        skin = new Skin(Gdx.files.internal("assets/textures/skin.json"), gui);
    }

    private static void loadFonts() {
        font = new BitmapFont(Gdx.files.internal("assets/fonts/visitor32.fnt"));
//        font16 = new BitmapFont(Gdx.files.internal("assets/fonts/font16.fnt"));
//        font24 = new BitmapFont(Gdx.files.internal("assets/fonts/font24.fnt"));
    }

    private static void loadSounds() {

    }

    public static TextureRegion getTexture(String textureName) {
        return game.findRegion(textureName);
    }
    public static BitmapFont getFont() { return font; }


    public static void dispose() {
//        game.dispose();
//        gui.dispose();
//        skin.dispose();
        font.dispose();
    }
}
