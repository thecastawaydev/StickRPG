package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.physics.box2d.World;

public class InputHelper implements InputProcessor{
	
	private Player player;
	
	public InputHelper(Player player){
		this.player = player;
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.RIGHT){
			player.body.setLinearVelocity(0.1f, 0f);
		}
		if(keycode == Input.Keys.LEFT){
			player.body.setLinearVelocity(-0.1f, 0);
		}
		if(keycode == Input.Keys.UP){
			player.body.setLinearVelocity(0, 0.1f);
		}
		if(keycode == Input.Keys.DOWN){
			player.body.setLinearVelocity(0, -0.1f);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.RIGHT){
			player.body.setLinearVelocity(0, player.body.getLinearVelocity().y);
		}
		if(keycode == Input.Keys.LEFT){
			player.body.setLinearVelocity(0, player.body.getLinearVelocity().y);
		}
		if(keycode == Input.Keys.UP){
			player.body.setLinearVelocity(player.body.getLinearVelocity().x, 0);
		}
		if(keycode == Input.Keys.DOWN){
			player.body.setLinearVelocity(player.body.getLinearVelocity().x, 0);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
