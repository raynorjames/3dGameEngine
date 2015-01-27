package main;

import ObjectPopulation.Get;

public class ShutDown {

	public static void shutDown() {
		for (int i = 0; i < Get.getTerrainList().size(); i++) {
			Get.getTerrainList().remove(i);
		}
		for (int i = 0; i < Get.getLightList().size(); i++) {
			Get.getLightList().remove(i);
		}
		for (int i = 0; i < Get.getEntityList().size(); i++) {
			Get.getEntityList().remove(i);
		}
		Assets.guiRender.cleanUp();
		Assets.renderer.cleanUp();
		Assets.loader.cleanUp();
		System.exit(0);
	}
}
