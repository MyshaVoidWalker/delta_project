package net.mvw.delta.states.controllers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.mvw.delta.input.InputBox;

import static net.mvw.delta.ProjectDelta.SCREEN_HEIGHT;
import static net.mvw.delta.ProjectDelta.SCREEN_WIDTH;
import static net.mvw.delta.ProjectDelta.gamefont;
import static net.mvw.delta.ProjectDelta.viewportHeight;
import static net.mvw.delta.input.Resources.logo_SpectralBit;
import static net.mvw.delta.input.Resources.side;
import static net.mvw.delta.logic.Global.GameState.MENU_MAIN;
import static net.mvw.delta.logic.Global.getDelta;
import static net.mvw.delta.logic.Global.layout;
import static net.mvw.delta.logic.Global.state;


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

    private static float side_size = 256;
    public static void draw(SpriteBatch batch) {
        batch.begin();
        logo.draw(batch);
        gamefont.getData().setScale(2);
        layout.setText(gamefont,introText);
        gamefont.draw(batch,introText,SCREEN_WIDTH/2 - layout.width/2, SCREEN_HEIGHT/2 - logo.getTexture().getHeight() - layout.height*2);
        batch.draw(side, -side_size / 2 - (InputBox.xScreenOffset+side_size/2)*(float)((Math.PI-angleAlpha)/(Math.PI - Math.PI/alphaits)), -InputBox.yScreenOffset, side_size, viewportHeight);
        batch.draw(side, SCREEN_WIDTH - side_size / 2 + (InputBox.xScreenOffset+side_size/2)*(float)((Math.PI-angleAlpha)/(Math.PI - Math.PI/alphaits)), -InputBox.yScreenOffset, side_size, viewportHeight);
        batch.end();

        angleAlpha += (float)Math.PI/alphaits *getDelta();

        logo.setAlpha((float)Math.sin(angleAlpha));

        if(angleAlpha> Math.PI - Math.PI/alphaits){
            gamefont.getData().setScale(1);
            state = MENU_MAIN;
        }
    }
}
