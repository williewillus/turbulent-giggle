package turbulentgiggle.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
    private static BitmapFont font32, font20;//font16, font20;
    private static Music music;

    public static void load() {
//        loadTextures();
        loadFonts();
        loadSounds();
    }

    private static void loadTextures() {
//        game = new TextureAtlas(Gdx.files.internal("assets/textures/game.atlas"));
//        gui = new TextureAtlas(Gdx.files.internal("assets/textures/gui.atlas"));
//        skin = new Skin(Gdx.files.internal("assets/textures/skin.json"), gui);
    }

    private static void loadFonts() {
        font32 = new BitmapFont(Gdx.files.internal("assets/fonts/visitor32.fnt"));
        font20 = new BitmapFont(Gdx.files.internal("assets/fonts/visitor20.fnt"));
//        font16 = new BitmapFont(Gdx.files.internal("assets/fonts/font16.fnt"));
//        font20 = new BitmapFont(Gdx.files.internal("assets/fonts/font20.fnt"));
    }

    private static void loadSounds() {
        music = Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/bg.mp3"));
    }

    public static TextureRegion getTexture(String textureName) {
        return game.findRegion(textureName);
    }
    public static BitmapFont getFont() { return font32; }
    public static BitmapFont getSmallFont() {return font20;}
    public static Music getSound() {return music;}

    public static void dispose() {
//        game.dispose();
//        gui.dispose();
//        skin.dispose();
        font32.dispose();
        font20.dispose();
        music.dispose();
    }
}
