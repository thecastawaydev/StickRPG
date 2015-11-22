package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by rooo on 11/16/2015.
 */
public class SecondMap implements Screen {
    final MainGame game;

    int mapWidth = 50;
    int mapHeight = 50;

    Rectangle rect;

    Sprite map;
    ShapeRenderer sp;

    public SecondMap(final MainGame gam){
        game = gam;

        sp = new ShapeRenderer();
        map = new Sprite(new Texture("map2.png"));
        map.setPosition(0, 0);
        map.setSize(mapWidth, mapHeight);

        rect = new Rectangle(16, 0, 5, 5);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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

        game.batch.setProjectionMatrix(game.cameraHelper.guiCam.combined);
        game.batch.begin();
        game.font.draw(game.batch, game.player.bucket.getX() + " : " + game.player.bucket.getY(), 1, 1);
        game.batch.end();
        
        if(rect.contains(game.player.bucket.getX() + 1, game.player.bucket.getY() + 1) && Gdx.input.isKeyPressed(Input.Keys.E)){

            game.setScreen(new StartMap(game));
            game.player.bucket.setPosition(16, 31);
            dispose();
        }
        sp.begin(ShapeRenderer.ShapeType.Line);
        sp.setColor(Color.RED);
        sp.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        sp.rect(game.player.playerRect.getX(), game.player.playerRect.getY(), game.player.bucket.getWidth(), game.player.bucket.getHeight());
        sp.end();
    }

    @Override
    public void resize(int width, int height) {

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
