package com.uam.kck.antminer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by hubert on 20.10.14.
 */
public class SceneTest implements ApplicationListener {
    GL20 gl;
    ActionResolver actionResolver;
    private Stage stage;

    public SceneTest(ActionResolver actionResolver) { this.actionResolver = actionResolver; }

    public class MyActor extends Actor {
        Texture texture = new Texture(Gdx.files.internal("ant.jpg"));
        public boolean started = false;

        public boolean isStarted() {
            return started;
        }

        public MyActor() {
            setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
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
        Gdx.input.setInputProcessor(stage);

        MyActor myActor = new MyActor();

        MoveToAction moveAction = new MoveToAction();
        RotateToAction rotateAction = new RotateToAction();
        ScaleToAction scaleAction = new ScaleToAction();

        moveAction.setPosition(800f, 200f);
        moveAction.setDuration(5f);
        rotateAction.setRotation(90f);
        rotateAction.setDuration(5f);
        scaleAction.setScale(10f);
        scaleAction.setDuration(5f);

        myActor.addAction(moveAction);
        myActor.addAction(rotateAction);
        myActor.addAction(scaleAction);

        stage.addActor(myActor);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void render() {
        gl.glClearColor(1, 1, 1, 1);
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
}
