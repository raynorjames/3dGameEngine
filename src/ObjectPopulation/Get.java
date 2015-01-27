package ObjectPopulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import objects.Entity;
import objects.Lamp;
import objects.Light;
import objects.Player;
import models.TexturedModel;
import terrains.Terrain;
import gui.GuiTexture;

public class Get {
	public static HashMap<Integer, Object> list = new HashMap<Integer, Object>();
	public static HashMap<Integer, Player> playerlist = new HashMap<Integer, Player>();
	public static HashMap<Integer, Terrain> terrainlist = new HashMap<Integer, Terrain>();
	public static HashMap<Integer, TexturedModel> modellist = new HashMap<Integer, TexturedModel>();
	public static HashMap<Integer, Boolean> guirenderlist = new HashMap<Integer, Boolean>();
	public static HashMap<Integer, Lamp> lamplist = new HashMap<Integer, Lamp>();
	private static List<Entity> EntityRenderList = new ArrayList<Entity>();
	private static List<Light> LightRenderList = new ArrayList<Light>();
	private static List<GuiTexture> GuiRenderList = new ArrayList<GuiTexture>();
	private static List<Player> PlayerRenderList = new ArrayList<Player>();
	private static List<Terrain> TerrainRenderList = new ArrayList<Terrain>();

	public Get(Entity entity, int id) {
		list.put(id, entity);
		RenderEntity(id);
	}

	public Get(Light light, int id) {
		list.put(id, light);
		RenderLight(id);
	}

	public Get(GuiTexture gui, int id) {
		list.put(id, gui);
		guirenderlist.put(id, false);
	}

	public Get(Player player, int id) {
		playerlist.put(id, player);
		RenderPlayer(id);
	}

	public Get(Terrain terrain, int id) {
		terrainlist.put(id, terrain);
		RenderTerrain(id);
	}

	public Get(Lamp lamp, int id) {
		lamplist.put(id, lamp);
		RenderLamp(id);
	}

	public Get(TexturedModel model, int id) {
		modellist.put(id, model);
	}

	public static void RenderEntity(int id) {
		EntityRenderList.add(getEntity(id));
	}

	public static void UnRenderEntity(int id) {
		EntityRenderList.remove(getEntity(id));
	}

	public static void RenderLight(int id) {
		LightRenderList.add(getLight(id));
	}

	public static void UnRenderLight(int id) {
		LightRenderList.remove(getLight(id));
	}

	public static void RenderGui(int id) {
		GuiRenderList.add(getGui(id));
		guirenderlist.put(id, true);
	}

	public static void UnRenderGui(int id) {
		GuiRenderList.remove(getGui(id));
		guirenderlist.put(id, false);
	}

	public static void RenderPlayer(int id) {
		PlayerRenderList.add(getPlayer(id));
	}

	public static void UnRenderPlayer(int id) {
		PlayerRenderList.remove(getPlayer(id));
	}

	public static void RenderLamp(int id) {
		EntityRenderList.add(getLamp(id).getEntity());
		LightRenderList.add(getLamp(id).getLight());
	}

	public static void UnRenderLamp(int id) {
		EntityRenderList.remove(getLamp(id).getEntity());
		LightRenderList.remove(getLamp(id).getLight());
	}

	public static void RenderTerrain(int id) {
		TerrainRenderList.add(getTerrain(id));
	}

	public static void UnRenderTerrain(int id) {
		TerrainRenderList.remove(getTerrain(id));
	}

	public static List<Entity> getEntityList() {
		return EntityRenderList;
	}

	public static List<Light> getLightList() {
		return LightRenderList;
	}

	public static List<GuiTexture> getGuiList() {
		return GuiRenderList;
	}

	public static List<Player> getPlayerList() {
		return PlayerRenderList;
	}

	public static List<Terrain> getTerrainList() {
		return TerrainRenderList;
	}

	public static Entity getEntity(int id) {
		return (Entity) list.get(id);
	}

	public static Light getLight(int id) {
		return (Light) list.get(id);
	}

	public static GuiTexture getGui(int id) {
		return (GuiTexture) list.get(id);
	}

	public static Player getPlayer(int id) {
		return playerlist.get(id);
	}

	public static Terrain getTerrain(int id) {
		return terrainlist.get(id);
	}

	public static TexturedModel getEntityModel(int id) {
		return modellist.get(id);
	}

	public static boolean getGuiRendered(int id) {
		return guirenderlist.get(id);
	}

	public static Lamp getLamp(int id) {
		return lamplist.get(id);
	}
}
