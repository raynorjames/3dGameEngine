package button;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import toolbox.input;
import ObjectPopulation.Get;
import gui.GuiTexture;

public abstract class Button {

	public static boolean isHovering(int guiID) {
		if (Get.getGuiRendered(guiID)) {
			GuiTexture tgui = Get.getGui(guiID);
			Texture texture = Loader.getTexture(tgui.getTexture());
			float WIDTH_TEXTURE = texture.getImageWidth();
			float HEIGHT_TEXTURE = texture.getImageHeight();
			float WIDTH_SCALE = tgui.getScale().x * 3.5f;
			float HEIGHT_SCALE = tgui.getScale().y * 2f;
			float LOCATION_X = tgui.getPosition().x;
			float LOCATION_Y = tgui.getPosition().y;
			float HALF_IMAGE_WIDTH = ((WIDTH_TEXTURE * WIDTH_SCALE) / 2) / DisplayManager.RATIO;
			float HALF_IMAGE_HEIGHT = ((HEIGHT_TEXTURE * HEIGHT_SCALE) / 2) / DisplayManager.RATIO;
			float startx = ((((DisplayManager.getWIDTH() * LOCATION_X) - 1920) + 3840.0f) / 2) - HALF_IMAGE_WIDTH;
			float starty = ((((DisplayManager.getHEIGHT() * LOCATION_Y) - 1080) + 2160.0f) / 2) - HALF_IMAGE_HEIGHT;
			float stopx = startx + HALF_IMAGE_WIDTH * 2;
			float stopy = starty + HALF_IMAGE_HEIGHT * 2;
			if (Mouse.getX() > startx && Mouse.getX() < stopx) {
				if (Mouse.getY() > starty && Mouse.getY() < stopy) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isClicked(int guiID) {
		if (input.getMouseDown(0)) {
			if (isHovering(guiID)) {
				return true;
			}
		}
		return false;
	}
}
