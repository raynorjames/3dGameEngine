package main;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.util.vector.Vector3f;

public class Assets extends initialization {

	public void assets() throws IOException, LWJGLException, InterruptedException {
		CLOUD = handler.InitEntity("cloud", "cloud", 1, false, false);
		FERN = handler.InitEntity("fern", "fern", 2, false, true);
		TREE = handler.InitEntity("pine", "pine", 1, true, false);
		GRASS = handler.InitEntity("grassModel", "grasstest", 2, false, true);
		PLAYER = handler.InitPlayer("person", "playerTexture", new Vector3f(0, 1, 0), 0.6f);
		TERRAIN_1 = handler.InitTerrain("grassy", "mud", "grassFlowers", "path", "blendMap", 0, -1, "heightmap");
		TERRAIN_2 = handler.InitTerrain("grassy", "mud", "grassFlowers", "path", "blendMap", 0, 0, "heightmap");
		TERRAIN_3 = handler.InitTerrain("grassy", "mud", "grassFlowers", "path", "blendMap", -1, 0, "heightmap");
		TERRAIN_4 = handler.InitTerrain("grassy", "mud", "grassFlowers", "path", "blendMap", -1, -1, "heightmap");
		LIGHT_1 = handler.InitLight(new Vector3f(0, 1000, -7000), new Vector3f(0, 0, 0));
		LAMP = handler.InitLamp(new Vector3f(100, -10, 100), new Vector3f(1,1,1), new Vector3f(1, 0.01f, 0.002f));
	}
}
