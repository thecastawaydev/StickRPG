package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.sql.Time;
import java.util.Iterator;

public class MainGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public Player player;
    public CameraHelper cameraHelper;
    public World world;
    
    @Override
    public void create () {
    	world = new World(new Vector2(0, 0), true);
        batch = new SpriteBatch();
        font = new BitmapFont();
        cameraHelper = new CameraHelper();
        player = new Player(this);

        this.setScreen(new StartMap(this));
    }


    @Override
    public void render () {
        super.render();

    }

    @Override
    public void resize(int width, int height){

    }
}
