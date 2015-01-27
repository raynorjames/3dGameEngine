package toolbox;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;

public class Audio {
	private static Mixer mixer;
	private String RES_LOC_SONGS = StaticLocation.defaultDirectory("songs");
	private String RES_LOC_SOUNDS = StaticLocation.defaultDirectory("sounds");
	private File soundURL;
	public static Clip clip;
	public static int SONG = 0;
	public static int SOUND = 1;

	public void loadAudio(String AudioName, int Type, float AudioLevel) {
		Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
		mixer = AudioSystem.getMixer(mixInfos[0]);
		DataLine.Info datatInfo = new DataLine.Info(Clip.class, null);
		try {
			clip = (Clip) mixer.getLine(datatInfo);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			if (Type == 0) {
				soundURL = new File(RES_LOC_SONGS + AudioName + ".wav");
			} else if (Type == 1) {
				soundURL = new File(RES_LOC_SOUNDS + AudioName + ".wav");
			} else {
				soundURL = null;
				System.err.println("Out of bounds.");
			}
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
			clip.open(audioStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (AudioLevel > 6) {
			AudioLevel = 6f;
		}
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(AudioLevel);
		clip.start();
	}

	public boolean isPlaying() {
		return clip.isActive();
	}

	public void LoadLoopedClip(String songName, float Audiolevel) {
		Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
		mixer = AudioSystem.getMixer(mixInfos[0]);
		DataLine.Info datatInfo = new DataLine.Info(Clip.class, null);
		try {
			clip = (Clip) mixer.getLine(datatInfo);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			File soundURL = new File(RES_LOC_SONGS + songName + ".wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
			clip.open(audioStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (Audiolevel > 6) {
			Audiolevel = 6f;
		}
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(Audiolevel);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		clip.stop();
	}
}
