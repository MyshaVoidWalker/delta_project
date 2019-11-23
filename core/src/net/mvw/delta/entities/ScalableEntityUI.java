package net.mvw.delta.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;

import net.mvw.delta.ProjectDelta;
import net.mvw.delta.logic.Global;

import java.util.ArrayList;

import static net.mvw.delta.ProjectDelta.SCREEN_HEIGHT;
import static net.mvw.delta.ProjectDelta.SCREEN_WIDTH;
import static net.mvw.delta.logic.Global.GameState;
import static net.mvw.delta.logic.Global.getDelta;
import static net.mvw.delta.logic.Global.layout;
import static net.mvw.delta.logic.Global.state;

public class ScalableEntityUI {

    public static float menuButtonDistance = 32;

    public float targetZoom, fullZoom, speedOfScale,position;
    public boolean hovered = false;
    private Sprite sprite;
    private String text;
    public boolean modButton = false;
    private ArrayList<Global.GameState> stateOfAction = new ArrayList<>();
    private static float compensatorOffsetY=48;

    public ScalableEntityUI(Sprite sprite, String text, float startZoom, float targetZoom, float fullZoom, float speedOfScale, float position, GameState... stateOfAction) {
        this.sprite = sprite;
        this.sprite.setScale(startZoom);
        this.targetZoom = targetZoom;
        this.fullZoom = fullZoom;
        this.speedOfScale = speedOfScale;
        this.position = position;

        for (GameState stateofElement : stateOfAction) {
            this.stateOfAction.add(stateofElement);
        }
        if (text != null) setText(text);


        this.getSprite().setPosition(SCREEN_WIDTH/2-this.sprite.getWidth()/2,SCREEN_HEIGHT/4*3-384 - position*menuButtonDistance -position*192);


    }

    public void getTotargetZoom() {

        this.getSprite().setScale(this.getSprite().getScaleX() + (targetZoom - this.getSprite().getScaleX()) / speedOfScale * getDelta());

    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        createNewTexture();

    }

    private void createNewTexture() {

        FrameBuffer fbo;
        TextureRegion textedTexture;

        Texture currentTexture = sprite.getTexture();

        SpriteBatch batch = new SpriteBatch();
        fbo = new FrameBuffer(Pixmap.Format.RGBA8888, currentTexture.getWidth(), currentTexture.getHeight(), false);
        Matrix4 m = new Matrix4();
        m.setToOrtho2D(0, 0, fbo.getWidth(), fbo.getHeight());
        batch.setProjectionMatrix(m);


        fbo.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(0,0,0,0);
        batch.begin();
        batch.draw(currentTexture, 0, currentTexture.getHeight(), currentTexture.getWidth(), -currentTexture.getHeight());
        layout.setText(ProjectDelta.gamefontfliped, text);
        ProjectDelta.gamefontfliped.draw(batch, text, currentTexture.getWidth() / 2f - layout.width / 2, currentTexture.getHeight() / 2f-layout.height/2);
        batch.end();
        fbo.end();
        textedTexture = new TextureRegion(fbo.getColorBufferTexture());
        //textedTexture.flip(true, true);

        sprite.setTexture(textedTexture.getTexture());

    }

    public GameState getStateOfAction() {
        for (GameState stateofElement : stateOfAction) {
            if (stateofElement.equals(state)) {
                return stateofElement;
            }
        }
        return GameState.NEUTRAL;
    }

}