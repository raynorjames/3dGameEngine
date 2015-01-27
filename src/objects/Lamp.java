package objects;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.Assets;
import models.ModelTexture;
import models.RawModel;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;

import org.lwjgl.util.vector.Vector3f;

public class Lamp {
	private Vector3f Location;
	private Vector3f LocationLight;
	private Vector3f color;
	private Vector3f attenuation = new Vector3f(1, 0, 0);
	private Entity entity;
	private Light light;

	public Lamp(Vector3f Location, Vector3f Color, Vector3f attenuation) throws FileNotFoundException, IOException {
		this.Location = Location;
		this.LocationLight = new Vector3f(Location.x, Location.y + 14.7f, Location.z);
		this.color = Color;
		this.attenuation = attenuation;
		ModelTexture modelTexture = new ModelTexture(Assets.loader.loadTexture("lamp"));
		ModelData EntityData = OBJFileLoader.loadOBJ("lamp");
		RawModel EntityModel = Assets.loader.loadToVAO(EntityData.getVertices(), EntityData.getTextureCoords(), EntityData.getNormals(), EntityData.getIndices());
		TexturedModel model = new TexturedModel(EntityModel, modelTexture);
		entity = new Entity(model, Location, 0, 0, 0, 1);
		model.getTexture().setUseFakeLighting(true);
		light = new Light(LocationLight, color, attenuation);
	}

	public Vector3f getLocation() {
		return Location;
	}

	public Vector3f getColor() {
		return color;
	}

	public Vector3f getAttenuation() {
		return attenuation;
	}

	public Entity getEntity() {
		return entity;
	}

	public Light getLight() {
		return light;
	}

	public void increasePosition(float dx, float dy, float dz) {
		this.Location.x += dx;
		this.Location.y += dy;
		this.Location.z += dz;
		this.LocationLight.x += dx;
		this.LocationLight.y += dy;
		this.LocationLight.z += dz;
	}

	public void increasetColor(float dx, float dy, float dz) {
		this.color.x += dx;
		this.color.y += dy;
		this.color.z += dz;
	}

}
