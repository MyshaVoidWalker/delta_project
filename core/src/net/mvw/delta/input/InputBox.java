package net.mvw.delta.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import net.mvw.delta.entities.Panda;
import net.mvw.delta.logic.SaveManager;
import net.mvw.delta.states.controllers.GameController;

import java.util.Iterator;

import static net.mvw.delta.ProjectDelta.camera;
import static net.mvw.delta.logic.Global.GameState;
import static net.mvw.delta.logic.Global.GameState.GAME;
import static net.mvw.delta.logic.Global.GameState.GAME_PROGRESS;
import static net.mvw.delta.logic.Global.GameState.INTRO;
import static net.mvw.delta.logic.Global.GameState.MENU_ACHIEVEMENTS;
import static net.mvw.delta.logic.Global.GameState.MENU_CREDITS;
import static net.mvw.delta.logic.Global.GameState.MENU_EXTRAS;
import static net.mvw.delta.logic.Global.GameState.MENU_MAIN;
import static net.mvw.delta.logic.Global.GameState.MENU_OPTIONS;
import static net.mvw.delta.logic.Global.musicToggle;
import static net.mvw.delta.logic.Global.soundToggle;
import static net.mvw.delta.logic.Global.state;
import static net.mvw.delta.states.controllers.MenuController.aboutButton;
import static net.mvw.delta.states.controllers.MenuController.achievementsButton;
import static net.mvw.delta.states.controllers.MenuController.backButton;
import static net.mvw.delta.states.controllers.MenuController.creditsButton;
import static net.mvw.delta.states.controllers.MenuController.exitButton;
import static net.mvw.delta.states.controllers.MenuController.extrasButton;
import static net.mvw.delta.states.controllers.MenuController.musicToggleButtonOFF;
import static net.mvw.delta.states.controllers.MenuController.musicToggleButtonON;
import static net.mvw.delta.states.controllers.MenuController.optionsButton;
import static net.mvw.delta.states.controllers.MenuController.playButton;
import static net.mvw.delta.states.controllers.MenuController.scrollSetYCredits;
import static net.mvw.delta.states.controllers.MenuController.soundToggleButtonOFF;
import static net.mvw.delta.states.controllers.MenuController.soundToggleButtonON;
import static net.mvw.delta.input.Resources.*;

public class InputBox implements InputProcessor, GestureDetector.GestureListener {


    public static float xScreenOffset = 0;
    public static float yScreenOffset = 0;
    public static float masterSoundVolume = 1f, masterMusicVolume = 1f;
    private static Vector2 lastTouch = new Vector2();

    public static void setxScreenOffset(float xScreenOffset) {
        InputBox.xScreenOffset = xScreenOffset;
    }

    public static void setyScreenOffset(float yScreenOffset) {
        InputBox.yScreenOffset = yScreenOffset;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (masterMusicVolume < 1) {
            musicToggle = false;
        }

        if (masterSoundVolume < 1) {
            soundToggle = false;
        }

        Vector3 touchPos3d = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos3d);
        Vector2 touchPos = new Vector2(touchPos3d.x, touchPos3d.y);

        if (state.equals(INTRO)) {
            state = MENU_MAIN;
            return true;
        }

        if (state == MENU_CREDITS) {
            state = MENU_EXTRAS;
            scrollSetYCredits = 0;
            return true;
        }

        if (backButton.getSprite().getBoundingRectangle().contains(touchPos)) {
            SaveManager.save();
            switch (state) {
                case GAME:
                    state = GAME_PROGRESS;
                    break;
                case MENU_ACHIEVEMENTS:
                    state = MENU_OPTIONS;
                    break;
                default:
                    state = MENU_MAIN;
                    break;
            }
            sound_click.play(masterSoundVolume);
            return true;
        }

        if (playButton.getSprite().getBoundingRectangle().contains(touchPos)) {
            state = GAME;
            sound_click.play(masterSoundVolume);
            return true;
        }

        if (extrasButton.getSprite().getBoundingRectangle().contains(touchPos)) {
            state = MENU_EXTRAS;
            sound_click.play(masterSoundVolume);
            return true;
        }

        if (optionsButton.getSprite().getBoundingRectangle().contains(touchPos)) {
            state = MENU_OPTIONS;
            sound_click.play(masterSoundVolume);
            return true;
        }

        if (exitButton.getSprite().getBoundingRectangle().contains(touchPos)) {
            sound_click.play(masterSoundVolume);
            SaveManager.save();
            Gdx.app.exit();
            return true;
        }

        if (aboutButton.getSprite().getBoundingRectangle().contains(touchPos)) {
            Gdx.net.openURI("https://spectralbit.com/home");
            sound_click.play(masterSoundVolume);
            return true;
        }

        if (creditsButton.getSprite().getBoundingRectangle().contains(touchPos)) {
            state = MENU_CREDITS;
            sound_click.play(masterSoundVolume);
            return true;
        }

        if (achievementsButton.getSprite().getBoundingRectangle().contains(touchPos)) {
            state = MENU_ACHIEVEMENTS;
            sound_click.play(masterSoundVolume);
            return true;
        }

        if (soundToggleButtonON.getSprite().getBoundingRectangle().contains(touchPos)) {
            soundToggle = false;
            masterSoundVolume = 0f;
            sound_click.play(masterSoundVolume);
            return true;
        }

        if (soundToggleButtonOFF.getSprite().getBoundingRectangle().contains(touchPos)) {
            soundToggle = true;
            masterSoundVolume = 1f;
            sound_click.play(masterSoundVolume);
            return false;
        }

