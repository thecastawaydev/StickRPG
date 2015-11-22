package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by rooo on 11/16/2015.
 */
public class StartMap implements Screen {
    final MainGame game;

    int mapWidth = 100;
    int mapHeight = 100;

    Rectangle rect;

    Sprite map;
    ShapeRenderer sp;

    public StartMap(final MainGame gam){
        game = gam;

        sp = new ShapeRenderer();
        map = new Sprite(new Texture("map.png"));
        map.setPosition(0, 0);
        map.setSize(mapWidth, mapHeight);

        rect = new Rectangle(14, 29, 5, 5);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawGame();
        drawGui();
    }

    private void drawGame(){

        game.cameraHelper.cam.position.set(game.player.bucket.getX() + game.player.bucket.getWidth() / 2, game.player.bucket.getY() + game.player.bucket.getHeight() / 2, 0);
        game.player.handleInput(mapWidth, mapHeight);
        game.cameraHelper.cam.update();
        game.batch.setProjectionMatrix(game.cameraHelper.cam.combined);
        sp.setProjectionMatrix(game.cameraHelper.cam.combined);

        game.batch.begin();
        map.draw(game.batch);
        game.player.bucket.draw(game.batch);
        game.player.updatePlayer();
        game.batch.end();

        sp.begin(ShapeRenderer.ShapeType.Line);
        sp.setColor(Color.RED);
        sp.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        sp.rect(game.player.playerRect.getX(), game.player.playerRect.getY(), game.player.bucket.getWidth(), game.player.bucket.getHeight());
        sp.end();
    }

    private void drawGui(){
        game.batch.setProjectionMatrix(game.cameraHelper.guiCam.combined);
        game.batch.begin();
        game.font.draw(game.batch, game.player.bucket.getX() + " : " + game.player.bucket.getY(), 1, 1);
        if(rect.contains(game.player.bucket.getX() + 1, game.player.bucket.getY() + 1) && Gdx.input.isKeyPressed(Input.Keys.E)){
            game.font.draw(game.batch, "SITHEITHE", 50, 50);
            game.setScreen(new SecondMap(game));
            game.player.bucket.setPosition(17, 1);
            dispose();
        }
        game.batch.end();
    }


    @Override
    public void resize(int width, int height) {
        game.cameraHelper.cam.viewportWidth = 30f;
        game.cameraHelper.cam.viewportHeight = 30f * height / width;
        game.cameraHelper.cam.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
