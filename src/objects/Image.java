package objects;

import models.TexturedModel;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Image {
	private TexturedModel model;
	private Vector2f position;

	public Image(TexturedModel model, Vector2f position) {
		this.model = model;
		this.position = position;
	}

	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
	}

	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return new Vector3f(position.x, position.y, 0);
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}
}
