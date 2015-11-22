package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by rooo on 11/16/2015.
 */
public class Player {
    Sprite bucket;
    Rectangle playerRect;
    final MainGame game;

    public Player(final MainGame gam) {
        game = gam;
        bucket = new Sprite(new Texture("player.png"));
        bucket.setPosition(50, 50);
        bucket.setSize(2, 2);

        playerRect = new Rectangle();
        playerRect.x = bucket.getX();
        playerRect.y = bucket.getY();
        playerRect.width = 64;
        playerRect.height = 64;
    }

    public void updatePlayer(){
        playerRect.x = bucket.getX();
        playerRect.y = bucket.getY();
    }

    public void handleInput(int mapWidth, int mapHeight){
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            game.cameraHelper.cam.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            game.cameraHelper.cam.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            game.player.bucket.translateX(-0.3f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            game.player.bucket.translateX(0.3f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            game.player.bucket.translateY(-0.3f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            game.player.bucket.translateY(0.3f);
        }

        if(game.player.bucket.getX() < 0)
            game.player.bucket.setX(0);
        if(game.player.bucket.getX() - game.player.bucket.getWidth() >= mapWidth - 2)
            game.player.bucket.setX(game.player.bucket.getX() - game.player.bucket.getWidth());
        if(game.player.bucket.getY() < 0)
            game.player.bucket.setY(0);
        if(game.player.bucket.getY() - game.player.bucket.getHeight() >= mapHeight - 2)
            game.player.bucket.setY(game.player.bucket.getY() - game.player.bucket.getHeight());

        game.cameraHelper.cam.zoom = MathUtils.clamp(game.cameraHelper.cam.zoom, 0.1f, mapWidth / game.cameraHelper.cam.viewportWidth);

        float effectiveViewportWidth = game.cameraHelper.cam.viewportWidth * game.cameraHelper.cam.zoom;
        float effectiveViewportHeight = game.cameraHelper.cam.viewportHeight * game.cameraHelper.cam.zoom;

        game.cameraHelper.cam.position.x = MathUtils.clamp(game.cameraHelper.cam.position.x, effectiveViewportWidth / 2f, mapWidth - effectiveViewportWidth / 2f);
        game.cameraHelper.cam.position.y = MathUtils.clamp(game.cameraHelper.cam.position.y, effectiveViewportHeight / 2f, mapHeight - effectiveViewportHeight / 2f);
    }
}
