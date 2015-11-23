package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by rooo on 11/16/2015.
 */
public class Player {
    Sprite bucket;
    Rectangle playerRect;
    final MainGame game;
    
	
	final float pixelToMeters = 100f;
	Body body;
	
	InputHelper iHelper;
	
    public Player(final MainGame gam) {
    	iHelper = new InputHelper(this);
        game = gam;
        bucket = new Sprite(new Texture("player.png"));
        bucket.setPosition(50, 50);
        bucket.setSize(2, 2);

        playerRect = new Rectangle();
        playerRect.x = bucket.getX();
        playerRect.y = bucket.getY();
        playerRect.width = 64;
        playerRect.height = 64;
        
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((bucket.getX() * bucket.getWidth()/2) / pixelToMeters, (bucket.getY() * bucket.getHeight() / 2) / pixelToMeters);
        body = game.world.createBody(bodyDef);
        
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(bucket.getWidth() / 2 / pixelToMeters, bucket.getHeight() / 2 / pixelToMeters);
        
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 10f;
        
        body.createFixture(fd);
        shape.dispose();
        
        
    }

    public void updatePlayer(){
        game.world.step(1f / 60f, 6, 2);
        
    	bucket.setPosition((body.getPosition().x * pixelToMeters) - bucket.getWidth() / 2,  (body.getPosition().y * pixelToMeters) - bucket.getHeight() / 2);
    	
        playerRect.x = bucket.getX();
        playerRect.y = bucket.getY();  
    }

   public void handleInput(int mapWidth, int mapHeight){
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
