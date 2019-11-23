package net.mvw.delta.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

public class Resources {

    public static Texture logo_SpectralBit;
    public static Texture logo_ProjectDelta;
    public static Texture background;
    public static Texture side;
    public static Texture button;
    public static Texture button_f;
    public static Texture panda_neutral;
    public static Texture panda_eating;
    public static Texture panda_grumpy;
    public static Texture vertex;
    public static Texture banner;

    public static ArrayList<Texture> bamboos = new ArrayList<>();


    public static Music track_Magic;
    public static Music track_Odyssey;
    public static Music track_Tale;

    public static Music playingTrack;

    public static Sound sound_pop;
    public static Sound sound_click;
    public static Sound sound_munch;


    private static ArrayList<Disposable> disposables = new ArrayList<>();

    public static void loadResources() {
        logo_SpectralBit = new Texture("images/menu/spectralbit_256.png");
        logo_ProjectDelta = new Texture("images/menu/logo.png");
        background = new Texture("images/menu/background.png");
        side = new Texture("images/menu/side.png");
        button = new Texture("images/menu/button.png");
        button_f = new Texture("images/menu/button_f.png");
        panda_neutral = new Texture("images/pandas/panda_neutral.png");
        panda_eating = new Texture("images/pandas/panda_eating.png");
        vertex = new Texture("images/vertex.png");
        banner = new Texture("images/menu/banner.png");
        panda_grumpy = new Texture("images/pandas/panda_grumpy.png");

        disposables.add(logo_ProjectDelta);
        disposables.add(logo_SpectralBit);
        disposables.add(background);
        disposables.add(side);
        disposables.add(button);
        disposables.add(button_f);
        disposables.add(panda_neutral);
        disposables.add(panda_eating);
        disposables.add(panda_grumpy);
        disposables.add(vertex);

        for (int i = 1; i <= 3; i++) {
            Texture t = new Texture("images/bamboos/bamboo" + i + ".png");
            bamboos.add(t);
            disposables.add(t);
        }

        track_Magic = Gdx.audio.newMusic(Gdx.files.internal("sounds/track_Magic.mp3"));
        track_Odyssey = Gdx.audio.newMusic(Gdx.files.internal("sounds/track_Odyssey.mp3"));
        track_Tale = Gdx.audio.newMusic(Gdx.files.internal("sounds/track_Tale.mp3"));

        sound_pop = Gdx.audio.newSound(Gdx.files.internal("sounds/sound_pop.mp3"));
        sound_click = Gdx.audio.newSound(Gdx.files.internal("sounds/sound_click.mp3"));
        sound_munch = Gdx.audio.newSound(Gdx.files.internal("sounds/sound_munch.mp3"));


        disposables.add(track_Magic);
        disposables.add(track_Tale);
        disposables.add(track_Odyssey);

        disposables.add(sound_click);
        disposables.add(sound_munch);
        disposables.add(sound_pop);

    }

    public static void disposeResources() {
        for (Disposable d : disposables) {
            d.dispose();
        }
    }

    public static Music getRandomCalmTrack() {
        int x = (int) (Math.random() * 3);
        if (x == 1) {
            return track_Magic;
        } else if (x == 2) {
            return track_Odyssey;
        }
        return track_Tale;

    }


}
