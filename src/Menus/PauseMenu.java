package Menus;

import main.Assets;
import main.ShutDown;
import objects.Player;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import toolbox.input;
import ObjectPopulation.Get;
import button.Button;
import button.GuiUpdateKeys;

public class PauseMenu {
	public static int GAME_NAME;
	public static int TINT;
	public static int OPTIONS;
	public static int QUIT;

	public void initButtons() {
		TINT = Assets.handler.InitGui("tint", new Vector2f(0, 0), new Vector2f(1, 1));
		GAME_NAME = Assets.handler.InitGui("Title", new Vector2f(0, 0.5f), new Vector2f(0.25f, 0.1f));
		OPTIONS = Assets.handler.InitGui("Options", new Vector2f(-0.13f, 0.3f), new Vector2f(0.1f, 0.025f));
		QUIT = Assets.handler.InitGui("Quit", new Vector2f(0.13f, 0.3f), new Vector2f(0.1f, 0.025f));
	}

	public static void render() {
		Get.RenderGui(TINT);
		Get.RenderGui(GAME_NAME);
		Get.RenderGui(OPTIONS);
		Get.RenderGui(QUIT);
	}

	public static void derender() {
		Get.UnRenderGui(TINT);
		Get.UnRenderGui(GAME_NAME);
		Get.UnRenderGui(OPTIONS);
		Get.UnRenderGui(QUIT);
	}

	public static void back() {
		if (input.getKeyDown(Keyboard.KEY_ESCAPE)) {
			if (Get.getGuiRendered(GAME_NAME)) {
				derender();
				OptionMenu.derender();
				Player.setPaused(false);
			}
		}
		if (!Get.getGuiRendered(GAME_NAME)) {
			GuiUpdateKeys.PointUpdate(Keyboard.KEY_ESCAPE);
		}
	}

	public void update() {
		if (Button.isClicked(OPTIONS)) {
			derender();
			OptionMenu.render();
		}
		if (Button.isClicked(QUIT)) {
			ShutDown.shutDown();
		}
		back();
	}

}
