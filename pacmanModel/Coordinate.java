package pacmanModel;


public class Coordinate {
	//Champ(s)
	private int y;
	private int x;
	
	//Constructeur
	public Coordinate(int x, int y) {
		this.y = y;
		this.x = x;
	}
	
	//Méthode(s)
	public int getX() { return x; }
	
	public void setX(int x) { this.x = x; }

	public int getY() { return y; }
	
	public void setY(int y) { this.y = y; }
	
	@Override
	public boolean equals(Object obj) {
		Coordinate other = (Coordinate) obj;
		if (obj == null || x != other.x || y != other.y || getClass() != obj.getClass()) return false;
		if (this == obj) return true;
		return true;
	}
}
