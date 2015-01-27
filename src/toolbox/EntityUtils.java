package toolbox;

import java.util.Random;

import main.initialization;

import org.lwjgl.util.vector.Vector3f;

import renderEngine.MasterRenderer;
import terrains.Terrain;
import ObjectPopulation.Get;
import ObjectPopulation.Handler;

public class EntityUtils {

	public static void GenerateRandomAtlas(int amount, int EntityID, float scale, Terrain terrain) {
		Random random = new Random();
		float x = 0;
		float z = 0;
		for (int i = 0; i < amount; i++) {
			if (terrain == Get.getTerrain(initialization.TERRAIN_1)) {
				x = random.nextFloat() * 800;
				z = random.nextFloat() * -600;
			} else if (terrain == Get.getTerrain(initialization.TERRAIN_2)) {
				x = random.nextFloat() * 800;
				z = random.nextFloat() * 600;
			} else if (terrain == Get.getTerrain(initialization.TERRAIN_3)) {
				x = random.nextFloat() * -800;
				z = random.nextFloat() * 600;
			} else if (terrain == Get.getTerrain(initialization.TERRAIN_4)) {
				x = random.nextFloat() * -800;
				z = random.nextFloat() * -600;
			}
			float y = terrain.getHeightOfTerrain(x, z);
			Handler.spawnEntity(EntityID, new Vector3f(x, y, z), scale, true);
		}
	}

	public static void GenerateRandom(int amount, int EntityID, float scale, Terrain terrain) {
		Random random = new Random();
		float x = 0;
		float z = 0;
		for (int i = 0; i < amount; i++) {
			if (terrain == Get.getTerrain(initialization.TERRAIN_1)) {
				x = random.nextFloat() * 800;
				z = random.nextFloat() * -600;
			} else if (terrain == Get.getTerrain(initialization.TERRAIN_2)) {
				x = random.nextFloat() * 800;
				z = random.nextFloat() * 600;
			} else if (terrain == Get.getTerrain(initialization.TERRAIN_3)) {
				x = random.nextFloat() * -800;
				z = random.nextFloat() * 600;
			} else if (terrain == Get.getTerrain(initialization.TERRAIN_4)) {
				x = random.nextFloat() * -800;
				z = random.nextFloat() * -600;
			}
			float y = terrain.getHeightOfTerrain(x, z);
			Handler.spawnEntity(EntityID, new Vector3f(x, y, z), scale, false);
		}
	}

	public static void GenerateCloudRandom(int amount, Terrain terrain) {
		if (MasterRenderer.getFog().y <= 0.002f) {
			Random random = new Random();
			float x = 0;
			float z = 0;
			for (int i = 0; i < amount; i++) {
				if (terrain == Get.getTerrain(initialization.TERRAIN_1)) {
					x = random.nextFloat() * 800;
					z = random.nextFloat() * -800;
				} else if (terrain == Get.getTerrain(initialization.TERRAIN_2)) {
					x = random.nextFloat() * 800;
					z = random.nextFloat() * 800;
				} else if (terrain == Get.getTerrain(initialization.TERRAIN_3)) {
					x = random.nextFloat() * -800;
					z = random.nextFloat() * 800;
				} else if (terrain == Get.getTerrain(initialization.TERRAIN_4)) {
					x = random.nextFloat() * -800;
					z = random.nextFloat() * -800;
				}
				float y = random.nextFloat() * 100;
				if (y < 50) {
					y += 50;
				}
				float Scale = random.nextFloat() * 6.5f;
				if (Scale <= 5) {
					Scale += 6;
				}
				Handler.spawnEntity(initialization.CLOUD, new Vector3f(x, y, z), Scale, false);
			}
		}
	}

	public static Terrain getTerrain(float x, float z) {
		if (x < Get.getTerrain(initialization.TERRAIN_1).getSize() && x > 0 && z < 0 && z > -Get.getTerrain(initialization.TERRAIN_1).getSize()) {
			return Get.getTerrain(initialization.TERRAIN_1);
		} else if (x < Get.getTerrain(initialization.TERRAIN_2).getSize() && x > 0 && z > 0 && z < Get.getTerrain(initialization.TERRAIN_2).getSize()) {
			return Get.getTerrain(initialization.TERRAIN_2);
		} else if (x > -Get.getTerrain(initialization.TERRAIN_3).getSize() && x < 0 && z > 0 && z < Get.getTerrain(initialization.TERRAIN_3).getSize()) {
			return Get.getTerrain(initialization.TERRAIN_3);
		} else if (x > -Get.getTerrain(initialization.TERRAIN_4).getSize() && x < 0 && z > -Get.getTerrain(initialization.TERRAIN_4).getSize() && z < 0) {
			return Get.getTerrain(initialization.TERRAIN_4);
		}
		Get.getPlayer(initialization.PLAYER).setPosition(new Vector3f(0, 0, 0));
		return Get.getTerrain(initialization.TERRAIN_1);
	}
}
