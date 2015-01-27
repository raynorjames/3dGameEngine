package toolbox;

public class StaticLocation {
	public static String defaultDirectory(String Folder) {
		if (Folder.equalsIgnoreCase("models")) {
			String loc1 = "res/models/";
			return loc1;
		} else if (Folder.equalsIgnoreCase("songs")) {
			String loc1 = "res/songs/";
			return loc1;
		} else if (Folder.equalsIgnoreCase("textures")) {
			String loc1 = "res/textures/";
			return loc1;
		} else if (Folder.equalsIgnoreCase("sounds")) {
			String loc1 = "res/sounds/";
			return loc1;
		} else if (Folder.equalsIgnoreCase("shader")) {
			String loc1 = "res/shaders/";
			return loc1;
		} else if (Folder.equalsIgnoreCase("frames")) {
			String loc1 = "res/frames/";
			return loc1;
		} else if (Folder.equalsIgnoreCase("font")) {
			String loc1 = "res/font/";
			return loc1;
		}
		return "ERROR";
	}
}
