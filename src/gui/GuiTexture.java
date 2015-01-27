package gui;

import org.lwjgl.util.vector.Vector2f;

import renderEngine.DisplayManager;

public class GuiTexture {

	private int texture;
	private Vector2f position;
	private Vector2f scale;
	private float rotX, rotY;

	public GuiTexture(int texture, Vector2f position, Vector2f scale) {
		this.texture = texture;
		this.position = position;
		this.scale = new Vector2f(scale.x, scale.y * DisplayManager.RATIO);
	}

	public void setImage(int id) {
		this.texture = id;
	}

	public int getTexture() {
		return texture;
	}

	public Vector2f getRotation() {
		return new Vector2f(rotX, rotY);
	}

	public Vector2f getPosition() {
		return position;
	}

	public Vector2f getScale() {
		return scale;
	}

}
