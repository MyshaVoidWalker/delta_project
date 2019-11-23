package net.mvw.delta.input;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

@SuppressWarnings("LibGDXStaticResource")
public class Resources {

    public static Texture logo_SpectralBit;
    public static Texture logo_ProjectDelta;
    public static Texture background;
    public static Texture side;
    public static Texture button;
    public static Texture button_f;
    public static Texture panda_neutral;
    public static Texture vertex;
    public static  Texture banner;

    public static ArrayList<Texture> bamboos = new ArrayList<>();


//    public static Music track_dayOfChaos; // scorch
//    public static Music track_giantWyrm; // flood
//    public static Music track_jazzBrunch; // calm 1
//    public static Music track_nightOnTheDocs;// calm 2
//    public static Music track_poppersAndProsecco; // calm 3
//    public static Music track_smoothLovin; // calm 4
//    public static Music track_thisHouse; // storm
//
//    public static Music playingTrack;
//
//    public static Sound sound_pop;
//    public static Sound sound_build;
//    public static Sound sound_unlock;


    private static ArrayList<Disposable> disposables = new ArrayList<>();

    public static void loadResources() {
        logo_SpectralBit = new Texture("images/menu/spectralbit_256.png");
        logo_ProjectDelta = new Texture("images/menu/logo.png");
        background = new Texture("images/menu/background.png");
        side = new Texture("images/menu/side.png");
        button = new Texture("images/menu/button.png");
        button_f = new Texture("images/menu/button_f.png");
        panda_neutral = new Texture("images/pandas/panda_neutral.png");
        vertex = new Texture("images/vertex.png");
        banner = new Texture("images/menu/banner.png");

        disposables.add(logo_ProjectDelta);
        disposables.add(logo_SpectralBit);
        disposables.add(background);
        disposables.add(side);
        disposables.add(button);
        disposables.add(button_f);
        disposables.add(panda_neutral);
        disposables.add(vertex);

        for (int i = 1;i<=3;i++){
            Texture t = new Texture("images/bamboos/bamboo"+i+".png");
            bamboos.add(t);
            disposables.add(t);
        }


//        track_dayOfChaos = Gdx.audio.newMusic(Gdx.files.internal("music/Day of Chaos.mp3"));
//        track_giantWyrm = Gdx.audio.newMusic(Gdx.files.internal("music/Giant Wyrm.mp3"));
//        track_jazzBrunch = Gdx.audio.newMusic(Gdx.files.internal("music/Jazz Brunch.mp3"));
//        track_nightOnTheDocs = Gdx.audio.newMusic(Gdx.files.internal("music/Night on the Docks - Sax.mp3"));
//        track_poppersAndProsecco = Gdx.audio.newMusic(Gdx.files.internal("music/Poppers and Prosecco.mp3"));
//        track_smoothLovin = Gdx.audio.newMusic(Gdx.files.internal("music/Smooth Lovin.mp3"));
//        track_thisHouse = Gdx.audio.newMusic(Gdx.files.internal("music/This House.mp3"));
//
//        sound_pop = Gdx.audio.newSound(Gdx.files.internal("sounds/pop.mp3"));
//        sound_build = Gdx.audio.newSound(Gdx.files.internal("sounds/build.mp3"));
//        sound_unlock = Gdx.audio.newSound(Gdx.files.internal("sounds/unlock.mp3"));

    }

    public static void disposeResources() {
        for (Disposable d : disposables) {
            d.dispose();
        }
    }

    public static Music getRandomCalmTrack() {
//        int x = (int) (Math.random() * 4);
//        if (x == 1) {
//            return track_smoothLovin;
//        } else if (x == 2) {
//            return track_jazzBrunch;
//        } else if (x == 3) {
//            return track_poppersAndProsecco;
//        } else {
//            return track_nightOnTheDocs;
//        }
        return null;
    }


}
