package com.uam.kck.SpeechGDX;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by hubert on 20.10.14.
 */
public class SpeechGDX implements ApplicationListener {
    GL20 gl;
    ActionResolver actionResolver; // this exists to be able to call native Android methods.

    // We create the main stage:
    private Stage stage;
    private Skin skin;

    TextField textField;

    public SpeechGDX(ActionResolver actionResolver) { this.actionResolver = actionResolver; }

    // We create an actor to perform on our stage:
    public class MicButton extends Actor {
        Texture texture = new Texture(Gdx.files.internal("mic.jpg"));

        public MicButton() {
            setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
            setPosition(Gdx.graphics.getWidth()/2f - texture.getWidth() / 2, 100);

            addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    actionResolver.showSpeechPopup();
                    return true;
                }
            });
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
                    getHeight(), getScaleX(), getScaleY(), getRotation(), 0, 0,
                    texture.getWidth(), texture.getHeight(), false, false);
        }

    }

    @Override
    public void create() {
        gl = Gdx.app.getGraphics().getGL20();
        stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("Scene2D/uiskin.json"));

        Gdx.input.setInputProcessor(stage);

        MicButton micButton = new MicButton();
        micButton.setTouchable(Touchable.enabled);

        textField = new TextField("\tTap the mic icon to speak", skin);
        textField.setX(Gdx.graphics.getWidth() / 2 - textField.getWidth() * 2);
        textField.setY(Gdx.graphics.getHeight() - 300);
        textField.setWidth(500);
        textField.setHeight(80);

        textField.setDisabled(true);

        stage.addActor(textField);
        stage.addActor(micButton);

    }

    public void setTextFieldText(String text) {
        textField.setText(" " + text);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void render() {
        gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        // Debug methods:
        //Gdx.app.log("X", "FPS: " + Gdx.graphics.getFramesPerSecond());
        //SpriteBatch spriteBatch = (SpriteBatch)stage.getBatch();
        //Gdx.app.log("X", "render calls: " + spriteBatch.totalRenderCalls);
        //spriteBatch.totalRenderCalls = 0;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
