package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	private static int WIDTH = 1920;
	private static int HEIGT = 1080;
	private static int FPS_CAP = 120;
	private static long lastFrameTime;
	private static float delta;
	private static MasterRenderer renderer;
	private static Loader loader;
	private static int fps;
	private static long lastFPS;
	public static final float RATIO = (float) WIDTH / (float) HEIGT;

	public static void createDisplay(boolean fullscreen) throws LWJGLException {
		ContextAttribs attribs = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);
		setDisplayMode(WIDTH, HEIGT, fullscreen);
		try {
			Display.create(new PixelFormat(), attribs);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, WIDTH, HEIGT);
		lastFrameTime = getCurrentTime();
		Display.setResizable(false);
		lastFPS = getTime();

	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGT;
	}

	@SuppressWarnings("unused")
	private static void setDisplay(int width, int height, boolean fullscreen) {
		DisplayManager.WIDTH = width;
		DisplayManager.HEIGT = height;
		if ((Display.getDisplayMode().getWidth() == width) && (Display.getDisplayMode().getHeight() == height) && (Display.isFullscreen() == fullscreen)) {
			return;
		}
		try {
			DisplayMode targetDisplayMode = null;
			if (fullscreen) {
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;
				for (int i = 0; i < modes.length; i++) {
					DisplayMode current = modes[i];
					if ((current.getWidth() == width) && (current.getHeight() == height)) {
						if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
							if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}
						if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) && (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
							targetDisplayMode = current;
							break;
						}
					}
				}
			} else {
				targetDisplayMode = new DisplayMode(width, height);
			}
			if (targetDisplayMode == null) {
				System.out.println("Failed to find value mode: " + width + "x" + height + " fs=" + fullscreen);
				return;
			}
			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fullscreen);
		} catch (LWJGLException e) {
			System.out.println("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullscreen + e);
		}
	}

	public static void DefineInstance(MasterRenderer renderers, Loader loaders) {
		renderer = renderers;
		loader = loaders;
	}

	public static void updateDisplay() {
		Display.sync(FPS_CAP);
		Display.update();
		long currentFramtime = getCurrentTime();
		delta = (currentFramtime - lastFrameTime) / 1000f;
		lastFrameTime = currentFramtime;
		checkShutDownKeys(renderer, loader);
		updateFPS();
	}

	private static void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	private static int getTime() {
		return (int) ((Sys.getTime() * 1000) / Sys.getTimerResolution());
	}

	public static float getFrameTimeSeconds() {
		return delta;
	}

	private static void checkShutDownKeys(MasterRenderer renderer, Loader loader) {
		
	}

	public static void closeDisplay() {

		Display.destroy();
	}

	private static long getCurrentTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

	private static void setDisplayMode(int w, int h, boolean fullscreen) {
		DisplayManager.HEIGT = h;
		DisplayManager.WIDTH = w;
		if ((Display.getDisplayMode().getWidth() == w) && (Display.getDisplayMode().getHeight() == h) && (Display.isFullscreen() == fullscreen)) {
			return;
		}
		try {
			DisplayMode targetDisplayMode = null;
			if (fullscreen) {
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;

				for (int i = 0; i < modes.length; i++) {
					DisplayMode current = modes[i];

					if ((current.getWidth() == w) && (current.getHeight() == h)) {
						if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
							if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}

						if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) && (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
							targetDisplayMode = current;
							break;
						}
					}
				}
			} else {
				targetDisplayMode = new DisplayMode(w, h);
			}

			if (targetDisplayMode == null) {
				System.out.println("Failed to find value mode: " + w + "x" + h + " fs=" + fullscreen);
				return;
			}

			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fullscreen);

		} catch (LWJGLException e) {
			System.out.println("Unable to setup mode " + w + "x" + h + " fullscreen=" + fullscreen + e);
		}
	}

}
