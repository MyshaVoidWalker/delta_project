package net.mvw.delta.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Contains methods and fields used for working with resources.
 *
 * @author MyshaVoidWalker
 */
public class Resources {

    /**
     * Textures
     */
    public static Texture logo_SpectralBit;
    public static Texture logo_ProjectGamma;
    public static Texture vertex;
    public static Texture planet;
    public static Texture fade_aura;
    public static Texture blank;
    public static Texture sun;
    public static Texture icon_human;
    public static Texture icon_eco;
    public static Texture icon_temp;
    public static Texture icon_power;
    public static Texture icon_food;
    public static Texture icon_materials;
    public static Texture icon_fuel;
    public static Texture icon_build;
    public static Texture icon_recycle;
    public static Texture icon_upgrade;
    public static Texture icon_back;
    public static Texture icon_progress;
    public static Texture icon_clock;
    public static Texture leftArrow;
    public static Texture rightArrow;
    public static Texture bigPanel;
    public static Texture pillPanel;
    public static Texture buildPanel;
    public static Texture panel;
    public static Texture panel_grass;
    public static Texture pill;
    public static Texture starTexture;
    public static Texture medal_Scorch;
    public static Texture medal_Flood;
    public static Texture medal_Storm;
    public static Texture softshade;
    public static Texture sat0;
    public static Texture sat1;

    public static ArrayList<Texture> cloudTextures = new ArrayList<Texture>();
    public static ArrayList<Texture> natureTextures = new ArrayList<Texture>();
    public static ArrayList<Texture> humansTextures = new ArrayList<Texture>();
    public static ArrayList<Texture> facesTextures = new ArrayList<Texture>();

    public static Music track_dayOfChaos; // scorch
    public static Music track_giantWyrm; // flood
    public static Music track_jazzBrunch; // calm 1
    public static Music track_nightOnTheDocs;// calm 2
    public static Music track_poppersAndProsecco; // calm 3
    public static Music track_smoothLovin; // calm 4
    public static Music track_thisHouse; // storm

    public static Music playingTrack;

    public static Sound sound_pop;
    public static Sound sound_build;
    public static Sound sound_unlock;

    /**
     * Loads all resources.
     */
    public static void loadResources() {
        logo_SpectralBit = new Texture("menu/spectralbit_256.png");
        logo_ProjectGamma = new Texture("menu/logo.png");
        vertex = new Texture("vertex.png");
        planet = new Texture("menu/planet.png");
        fade_aura = new Texture("menu/fade.png");
        blank = new Texture("menu/blank.png");
        sun = new Texture("menu/sun.png");
        icon_human = new Texture("menu/icon_humans.png");
        icon_power = new Texture("menu/icon_power.png");
        icon_eco = new Texture("menu/icon_eco.png");
        icon_temp = new Texture("menu/icon_temp.png");
        icon_fuel = new Texture("menu/icon_fuel.png");
        icon_food = new Texture("menu/icon_food.png");
        icon_materials = new Texture("menu/icon_materials.png");
        icon_build = new Texture("menu/build.png");
        icon_recycle = new Texture("menu/remove.png");
        icon_upgrade = new Texture("menu/upgrade.png");
        icon_back = new Texture("menu/back.png");
        icon_progress = new Texture("menu/progress.png");
        icon_clock = new Texture("menu/icon_clock.png");
        pill = new Texture("menu/pill.png");
        starTexture = new Texture("menu/star.png");

        leftArrow = new Texture("menu/left.png");
        rightArrow = new Texture("menu/right.png");
        buildPanel = new Texture("menu/buildPanel.png");
        bigPanel = new Texture("menu/bigPanel.png");
        pillPanel = new Texture("menu/pillPanel.png");
        panel = new Texture("menu/panel.png");
        panel_grass = new Texture("menu/panel_grass.png");

        medal_Scorch = new Texture("menu/a_scorch.png");
        medal_Flood = new Texture("menu/a_flood.png");
        medal_Storm = new Texture("menu/a_storm.png");

        softshade = new Texture("softshade.png");

        sat0 = new Texture("clouds/sat0.png");
        sat1 = new Texture("clouds/sat1.png");

        for (int i = 0; i < 7; i++) {
            cloudTextures.add(new Texture("clouds/cloud" + i + ".png"));
        }
        for (int i = 0; i < 12; i++) {
            natureTextures.add(new Texture("nature/nature" + i + ".png"));
        }
        for (int i = 1; i < 18; i++) {
                humansTextures.add(new Texture("humans/h"+i + ".png"));
        }
        for (int i = 0; i < 6; i++) {
            facesTextures.add(new Texture("faces/f" + i + ".png"));
        }

        track_dayOfChaos = Gdx.audio.newMusic(Gdx.files.internal("music/Day of Chaos.mp3"));
        track_giantWyrm = Gdx.audio.newMusic(Gdx.files.internal("music/Giant Wyrm.mp3"));
        track_jazzBrunch = Gdx.audio.newMusic(Gdx.files.internal("music/Jazz Brunch.mp3"));
        track_nightOnTheDocs = Gdx.audio.newMusic(Gdx.files.internal("music/Night on the Docks - Sax.mp3"));
        track_poppersAndProsecco = Gdx.audio.newMusic(Gdx.files.internal("music/Poppers and Prosecco.mp3"));
        track_smoothLovin = Gdx.audio.newMusic(Gdx.files.internal("music/Smooth Lovin.mp3"));
        track_thisHouse = Gdx.audio.newMusic(Gdx.files.internal("music/This House.mp3"));

        sound_pop = Gdx.audio.newSound(Gdx.files.internal("sounds/pop.mp3"));
        sound_build = Gdx.audio.newSound(Gdx.files.internal("sounds/build.mp3"));
        sound_unlock = Gdx.audio.newSound(Gdx.files.internal("sounds/unlock.mp3"));
    }


