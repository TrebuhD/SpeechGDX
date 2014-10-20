package com.uam.kck.antminer;

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
            setX(0);
            setY(0);
            setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
            addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    MyActor actor = ((MyActor)event.getTarget());

                    actor.started = true;
                    Gdx.app.log(String.valueOf(actor.isStarted()), "smth");
                    Gdx.app.log("touchdown", "event");
                    return true;
                }
            });
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.draw(texture, getX(), getY());
        }

        @Override
        public void act(float delta) {
            if(started) {
                setX(getX()+5);
                setY(getY()+3);
            }
        }
    }

    @Override
    public void create() {
        gl = Gdx.app.getGraphics().getGL20();
        stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        MyActor myActor = new MyActor();
        myActor.setTouchable(Touchable.enabled);
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
