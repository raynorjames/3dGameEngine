package objects;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	private static float distanceFromPlayer = 40;
	private static float angleAroundPlayer = 0;

	private Vector3f position = new Vector3f(0, 0, 0);;
	private static float pitch = 20;
	private static float yaw = 0;
	private float roll;
	private Player player;

	public Camera(Player player) {
		this.player = player;
		if (Player.getPerspective() == Player.FIRST_PERSON || Player.getPerspective() == Player.DEBUG) {
			Mouse.setGrabbed(true);
			player.setRotY(player.getRotY() - 180);
		}
	}

	public void move() {
		if (!Player.getPaused()) {
			if (Player.getPerspective() == Player.FIRST_PERSON) {
				position = new Vector3f(player.getPosition().x, player.getPosition().y + 8f, player.getPosition().z);
				calculatePitchFirstPerson();
				calculateYawFirstPerson();
			}
			if (Player.getPerspective() == Player.THIRD_PERSON) {
				calculateZoom();
				CalculateYaw();
				calculatePitch();
				calculateAngleAroundPlayer();
				float horizontalDistance = calculateHorizontalDistance();
				float verticalDistance = calculateVerticalDistance();
				calculateCameraPostion(horizontalDistance, verticalDistance);
				yaw = 180 - (player.getRotY() + angleAroundPlayer);
			} else if (Player.getPerspective() == Player.DEBUG) {
				position = new Vector3f(player.getPosition().x, player.getPosition().y + 10, player.getPosition().z);
				calculatePitchFirstPerson();
				calculateYawFirstPerson();
			}
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public static float getPitch() {
		return pitch;
	}

	public static float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

	public static void setAngleAroundPlayerDistanceAroundPlayer(float aap, float dfp) {
		Camera.angleAroundPlayer = aap;
		Camera.distanceFromPlayer = dfp;
	}

	public static void setPitchYaw(float pitch, float yaw) {
		Camera.yaw = yaw;
		Camera.pitch = pitch;
	}

	private void calculateCameraPostion(float horizDistance, float verticDistance) {
		float theta = player.getRotY() + angleAroundPlayer;
		float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
		position.x = player.getPosition().x - offsetX;
		position.z = player.getPosition().z - offsetZ;
		position.y = player.getPosition().y + verticDistance + (player.getScale() * 10) - 1;
	}

	private float calculateHorizontalDistance() {
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}

	private float calculateVerticalDistance() {
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}

	public void calculateZoom() {
		float zoomLevel = Mouse.getDWheel() * 0.1f;
		distanceFromPlayer -= zoomLevel;
		CheckZoom(60, 10);
	}

	public void CheckZoom(float MaxZoom, float MinZoom) {
		if (distanceFromPlayer >= MaxZoom) {
			distanceFromPlayer = MaxZoom;
		}
		if (distanceFromPlayer <= MinZoom) {
			distanceFromPlayer = MinZoom;
		}
	}

	public void calculatePitch() {
		if (Mouse.isButtonDown(1)) {
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
			CheckPitch(0, 95);
		}
	}

	public void calculatePitchFirstPerson() {
		float pitchChange = Mouse.getDY() * 0.5f;
		pitch -= pitchChange;
		CheckPitch(-90, 90);
	}

	public void calculatePitchDebug() {
		if (Mouse.isButtonDown(1)) {
			float pitchChange = Mouse.getDY() * 0.5f;
			pitch -= pitchChange;
			CheckPitch(-90, 90);
		}
	}

	public void CalculateYawDebug() {
		if (Mouse.isButtonDown(0)) {
			float yawChange = Mouse.getDX() * 0.5f;
			yaw += yawChange;

		}
	}

	public void calculateYawFirstPerson() {
		float yawChange = Mouse.getDX() * 0.5f;
		yaw += yawChange;
		player.increaseRoation(0, -yawChange, 0);
	}

	public void CalculateYaw() {
		if (Mouse.isButtonDown(2)) {
			float angleChange = Mouse.getDX() * 0.3f;
			angleAroundPlayer -= angleChange;
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
			CheckPitch(0, 95);
		}
	}

	public void CheckPitch(float MinPitch, float MaxPitch) {
		if (pitch >= MaxPitch) {
			pitch = MaxPitch;
		}
		if (pitch <= MinPitch) {
			pitch = MinPitch;
		}
	}

	public void CheckPitchfirst(float MinPitch, float MaxPitch) {
		if (pitch >= -MaxPitch) {
			pitch = -MaxPitch;
		}
		if (pitch <= MinPitch) {
			pitch = MinPitch;
		}
	}

	public void CheckYaw(float MinYaw, float MaxYaw) {
		if (yaw >= MaxYaw) {
			yaw = MaxYaw;
		}
		if (yaw <= MinYaw) {
			yaw = MinYaw;
		}
	}

	public void calculateAngleAroundPlayer() {
		if (Mouse.isButtonDown(0)) {
			float angleChange = Mouse.getDX() * 0.3f;
			angleAroundPlayer -= angleChange;
		}
	}
}
