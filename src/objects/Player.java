package objects;

import models.TexturedModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import terrains.Terrain;

public class Player extends Entity {
	public static int FIRST_PERSON = 0;
	public static int THIRD_PERSON = 1;
	public static int DEBUG = 2;
	private static final float RUN_SPEED = 35;
	private static final float TURN_SPEED = 160;
	private static final float GRAVITY = -64;
	private static final float JUMP_POWER = 30;
	private static float currentSpeed = 0;
	private static float currentTurnSpeed = 0;
	private static float upwardSpeed = 0;
	private boolean isInAir = false;
	private static int Perspective = 1;
	private static boolean render = true;
	private static boolean paused = false;

	public Player(TexturedModel model, Vector3f pos, float rotx, float roty, float rotz, float scale) {
		super(model, pos, rotx, roty, rotz, scale);
	}

	public static boolean toRender() {
		return render;
	}

	public void move(Terrain terrain) {
		if (!paused) {
			if (Perspective == FIRST_PERSON) {
				checkInputs_FIRST();
				if (render != false) {
					render = false;
				}
			} else if (Perspective == THIRD_PERSON) {
				checkInputs_THIRD();
				if (render != true) {
					render = true;
				}
			} else if (Perspective == DEBUG) {
				checkInputs_DEBUG();
				if (render != false) {
					render = false;
				}
			}
			if (Perspective == DEBUG) {
				super.increaseRoation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
				float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
				float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
				float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
				super.increasePosition(dx, 0, dz);
				upwardSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
				if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
					super.increasePosition(0, 1, 0);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
					super.increasePosition(0, -1, 0);
				}

			} else {
				super.increaseRoation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
				float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
				float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
				float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
				super.increasePosition(dx, 0, dz);
				upwardSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
				super.increasePosition(0, upwardSpeed * DisplayManager.getFrameTimeSeconds(), 0);
				float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
				if (super.getPosition().y < terrainHeight) {
					upwardSpeed = 0;
					isInAir = false;
					super.getPosition().y = terrainHeight;
				}
			}
		}
	}

	public static void setPaused(boolean paused) {
		Player.paused = paused;
	}

	public static boolean getPaused() {
		return paused;
	}

	public void jump() {
		if (!isInAir) {
			upwardSpeed = JUMP_POWER;
			isInAir = true;
		}
	}

	public void sprint() {
		currentSpeed = (RUN_SPEED * 2) - 10;
	}

	public void teleport(float x, float y, float z) {
		setPosition(new Vector3f(x, y, z));
	}

	public static int getPerspective() {
		return Perspective;
	}

	public void setPerspective(int perspective) {
		if (perspective == FIRST_PERSON) {
			Perspective = FIRST_PERSON;
			Mouse.setGrabbed(true);
			super.setRotY(180);
			Camera.setAngleAroundPlayerDistanceAroundPlayer(0, 40);
			Camera.setPitchYaw(0, 0);
		} else if (perspective == THIRD_PERSON) {
			Perspective = THIRD_PERSON;
			Mouse.setGrabbed(false);
			Camera.setPitchYaw(20, 70);
		} else if (perspective == DEBUG) {
			Perspective = DEBUG;
			Mouse.setGrabbed(false);
		}
	}

	private void checkInputs_THIRD() {
		if (!paused) {
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				currentSpeed = RUN_SPEED;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
				currentSpeed = -RUN_SPEED;
			} else {
				currentSpeed = 0;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				currentTurnSpeed = -TURN_SPEED;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
				currentTurnSpeed = TURN_SPEED;
			} else {
				currentTurnSpeed = 0;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				jump();
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				sprint();
			}
		}
	}

	private void checkInputs_FIRST() {
		if (!paused) {
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				currentSpeed = RUN_SPEED;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
				currentSpeed = -RUN_SPEED;
			} else {
				currentSpeed = 0;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				jump();
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				sprint();
			}
		}
	}

	private void checkInputs_DEBUG() {
		if (!paused) {
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				currentSpeed = RUN_SPEED * 8;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
				currentSpeed = -RUN_SPEED * 8;
			} else {
				currentSpeed = 0;
			}
		}
	}
}
