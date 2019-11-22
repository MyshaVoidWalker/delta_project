package net.mvw.delta.states.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import net.mvw.delta.entities.ScalableEntityUI;
import net.mvw.delta.input.Resources;
import net.mvw.delta.logic.Global;

import java.util.ArrayList;

import static net.mvw.delta.ProjectDelta.SCREEN_HEIGHT;
import static net.mvw.delta.ProjectDelta.SCREEN_WIDTH;
import static net.mvw.delta.ProjectDelta.camera;
import static net.mvw.delta.ProjectDelta.gamefont;
import static net.mvw.delta.logic.Global.GameState.MENU_ACHIEVEMENTS;
import static net.mvw.delta.logic.Global.GameState.MENU_CREDITS;
import static net.mvw.delta.logic.Global.GameState.MENU_EXTRAS;
import static net.mvw.delta.logic.Global.GameState.MENU_MAIN;
import static net.mvw.delta.logic.Global.GameState.MENU_OPTIONS;
import static net.mvw.delta.logic.Global.getDelta;
import static net.mvw.delta.logic.Global.layout;
import static net.mvw.delta.logic.Global.state;

public class MenuController {


    private static float menuButtonZoom = 2.5f;
    public static float menuButtonZoomSpeed = 2;

    private static String[] creditsString = {"Product of SpectralBit 2019", "Designed by SpectralBit Team", "", "Code and Graphics:", "Mihai Apolschi", "", "Music by Kevin MacLeod"};

    public static boolean medalHover = false;
    public static float hX = 0, hY = 0;
    public static String hMessage = "";


    //MENU_MAIN
    public static ScalableEntityUI playButton;
    public static ScalableEntityUI optionsButton;
    public static ScalableEntityUI extrasButton;
    public static ScalableEntityUI exitButton;

    //MENU_OPTIONS
    public static ScalableEntityUI soundToggleButtonON;
    public static ScalableEntityUI musicToggleButtonON;
    public static ScalableEntityUI soundToggleButtonOFF;
    public static ScalableEntityUI musicToggleButtonOFF;

    //MENU_EXTRAS
    public static ScalableEntityUI achievementsButton;
    public static ScalableEntityUI creditsButton;
    public static ScalableEntityUI aboutButton;

    public static ScalableEntityUI backButton;

    public static ArrayList<ScalableEntityUI> uiList = new ArrayList<ScalableEntityUI>();

    public static Sprite logoSprite;

    /**
     * Initializes the menu.
     */
    public static void init() {

        playButton = new ScalableEntityUI(new Sprite(Resources.button), "Play", 0, 0, menuButtonZoom, menuButtonZoomSpeed, 0, MENU_MAIN);
        optionsButton = new ScalableEntityUI(new Sprite(Resources.button_f), "Options", 0, 0, menuButtonZoom, menuButtonZoomSpeed, 1, MENU_MAIN);
        extrasButton = new ScalableEntityUI(new Sprite(Resources.button), "Extras", 0, 0, menuButtonZoom, menuButtonZoomSpeed, 2, MENU_MAIN);
        exitButton = new ScalableEntityUI(new Sprite(Resources.button_f), "Exit", 0, 0, menuButtonZoom, menuButtonZoomSpeed, 3, MENU_MAIN);

        soundToggleButtonON = new ScalableEntityUI(new Sprite(Resources.button), "Sound: ON", 0, 0, menuButtonZoom, menuButtonZoomSpeed, 1, MENU_OPTIONS);
        musicToggleButtonON = new ScalableEntityUI(new Sprite(Resources.button_f), "Music: ON", 0, 0, menuButtonZoom, menuButtonZoomSpeed, 2, MENU_OPTIONS);

        soundToggleButtonOFF = new ScalableEntityUI(new Sprite(Resources.button_f), "Sound: OFF", 0, 0, menuButtonZoom, menuButtonZoomSpeed, 1, MENU_OPTIONS);
        musicToggleButtonOFF = new ScalableEntityUI(new Sprite(Resources.button), "Music: OFF", 0, 0, menuButtonZoom, menuButtonZoomSpeed, 2, MENU_OPTIONS);

        achievementsButton = new ScalableEntityUI(new Sprite(Resources.button), "Achievements", 0, 0, menuButtonZoom, menuButtonZoomSpeed, 0, MENU_EXTRAS);
        aboutButton = new ScalableEntityUI(new Sprite(Resources.button_f), "About Us", 0, 0, menuButtonZoom, menuButtonZoomSpeed, 1, MENU_EXTRAS);
        creditsButton = new ScalableEntityUI(new Sprite(Resources.button), "Credits", 0, 0, menuButtonZoom, menuButtonZoomSpeed, 2, MENU_EXTRAS);

        backButton = new ScalableEntityUI(new Sprite(Resources.button_f), "Back", 0, 0, menuButtonZoom / 2, menuButtonZoomSpeed, 3, MENU_EXTRAS, MENU_OPTIONS, MENU_ACHIEVEMENTS);

        uiList.add(backButton);

        uiList.add(playButton);
        uiList.add(optionsButton);
        uiList.add(extrasButton);
        uiList.add(exitButton);

        uiList.add(musicToggleButtonON);
        uiList.add(musicToggleButtonOFF);
        uiList.add(soundToggleButtonON);
        uiList.add(soundToggleButtonOFF);

        uiList.add(achievementsButton);

        uiList.add(creditsButton);
        uiList.add(aboutButton);

        logoSprite = new Sprite(Resources.logo_ProjectDelta);
        logoSprite.setScale(2);
        logoSprite.setPosition(SCREEN_WIDTH / 2 - logoSprite.getWidth() / 2, SCREEN_HEIGHT - logoSprite.getHeight());

    }

