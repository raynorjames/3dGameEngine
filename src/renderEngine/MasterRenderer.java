package renderEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.TexturedModel;
import objects.Camera;
import objects.Entity;
import objects.Image;
import objects.Light;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import shaders.StaticShader;
import shaders.TerrainShader;
import terrains.Terrain;

public class MasterRenderer {
	private static final float FOV = 70;
	private static float NEAR_PLANE = 0.1f;
	private static float FAR_PLANE = 1000f;

	private static Vector3f skycolor;
	private static Vector2f fog;
	private static float TILE_SIZE = 60;
	private Matrix4f projectionMatrix;
	private StaticShader shader = new StaticShader();
	private EntityRenderer renderer;
	private TerrainRenderer terrainRenderer;
	private TerrainShader terrainShader = new TerrainShader();
	private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
	private List<Terrain> terrains = new ArrayList<Terrain>();
	private List<Image> images = new ArrayList<Image>();
	private static boolean scary = false;

	public MasterRenderer() {
		if (!scary) {
			fog = new Vector2f(1f, 0.0015f);
			skycolor = new Vector3f(0.1f, 0.1f, 0.1f);
		} else {
			fog = new Vector2f(5f, 0.015f);
			skycolor = new Vector3f(0, 0, 0);
			FAR_PLANE = 100F;
		}
		enableCulling();
		createProjectionMatrix();
		renderer = new EntityRenderer(shader, projectionMatrix);
		terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
	}

	public static void enableCulling() {
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}

	public void setTileSize(float size) {
		TILE_SIZE = size;
	}

	public void setFog(float gradient, float density) {
		fog.x = gradient;
		fog.y = density;
	}

	public static void disableCulling() {
		GL11.glDisable(GL11.GL_CULL_FACE);
	}

	public void render(List<Light> lights, Camera camera) {
		prepare();
		shader.start();
		shader.loadSkyColor(skycolor.x, skycolor.y, skycolor.z);
		shader.loadLights(lights);
		shader.loadviewMatrix(camera);
		shader.loadFog(fog.x, fog.y);
		renderer.render(entities);
		shader.stop();
		terrainShader.start();
		terrainShader.loadSkyColor(skycolor.x, skycolor.y, skycolor.z);
		terrainShader.loadLights(lights);
		terrainShader.loadTileSize(TILE_SIZE);
		terrainShader.loadviewMatrix(camera);
		terrainShader.loadFog(fog.x, fog.y);
		terrainRenderer.render(terrains);
		terrainShader.stop();
		terrains.clear();
		entities.clear();
		images.clear();
	}

	public void processTerrain(Terrain terrain) {
		terrains.add(terrain);
	}

	public void processEntity(Entity entity) {
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get(entityModel);
		if (batch != null) {
			batch.add(entity);
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}

	public void cleanUp() {
		try {
			shader.cleanUp();
			terrainShader.cleanUp();

		} catch (Exception e) {
		}
	}

	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(skycolor.x, skycolor.y, skycolor.z, 1f);
	}

	private void createProjectionMatrix() {
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) (1f / Math.tan(Math.toRadians(FOV / 2f)) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}

	public static Vector2f getFog() {
		return fog;
	}

	public static Vector3f getSkyColor() {
		return skycolor;
	}

	public static void setScary(boolean b) {
		scary = b;
	}

	public static boolean isScary() {
		return scary;
	}
}
