package Menus;

import main.Assets;
import objects.Player;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import renderEngine.Fullscreen;
import toolbox.input;
import ObjectPopulation.Get;
import button.Button;

public class OptionMenu {
	public static int GAME_NAME;
	public static int TINT;
	public static int POV;
	public static int MAX;

	public void initButtons() {
		TINT = Assets.handler.InitGui("tint", new Vector2f(0, 0), new Vector2f(1, 1));
		GAME_NAME = Assets.handler.InitGui("Title", new Vector2f(0, 0.5f), new Vector2f(0.25f, 0.1f));
		POV = Assets.handler.InitGui("Pov", new Vector2f(-0.13f, 0.3f), new Vector2f(0.1f, 0.025f));
		MAX = Assets.handler.InitGui("Max", new Vector2f(0.13f, 0.3f), new Vector2f(0.1f, 0.025f));
	}

	public static void render() {
		Get.RenderGui(TINT);
		Get.RenderGui(GAME_NAME);
		Get.RenderGui(POV);
		Get.RenderGui(MAX);
	}

	public static void derender() {
		Get.UnRenderGui(TINT);
		Get.UnRenderGui(GAME_NAME);
		Get.UnRenderGui(POV);
		Get.UnRenderGui(MAX);

	}

	public static void back() {
		if (input.getKeyDown(Keyboard.KEY_ESCAPE)) {
			if (Get.getGuiRendered(GAME_NAME)) {
				derender();
				PauseMenu.derender();
				PauseMenu.render();
			}
		}
	}

	public void update() {
		back();
		if (Button.isClicked(POV)) {
			if (Player.getPerspective() == Player.FIRST_PERSON) {
				Get.getPlayer(Assets.PLAYER).setPerspective(Player.THIRD_PERSON);
				System.out.println("Third person");
			} else if (Player.getPerspective() == Player.THIRD_PERSON) {
				Get.getPlayer(Assets.PLAYER).setPerspective(Player.FIRST_PERSON);
				System.out.println("First person");
			}
		} else if (Button.isClicked(MAX)) {
			if (Fullscreen.isFullscreen() == false) {
				Fullscreen.setFullscreen();
			} else if (Fullscreen.isFullscreen() == true) {
				Fullscreen.setFullscreenOff();
			}
		} else if (Button.isClicked(GAME_NAME)) {
			derender();
			PauseMenu.derender();
			PauseMenu.render();
		}
	}
}
