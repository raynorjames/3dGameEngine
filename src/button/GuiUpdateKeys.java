package button;

import main.Assets;
import objects.Player;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import toolbox.input;
import Menus.PauseMenu;

public class GuiUpdateKeys {
	private static boolean keydown = false;

	public static void PointUpdate(int id) {
		if (input.getKeyDown(id)) {
			if (keydown == false) {
				PauseMenu.derender();
				keydown = true;
				Player.setPaused(false);
				if (Player.getPerspective() == Player.FIRST_PERSON || Player.getPerspective() == Player.DEBUG) {
					Mouse.setGrabbed(true);
				}
			} else {
				PauseMenu.render();
				keydown = false;
				Player.setPaused(true);
				Mouse.setGrabbed(false);
			}
		}
	}
}