        if (musicToggleButtonON.getSprite().getBoundingRectangle().contains(touchPos)) {
            musicToggle = false;
            masterMusicVolume = 0f;
            playingTrack.setVolume(masterMusicVolume);
            sound_click.play(masterSoundVolume);
            return true;
        }

        if (musicToggleButtonOFF.getSprite().getBoundingRectangle().contains(touchPos)) {
            musicToggle = true;
            masterMusicVolume = 1f;

            playingTrack.setVolume(masterMusicVolume);
            sound_click.play(masterSoundVolume);
            return true;
        }

        if (state == GameState.GAME) {
            Iterator<Panda> pandaIterator = GameController.pandas.iterator();
            while(pandaIterator.hasNext()){
                Panda panda = pandaIterator.next();
                if (panda.getBoundingRectangle().contains(touchPos)) {
                    System.out.println("Got panda");
                   panda.toBeRemoved = true;
                    sound_pop.play(masterSoundVolume);
                }
            }

            return true;
        }

        return false;
    }

    public static void continualInputs() {

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACKSPACE || keycode == Input.Keys.BACK) {
            SaveManager.save();
            switch (state) {
                case GAME:
                    state = MENU_MAIN;
                    break;
                default:
                    state = MENU_MAIN;
                    break;
            }
            sound_click.play(masterSoundVolume);
        }


        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastTouch.set(screenX, screenY);
        return false;
    }

    private static float xVelocity, yVelocity;

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 newTouch = new Vector2(screenX, screenY);
        Vector2 delta = newTouch.cpy().sub(lastTouch);
        xVelocity = delta.x;
        yVelocity = delta.y;
        lastTouch = newTouch;
        return false;
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector3 touchPos3d = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos3d);
        Vector2 touchPos = new Vector2(touchPos3d.x, touchPos3d.y);

        if (state == MENU_MAIN) {
            if (playButton.getSprite().getBoundingRectangle().contains(touchPos)) {
                playButton.hovered = true;
                optionsButton.hovered = false;
                extrasButton.hovered = false;
                exitButton.hovered = false;
                return true;
            } else {
                playButton.hovered = false;
            }

            if (extrasButton.getSprite().getBoundingRectangle().contains(touchPos)) {
                extrasButton.hovered = true;
                playButton.hovered = false;
                optionsButton.hovered = false;
                exitButton.hovered = false;
                return true;
            } else {
                extrasButton.hovered = false;
            }

            if (optionsButton.getSprite().getBoundingRectangle().contains(touchPos)) {
                optionsButton.hovered = true;
                exitButton.hovered = false;
                return true;
            } else {
                optionsButton.hovered = false;
            }

            if (exitButton.getSprite().getBoundingRectangle().contains(touchPos)) {
                exitButton.hovered = true;
                return true;
            } else {
                exitButton.hovered = false;
            }
        }

        if (state == MENU_EXTRAS) {

            if (backButton.getSprite().getBoundingRectangle().contains(touchPos)) {
                backButton.hovered = true;
                aboutButton.hovered = false;
                return true;
            } else {
                backButton.hovered = false;
            }

            if (achievementsButton.getSprite().getBoundingRectangle().contains(touchPos)) {
                achievementsButton.hovered = true;
                return true;
            } else {
                achievementsButton.hovered = false;
            }

            if (aboutButton.getSprite().getBoundingRectangle().contains(touchPos)) {
                aboutButton.hovered = true;
                creditsButton.hovered = false;
                return true;
            } else {
                aboutButton.hovered = false;
            }

            if (creditsButton.getSprite().getBoundingRectangle().contains(touchPos)) {
                creditsButton.hovered = true;
                aboutButton.hovered = false;
                backButton.hovered = false;
                return true;
            } else {
                creditsButton.hovered = false;
            }
        }

        if (state == MENU_OPTIONS) {

            if (backButton.getSprite().getBoundingRectangle().contains(touchPos)) {
                backButton.hovered = true;
                musicToggleButtonOFF.hovered = false;
                musicToggleButtonON.hovered = false;
                return true;
            } else {
                backButton.hovered = false;
            }

            if (soundToggleButtonON.getSprite().getBoundingRectangle().contains(touchPos)) {
                soundToggleButtonON.hovered = true;
                musicToggleButtonOFF.hovered = false;
                musicToggleButtonON.hovered = false;
                return true;
            } else {
                soundToggleButtonON.hovered = false;
            }

            if (soundToggleButtonOFF.getSprite().getBoundingRectangle().contains(touchPos)) {
                soundToggleButtonOFF.hovered = true;
                musicToggleButtonOFF.hovered = false;
                musicToggleButtonON.hovered = false;
                return true;
            } else {
                soundToggleButtonOFF.hovered = false;
            }

            if (musicToggleButtonON.getSprite().getBoundingRectangle().contains(touchPos)) {
                musicToggleButtonON.hovered = true;
                soundToggleButtonON.hovered = false;
                soundToggleButtonOFF.hovered = false;
                backButton.hovered = false;
                return true;
            } else {
                musicToggleButtonON.hovered = false;
            }

            if (musicToggleButtonOFF.getSprite().getBoundingRectangle().contains(touchPos)) {
                musicToggleButtonOFF.hovered = true;
                soundToggleButtonON.hovered = false;
                soundToggleButtonOFF.hovered = false;
                backButton.hovered = false;
                return true;
            } else {
                musicToggleButtonOFF.hovered = false;
            }

        }

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    float deltaDist = 0;
    @Override
    public boolean zoom(float initialDistance, float distance) {
        deltaDist = distance - initialDistance;
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {}
}
