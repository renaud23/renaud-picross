package hama.view;

public abstract class MouseZone {

	private int x, y, width, height;

	public MouseZone() {}

	public MouseZone(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void mouseEntered(int x, int y);

	public abstract void mouseExited();

	public abstract void mouseMove(int x, int y);

	public abstract void mousePressed(int button, int x, int y);

	public abstract void mouseReleased(int button, int x, int y);

	public boolean isIn(int x, int y) {
		boolean state = false;
		if (x >= this.x && x <= (this.x + this.width) && y >= this.y && y <= (this.y + this.height)) {
			state = true;
		}
		return state;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
