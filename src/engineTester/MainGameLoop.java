package engineTester;

import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import main.Assets;
import main.RandomGeneration;
import main.ShutDown;
import main.Update;
import main.initialization;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import renderEngine.Loader;
import Menus.OptionMenu;
import Menus.PauseMenu;

public class MainGameLoop {
	public static Loader loader;

	@SuppressWarnings("static-access")
	public static void main(String args[]) throws IOException, LWJGLException, InterruptedException, UnsupportedAudioFileException {
		initialization init = new initialization();
		Assets initAssets = new Assets();
		RandomGeneration randomGenration = new RandomGeneration();
		Update update = new Update();
		ShutDown shutDown = new ShutDown();
		PauseMenu pauseMenu = new PauseMenu();
		OptionMenu optionMenu = new OptionMenu();
		init.init();
		initAssets.assets();
		init.initCamera();
		randomGenration.randomGenration();
		pauseMenu.initButtons();
		optionMenu.initButtons();
		while (!Display.isCloseRequested()) {
			update.update(init);
			optionMenu.update();
			pauseMenu.update();
		}
		shutDown.shutDown();
	}
}
