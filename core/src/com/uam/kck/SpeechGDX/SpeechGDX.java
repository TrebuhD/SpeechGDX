package com.uam.kck.SpeechGDX;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Main libGDX class.
 * Created by hubert on 20.10.14.
 */
public class SpeechGDX implements ApplicationListener {
    GL20 gl;
    ActionResolver actionResolver; // this exists to be able to call native Android methods.

    private Stage stage;
    private Skin skin;

    TextField inputTextField;
    TextArea botResponseTextField;
    MicButton micButton;

    public SpeechGDX(ActionResolver actionResolver) { this.actionResolver = actionResolver; }

    // Create the button:
    public class MicButton extends Actor {
        Texture texture = new Texture(Gdx.files.internal("mic.jpg"));

        public MicButton() {
            setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());

            addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    actionResolver.recognizeSpeech();
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

        float textBoxWidth = Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 10.0f;
        float offset = Gdx.graphics.getWidth() - textBoxWidth;

        inputTextField = new TextField("     ...", skin);
        inputTextField.setWidth(textBoxWidth);
        inputTextField.setHeight(80);
        inputTextField.setX(offset - offset / 2); // Centered
        inputTextField.setY(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 2f);

        botResponseTextField = new TextArea("\n I'm thinking...", skin);
        botResponseTextField.setWidth(textBoxWidth);
        botResponseTextField.setHeight(120);
        botResponseTextField.setPrefRows(2);
        botResponseTextField.setX(offset - offset / 2);
        botResponseTextField.setY(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 6.0f);
        botResponseTextField.setDisabled(true);

        micButton = new MicButton();
        micButton.setTouchable(Touchable.enabled);
        micButton.setPosition(inputTextField.getX() + inputTextField.getWidth() - micButton.getWidth(),
                inputTextField.getY() - micButton.getHeight());


        stage.addActor(inputTextField);
        stage.addActor(micButton);
        stage.addActor(botResponseTextField);

        actionResolver.showToast("Tap the mic icon to speak", 5000);
    }

    public void shakeMicButton() {
        Action shakeAction = Actions.repeat(2,
                (Actions.sequence(
                        Actions.moveBy(10, 0,0.05f),
                        Actions.moveBy(-10, 0,0.05f)
                )));
        micButton.addAction(Actions.sequence(shakeAction));
        micButton.act(Gdx.graphics.getDeltaTime());
    }

    public void setInputTextFieldText(String text) {
        inputTextField.setText(" " + text);
    }

    public void setBotResponseTextFieldText(String text) {
        botResponseTextField.setText(" " + text);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public void showToast(String message) {
        actionResolver.showToast(message, 5000);
    }
}
