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

/**
 * Represents a scalable UI entity.
 *
 * @author MyshaVoidWalker
 */
public class ScalableEntityUI {

    private static float menuButtonDistance = 60;

    public float targetZoom, fullZoom, speedOfScale,position;
    public boolean hovered = false;
    private Sprite sprite;
    private String text;
    public boolean modButton = false;
    private ArrayList<Global.GameState> stateOfAction = new ArrayList<>();
    private static float compensatorOffsetY=48;

    /**
     * Initializes a new instance of the ScalableEntityUI class.
     *
     * @param sprite The sprite of the entity.
     * @param text The text of the entity.
     * @param startZoom The start zoom.
     * @param targetZoom The target zoom.
     * @param fullZoom The full zoom.
     * @param speedOfScale The speed of scale.
     * @param position The position of the entity.
     * @param stateOfAction The state of the action.
     */
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


        this.getSprite().setPosition(SCREEN_WIDTH/2-this.sprite.getWidth()/2,SCREEN_HEIGHT/4*3-256 - position*menuButtonDistance -position*256);


    }

    /**
     * Changes the zoom to reach the target zoom.
     */
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