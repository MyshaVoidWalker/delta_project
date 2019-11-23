package net.mvw.delta.states.controllers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.mvw.delta.entities.Bamboo;
import net.mvw.delta.entities.Panda;
import net.mvw.delta.input.InputBox;
import net.mvw.delta.input.Resources;
import net.mvw.delta.logic.SaveManager;

import java.util.ArrayList;
import java.util.Iterator;

import static net.mvw.delta.ProjectDelta.SCREEN_HEIGHT;
import static net.mvw.delta.ProjectDelta.SCREEN_WIDTH;
import static net.mvw.delta.ProjectDelta.gamefont;
import static net.mvw.delta.logic.Global.*;

public class GameController {

    public static ArrayList<Bamboo> bamboo_forest = new ArrayList<>();
    public static ArrayList<Panda> pandas = new ArrayList<>();
    public static ArrayList<Panda> traitors = new ArrayList<>();

    public static boolean needToShowTutorial = true;
    private static boolean isPanda = false;
    public static long lastPanda = System.nanoTime(), pandas_score = 0,textTimer = System.nanoTime();
    private static float score = 0;
    public static int level = 0;

    private static String displayText = "",secondaryText="";

    public static Sprite goldenBamboo, level_play_button, level_exit_button;

    public static void init() {

        goldenBamboo = new Sprite(Resources.golden_bamboo);
        goldenBamboo.setOrigin(0, 0);
        goldenBamboo.setScale(2);

        level_play_button = new Sprite(Resources.playWood);
        level_play_button.setOrigin(0, 0);
        level_play_button.setScale(4);

        level_exit_button = new Sprite(Resources.exitWood);
        level_exit_button.setOrigin(0, 0);
        level_exit_button.setScale(2);

        bamboo_forest.clear();
        if (needToShowTutorial) {

        }
    }

    public static void draw(SpriteBatch batch) {

        gamefont.getData().setScale(2f);
        batch.begin();


        if (state == GameState.GAME_PROGRESS) {
            if (level < 10) {
                displayText = "Parts: " + level + "/10";
            } else {
                displayText = "Golden bamboo completed!";

                batch.draw(Resources.panda_happy,128,SCREEN_HEIGHT/4,256,256);
                batch.draw(Resources.panda_happy,SCREEN_WIDTH-128,SCREEN_HEIGHT/4,-256,256);

            }

            goldenBamboo.draw(batch);
            level_play_button.draw(batch);

            layout.setText(gamefont,secondaryText);
            if(secondaryText!="" && System.nanoTime()-textTimer<10e8*2){
                gamefont.draw(batch,secondaryText,SCREEN_WIDTH/2-layout.width/2,SCREEN_HEIGHT/2+layout.height/2);
            }else {
                secondaryText = new String("");
            }

        }

        if (state == GameState.GAME) {
            for (Bamboo sprite : bamboo_forest) {
                if (sprite.getPanda() != null) {
                    sprite.getPanda().draw(batch);
                }
                sprite.draw(batch);
            }
            for (Panda traitor : traitors) {
                traitor.draw(batch);
            }
            if (level < 10) {
                displayText = "Score: " + pandas_score + (level<10?("/"+(level+1)*500):"");
            } else {
                displayText = "Endless:" + pandas_score;
            }

        }


        batch.draw(Resources.banner, 0, SCREEN_HEIGHT + InputBox.yScreenOffset - 256, SCREEN_WIDTH, 256);
        layout.setText(gamefont, displayText);
        gamefont.draw(batch, displayText, SCREEN_WIDTH / 2 - layout.width / 2, SCREEN_HEIGHT + InputBox.yScreenOffset - 128 + layout.height / 2);
        level_exit_button.draw(batch);
        batch.end();
    }

    private static long lastBamboo = 0, height_of_sticks = -128;

