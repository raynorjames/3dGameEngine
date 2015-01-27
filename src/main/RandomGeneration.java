package main;

import toolbox.EntityUtils;
import ObjectPopulation.Get;

public class RandomGeneration {
	public void randomGenration() {
		int treeID = initialization.TREE;
		int grassID = initialization.GRASS;
		int fernID = initialization.FERN;
		int CLOUDS_AMOUNT = 10;
		int GRASS_AMOUNT = 250;
		int FERN_AMOUNT = 200;
		int TREE_AMOUNT = 50;
		boolean tree = true;
		boolean grass = false;
		boolean fern = true;
		boolean clouds = true;
		if (grass) {
			EntityUtils.GenerateRandomAtlas(GRASS_AMOUNT, grassID, 2f, Get.getTerrain(initialization.TERRAIN_1));
			EntityUtils.GenerateRandomAtlas(GRASS_AMOUNT, grassID, 2f, Get.getTerrain(initialization.TERRAIN_2));
			EntityUtils.GenerateRandomAtlas(GRASS_AMOUNT, grassID, 2f, Get.getTerrain(initialization.TERRAIN_3));
			EntityUtils.GenerateRandomAtlas(GRASS_AMOUNT, grassID, 2f, Get.getTerrain(initialization.TERRAIN_4));
		}
		if (fern) {
			EntityUtils.GenerateRandomAtlas(FERN_AMOUNT, fernID, 1f, Get.getTerrain(initialization.TERRAIN_1));
			EntityUtils.GenerateRandomAtlas(FERN_AMOUNT, fernID, 1f, Get.getTerrain(initialization.TERRAIN_2));
			EntityUtils.GenerateRandomAtlas(FERN_AMOUNT, fernID, 1f, Get.getTerrain(initialization.TERRAIN_3));
			EntityUtils.GenerateRandomAtlas(FERN_AMOUNT, fernID, 1f, Get.getTerrain(initialization.TERRAIN_4));
		}
		if (tree) {
			EntityUtils.GenerateRandom(TREE_AMOUNT, treeID, 2.5f, Get.getTerrain(initialization.TERRAIN_1));
			EntityUtils.GenerateRandom(TREE_AMOUNT, treeID, 2.5f, Get.getTerrain(initialization.TERRAIN_2));
			EntityUtils.GenerateRandom(TREE_AMOUNT, treeID, 2.5f, Get.getTerrain(initialization.TERRAIN_3));
			EntityUtils.GenerateRandom(TREE_AMOUNT, treeID, 2.5f, Get.getTerrain(initialization.TERRAIN_4));
		}
		if (clouds) {
			EntityUtils.GenerateCloudRandom(CLOUDS_AMOUNT, Get.getTerrain(initialization.TERRAIN_1));
			EntityUtils.GenerateCloudRandom(CLOUDS_AMOUNT, Get.getTerrain(initialization.TERRAIN_2));
			EntityUtils.GenerateCloudRandom(CLOUDS_AMOUNT, Get.getTerrain(initialization.TERRAIN_3));
			EntityUtils.GenerateCloudRandom(CLOUDS_AMOUNT, Get.getTerrain(initialization.TERRAIN_4));
		}
	}
}