    /**
     * Disposes all resources.
     */
    public static void disposeResources() {
        logo_SpectralBit.dispose();
        logo_ProjectGamma.dispose();
        vertex.dispose();
        planet.dispose();
        fade_aura.dispose();
        blank.dispose();
        sun.dispose();

        icon_materials.dispose();
        icon_food.dispose();
        icon_fuel.dispose();
        icon_temp.dispose();
        icon_power.dispose();
        icon_eco.dispose();
        icon_human.dispose();
        icon_build.dispose();
        icon_upgrade.dispose();
        icon_recycle.dispose();
        icon_back.dispose();
        icon_clock.dispose();

        medal_Flood.dispose();
        medal_Scorch.dispose();
        medal_Storm.dispose();

        rightArrow.dispose();
        leftArrow.dispose();
        buildPanel.dispose();
        bigPanel.dispose();
        pillPanel.dispose();
        panel.dispose();
        panel_grass.dispose();
        softshade.dispose();

        pill.dispose();
        starTexture.dispose();
        sat0.dispose();
        sat1.dispose();

        for (int i = 0; i < 7; i++) {
            cloudTextures.get(i).dispose();
        }
        for (int i = 0; i < 12; i++) {
            natureTextures.get(i).dispose();
        }
        for (int i = 0; i < humansTextures.size(); i++) {
            humansTextures.get(i).dispose();
        }
        for (int i = 0; i < 6; i++) {
            facesTextures.get(i).dispose();
        }

        track_poppersAndProsecco.dispose();
        track_smoothLovin.dispose();
        track_nightOnTheDocs.dispose();
        track_thisHouse.dispose();
        track_jazzBrunch.dispose();
        track_giantWyrm.dispose();
        track_dayOfChaos.dispose();

        sound_pop.dispose();
        sound_build.dispose();
        sound_unlock.dispose();

    }

    public static Music getRandomCalmTrack() {
        int x = (int) (Math.random() * 4);
        if (x == 1) {
            return track_smoothLovin;
        } else if (x == 2) {
            return track_jazzBrunch;
        } else if (x == 3) {
            return track_poppersAndProsecco;
        } else {
            return track_nightOnTheDocs;
        }
    }


}
