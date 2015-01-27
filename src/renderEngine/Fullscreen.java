package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

public class Fullscreen {
	private static boolean fullscreen = false;

	public static void setFullscreen() {
		try {
			Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		fullscreen = true;
	}

	public static void setFullscreenOff() {
		try {
			Display.setFullscreen(false);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		fullscreen = false;
	}

	public static boolean isFullscreen() {
		return fullscreen;
	}
}
