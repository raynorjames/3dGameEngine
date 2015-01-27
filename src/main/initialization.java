package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import objects.Camera;

import org.lwjgl.LWJGLException;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import ObjectPopulation.Get;
import ObjectPopulation.Handler;
import gui.GuiRenderer;

public class initialization {
	public Random rand;
	public static Loader loader;
	public static MasterRenderer renderer;
	public static GuiRenderer guiRender;
	public Camera camera;
	public static Handler handler;
	public static int TERRAIN_1;
	public static int TERRAIN_2;
	public static int TERRAIN_3;
	public static int TERRAIN_4;
	public static int LIGHT_1;
	public static int LIGHT_2;
	public static int LIGHT_3;
	public static int PLAYER;
	public static int GRASS;
	public static int TREE;
	public static int HDTREE;
	public static int CLOUD;
	public static int FERN;
	public static int GUI;
	public static int BUTTONTEST;
	public static int LAMP;

	public void init() throws LWJGLException, FileNotFoundException, IOException {
		DisplayManager.createDisplay(false);
		loader = new Loader();
		rand = new Random();
		renderer = new MasterRenderer();
		guiRender = new GuiRenderer(loader);
		DisplayManager.DefineInstance(renderer, loader);
		handler = new Handler(loader);
	}

	public void initCamera() {
		camera = new Camera(Get.getPlayer(Assets.PLAYER));
	}
}
