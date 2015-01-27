package ObjectPopulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import models.ModelTexture;
import models.RawModel;
import models.TerrainTexture;
import models.TerrainTexturePack;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import objects.Entity;
import objects.Lamp;
import objects.Light;
import objects.Player;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.Loader;
import terrains.Terrain;
import gui.GuiTexture;

public class Handler {
	private Loader loader;
	private int id = 0;

	public Handler(Loader loader) {
		this.loader = loader;
	}

	public int InitEntity(String obj, String tex, int nrows, boolean transp, boolean fakel) {
		TexturedModel model = null;
		RawModel EntityModel = null;
		ModelData EntityData = null;
		id++;
		try {
			ModelTexture modelTexture = new ModelTexture(loader.loadTexture(tex));
			modelTexture.setNumberOfRows(nrows);
			EntityData = OBJFileLoader.loadOBJ(obj);
			EntityModel = loader.loadToVAO(EntityData.getVertices(), EntityData.getTextureCoords(), EntityData.getNormals(), EntityData.getIndices());
			model = new TexturedModel(EntityModel, modelTexture);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.getTexture().setHasTransparency(transp);
		model.getTexture().setUseFakeLighting(fakel);
		new Get(model, id);
		return id;
	}

	public static Entity spawnEntity(int id, Vector3f Location, float scale, boolean atlast) {
		if (!atlast) {
			Entity entity = new Entity(Get.getEntityModel(id), Location, 0, 0, 0, scale);
			new Get(entity, id);
			return entity;
		} else {
			Random random = new Random();
			int i = random.nextInt(4);
			Entity entity = new Entity(Get.getEntityModel(id), i, Location, 0, 0, 0, scale);
			new Get(entity, id);
		}
		return null;
	}

	public int InitLight(Vector3f location, Vector3f color) {
		id++;
		Light light = new Light(location, color);
		new Get(light, id);
		return id;
	}

	public int InitLight(Vector3f location, Vector3f color, Vector3f attenuation) {
		id++;
		Light light = new Light(location, color, attenuation);
		new Get(light, id);
		return id;
	}

	public static void reInitLight(int id, Vector3f location, Vector3f color) {
		Get.UnRenderLight(id);
		Get.list.remove(id);
		Light light = new Light(location, color);
		new Get(light, id);
		Get.RenderLight(id);
	}

	public int InitPlayer(String obj, String tex, Vector3f pos, float scale) throws IOException {
		id++;
		ModelTexture modelTexture = new ModelTexture(loader.loadTexture(tex));
		modelTexture.setNumberOfRows(1);
		ModelData EntityData = OBJFileLoader.loadOBJ(obj);
		RawModel EntityModel = loader.loadToVAO(EntityData.getVertices(), EntityData.getTextureCoords(), EntityData.getNormals(), EntityData.getIndices());
		TexturedModel model = new TexturedModel(EntityModel, modelTexture);
		Player player = new Player(model, pos, 0, 0, 0, scale);
		new Get(player, id);
		return id;
	}

	public int InitTerrain(String backgroundTexture, String rTexture, String gTexture, String bTexture, String blendMap, int x, int y, String HeightMap) {
		TerrainTexturePack texturePack = null;
		TerrainTexture blendMaps = null;
		id++;
		try {
			TerrainTexture backgroundTextures = new TerrainTexture(loader.loadTexture(backgroundTexture));
			TerrainTexture rTextures = new TerrainTexture(loader.loadTexture(rTexture));
			TerrainTexture gTextures = new TerrainTexture(loader.loadTexture(gTexture));
			TerrainTexture bTextures = new TerrainTexture(loader.loadTexture(bTexture));
			texturePack = new TerrainTexturePack(backgroundTextures, rTextures, gTextures, bTextures);
			blendMaps = new TerrainTexture(loader.loadTexture(blendMap));
		} catch (Exception e) {
			System.err.println("Error in 'CreateTerrain' class due to texture loading.");
			System.exit(-1);
		}
		Terrain terrain = new Terrain(x, y, loader, texturePack, blendMaps, HeightMap);
		new Get(terrain, id);
		return id;
	}

	public int InitGui(String ImageName, Vector2f Location, Vector2f Scale) {
		id++;
		GuiTexture guitex = null;
		try {
			guitex = new GuiTexture(loader.loadTexture(ImageName), Location, Scale);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find image, " + ImageName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Get(guitex, id);
		return id;
	}

	public int InitLamp(Vector3f Location, Vector3f color, Vector3f attenuation) {
		id++;
		Lamp lamp = null;
		try {
			lamp = new Lamp(Location, color, attenuation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Get(lamp, id);
		return id;
	}
}
