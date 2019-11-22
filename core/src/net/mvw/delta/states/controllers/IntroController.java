package net.mvw.delta.states.controllers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static net.mvw.delta.input.Resources.*;
import static net.mvw.delta.ProjectDelta.*;
import static net.mvw.delta.logic.Global.*;
import static net.mvw.delta.logic.Global.GameState.*;


public class IntroController {

    private static float angleAlpha = 0;
    private static String introText = "SpectralBit presents:";
    private static Sprite logo;
    private static float alphaits = 8f;


    public static void init() {
        angleAlpha += (float)Math.PI/alphaits;
        logo = new Sprite(logo_SpectralBit);
        logo.setPosition(SCREEN_WIDTH/2 - logo_SpectralBit.getWidth()/2f, SCREEN_HEIGHT/2 - logo_SpectralBit.getHeight()/2f);
        logo.scale(2f);
        gamefont.getData().setScale(2f);
        layout.setText(gamefont,introText);
        logo.setAlpha((float)Math.sin(angleAlpha));

    }

    /**
     * Draws the intro state.
     *
     * @param batch The batch to draw on.
     */
    public static void draw(SpriteBatch batch) {

        batch.begin();
        logo.draw(batch);
        gamefont.getData().setScale(2);
        layout.setText(gamefont,introText);
        gamefont.draw(batch,introText,SCREEN_WIDTH/2 - layout.width/2, SCREEN_HEIGHT/2 - logo.getTexture().getHeight() - layout.height*2);
        batch.end();

        angleAlpha += (float)Math.PI/alphaits *getDelta();

        logo.setAlpha((float)Math.sin(angleAlpha));

        if(angleAlpha> Math.PI - Math.PI/alphaits){
            gamefont.getData().setScale(1);
            state = MENU_MAIN;
        }
    }
}