    public static void update() {

        if(state==GameState.GAME) {
            if (bamboo_forest.size() > 100 && pandas.size() < (level < 10 ? 11 - level : 2) && System.nanoTime() - lastPanda > 10e8) {
                int stick = (int) (Math.random() * bamboo_forest.size());
                Bamboo bamboo = bamboo_forest.get(stick);
                bamboo.setPanda(new Panda(Resources.panda_eating));
                bamboo.getPanda().setBamboo(bamboo);
                lastPanda = System.nanoTime();
                pandas.add(bamboo.getPanda());
                Resources.sound_munch.play(InputBox.masterSoundVolume);
            }

            if (bamboo_forest.size() < 400) {
                Bamboo b = new Bamboo(Resources.bamboos.get((int) (Math.random() * 3)), -20 + 40 * (float) (Math.random()),
                        -20 + 40 * (float) (Math.random()), 2f + (float) Math.random() * 2f, 2f + (float) Math.random() * 2f, 0);
                b.setPosition(64 + (float) (Math.random() * SCREEN_WIDTH - 128), -InputBox.yScreenOffset + height_of_sticks);
                bamboo_forest.add(0, b);
                height_of_sticks += 2;
            }


            Iterator<Panda> pandaIterator = pandas.iterator();
            while (pandaIterator.hasNext()) {
                Panda panda = pandaIterator.next();
                if (panda.toBeRemoved) {
                    pandas_score += (int) ((level + 1) * panda.getScaleX() * 10);
                    Panda traitor = new Panda(Resources.panda_grumpy);
                    traitor.setPosition(panda.getX(), panda.getY());
                    traitor.setScale(panda.getScaleX());
                    traitors.add(traitor);
                    pandaIterator.remove();
                    lastPanda = System.nanoTime();
                }
            }

            for (Panda panda : traitors) {
                panda.setPosition(panda.getX(), panda.getY() - 200 * getDelta());
                panda.setScale(panda.getScaleX() * 0.9f);
            }

            Iterator<Panda> traitorIterator = traitors.iterator();
            while (pandaIterator.hasNext()) {
                Panda panda = traitorIterator.next();
                if (panda.getScaleX() < 0.1) {
                    traitorIterator.remove();
                }
            }

            for (Bamboo sprite : bamboo_forest) {
                if (!pandas.contains(sprite.getPanda())) {
                    sprite.setPanda(null);
                }
                sprite.getToZoom(1);
                sprite.getToTargetAngle(4);
                if (sprite.getPanda() != null) {
                    sprite.getPanda().setPosition(sprite.getX() - (float) Math.sin(Math.toRadians(sprite.getRotation())) * (sprite.getHeight() - 32) * sprite.getScaleX(),
                            sprite.getY() + (float) Math.cos(Math.toRadians(sprite.getRotation())) * (sprite.getHeight() - 32) * sprite.getScaleY());
                    sprite.getPanda().setRotation(sprite.getRotation());
                    sprite.getPanda().setScale(sprite.getScaleX() / 4);
                }
            }
        }
        if (level < 10) {
            goldenBamboo.setColor((level + 1) * 0.09f, (level + 1) * 0.09f, (level + 1) * 0.09f, (level + 1) * 0.09f);
        } else {
            goldenBamboo.setColor(Color.WHITE);
        }
        goldenBamboo.setPosition(SCREEN_WIDTH / 2 - goldenBamboo.getWidth() / 2 * goldenBamboo.getScaleX(), -InputBox.yScreenOffset - 64);
        level_play_button.setPosition(SCREEN_WIDTH / 2 - level_play_button.getWidth() / 2 * level_play_button.getScaleX(), SCREEN_HEIGHT * 3 / 5);
        level_exit_button.setPosition(SCREEN_WIDTH + InputBox.xScreenOffset - level_exit_button.getWidth() * level_exit_button.getScaleX()-32, -InputBox.yScreenOffset+32);

        if(pandas_score>=(level+1)*500 && level<10){
            state = GameState.GAME_PROGRESS;
            level++;
            SaveManager.save();
            textTimer = System.nanoTime();
            secondaryText = "New part acquired";
        }

    }

    public static void resetByLevel(){
        bamboo_forest.clear();
        pandas.clear();
        pandas_score = 0;
        height_of_sticks = -128;
    }

}
