package pacmanModel;

import java.awt.Graphics;


public abstract class Objects {
	public static enum Type { wall, blank, door }
	protected Coordinate current;
	protected Type type;
	protected int Scale = 26;
	public Type getObjectType() { return this.type; }
	protected abstract void draw(Graphics g);
}
