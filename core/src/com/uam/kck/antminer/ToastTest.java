package com.uam.kck.antminer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by hubert on 19.10.14.
 */
public class ToastTest implements ApplicationListener, InputProcessor {
    GL20 gl;
    ActionResolver actionResolver;

    public static int myListVar = 0;

    public ToastTest(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
    }


    @Override
    public void create() {
        gl = Gdx.app.getGraphics().getGL20();

        actionResolver.showToast("Tap screen to open AlertBox!", 5000);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {    }

    @Override
    public void render() {
        gl.glClearColor(1, 1, 1, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.MENU:
                actionResolver.showMyList();
                return true;
        }
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        actionResolver.showAlertBox("AlertBox title", "AlertBox message", "Button Text");
        actionResolver.openUri("http://google.com");
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
