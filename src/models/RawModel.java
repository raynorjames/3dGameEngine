package models;

public class RawModel {

	private int vaoID;
	private int vertexCount;

	public RawModel(int vaoid, int vertexcount) {
		this.vaoID = vaoid;
		this.vertexCount = vertexcount;
	}

	public int getVaoID() {
		return vaoID;
	}

	public void setVaoID(int vaoID) {
		this.vaoID = vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}

}
