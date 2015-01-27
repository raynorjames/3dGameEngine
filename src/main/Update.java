package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import objects.Entity;
import objects.Player;
import renderEngine.DisplayManager;
import terrains.Terrain;
import toolbox.EntityUtils;
import toolbox.input;
import ObjectPopulation.Get;

public class Update {

	public void update(initialization assets) throws FileNotFoundException, IOException {
		Terrain terrain = EntityUtils.getTerrain(Get.getPlayer(Assets.PLAYER).getPosition().x, Get.getPlayer(Assets.PLAYER).getPosition().z);
		Get.getPlayer(Assets.PLAYER).move(terrain);
		assets.camera.move();
		if (Player.toRender() == true) {
			for (Player players : Get.getPlayerList()) {
				Assets.renderer.processEntity(players);
			}
		}
		input.update();
		for (Terrain terrains : Get.getTerrainList()) {
			Assets.renderer.processTerrain(terrains);
		}
		for (Entity entitys : Get.getEntityList()) {
			Assets.renderer.processEntity(entitys);
		}
		Assets.renderer.render(Get.getLightList(), assets.camera);
		Assets.guiRender.render(Get.getGuiList());
		DisplayManager.updateDisplay();
	}

}