    /**
     * Draws the menu.
     *
     * @param batch The batch to draw on.
     */
    public static void draw(SpriteBatch batch) {

        batch.begin();

        for (ScalableEntityUI e : uiList) {
            e.getSprite().draw(batch);
        }

        logoSprite.draw(batch);

        if (state == MENU_CREDITS) {
            int sIndex = 0;
            for (String s : creditsString) {
                gamefont.getData().setScale(2f);
                layout.setText(gamefont, s);
                gamefont.draw(batch, s, SCREEN_WIDTH / 2 - layout.width / 2, scrollSetYCredits - sIndex++ * (layout.height + 16));
            }
            if (scrollSetYCredits > SCREEN_HEIGHT + (sIndex * 80)) {
                state = MENU_EXTRAS;
                scrollSetYCredits = 0;
            }
            scrollSetYCredits += 128 * getDelta();
        }

        if (state == MENU_ACHIEVEMENTS) {

        }


        batch.end();
    }


    public static float scrollSetYCredits = 0;

    /**
     * Updates the menu.
     */
    public static void update() {

        Vector3 touchPos3d = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos3d);
        Vector2 touchPos = new Vector2(touchPos3d.x, touchPos3d.y);


        for (ScalableEntityUI e : uiList) {

            if (Global.state == Global.GameState.MENU_OPTIONS) {
                if ((e.equals(musicToggleButtonON) && Global.musicToggle) || (e.equals(musicToggleButtonOFF) && !Global.musicToggle)
                        || (e.equals(soundToggleButtonON) && Global.soundToggle) || (e.equals(soundToggleButtonOFF) && !Global.soundToggle)
                        || (e.equals(backButton))) {
                    e.speedOfScale = menuButtonZoomSpeed / 4;
                    if (e.hovered) e.targetZoom = e.fullZoom + 0.2f;
                    else e.targetZoom = e.fullZoom;
                } else {
                    e.speedOfScale = menuButtonZoomSpeed / 16;
                    e.targetZoom = 0;
                }
            } else {

                if (e.getStateOfAction().equals(Global.state)) {
                    e.speedOfScale = menuButtonZoomSpeed / 4;
                    if (e.hovered) e.targetZoom = e.fullZoom + 0.2f;
                    else e.targetZoom = e.fullZoom;
                } else {
                    e.speedOfScale = menuButtonZoomSpeed / 16;
                    e.targetZoom = 0;
                }
            }
            e.getTotargetZoom();
        }


    }


}
