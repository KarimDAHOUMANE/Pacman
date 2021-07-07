package pacmanModel;

import java.awt.Color;


public class PacgommeInvisible implements PacgommeType{

	@Override
	public Color getColor() { return Color.magenta; }

	@Override
	public int getPoints() { return 300; }

}
