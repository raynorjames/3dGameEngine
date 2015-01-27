package toolbox;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

public class input {
	public static final int NUM_KEYCODES = 256;
	public static final int NUM_MOUSEBUTTONS = 6;

	private static ArrayList<Integer> currentkeys = new ArrayList<Integer>();
	private static ArrayList<Integer> downkeys = new ArrayList<Integer>();
	private static ArrayList<Integer> upkeys = new ArrayList<Integer>();
	private static ArrayList<Integer> currentmouse = new ArrayList<Integer>();

	private static ArrayList<Integer> downMouse = new ArrayList<Integer>();
	private static ArrayList<Integer> upMouse = new ArrayList<Integer>();

	public static void update() {
		downMouse.clear();
		for (int i = 0; i < NUM_KEYCODES; i++) {
			if (getMouse(i) && !currentmouse.contains(i)) {
				downMouse.add(i);
			}
		}
		upMouse.clear();
		for (int i = 0; i < NUM_KEYCODES; i++) {
			if (!getMouse(i) && currentmouse.contains(i)) {
				upMouse.add(i);
			}
		}
		upkeys.clear();
		for (int i = 0; i < NUM_KEYCODES; i++) {
			if (!getKey(i) && currentkeys.contains(i)) {
				upkeys.add(i);
			}
		}
		downkeys.clear();
		for (int i = 0; i < NUM_KEYCODES; i++) {
			if (getKey(i) && !currentkeys.contains(i)) {
				downkeys.add(i);
			}
		}
		currentkeys.clear();
		for (int i = 0; i < NUM_KEYCODES; i++) {
			if (getKey(i)) {
				currentkeys.add(i);
			}
		}
		currentmouse.clear();
		for (int i = 0; i < NUM_MOUSEBUTTONS; i++) {
			if (getMouse(i)) {
				currentmouse.add(i);
			}
		}
	}

	private static boolean getKey(int keyCode) {
		return Keyboard.isKeyDown(keyCode);
	}

	public static boolean getKeyDown(int keyCode) {
		return downkeys.contains(keyCode);
	}

	public static boolean getKeyUp(int keyCode) {
		return upkeys.contains(keyCode);
	}

	public static boolean getMouse(int mouseButton) {
		return Mouse.isButtonDown(mouseButton);
	}

	public static boolean getMouseDown(int mouseButton) {
		return downMouse.contains(mouseButton);
	}

	public static boolean getMouseUp(int mouseButton) {
		return upMouse.contains(mouseButton);
	}

	public static Vector2f getMousePosition() {
		return new Vector2f(Mouse.getX(), Mouse.getY());
	}

}
