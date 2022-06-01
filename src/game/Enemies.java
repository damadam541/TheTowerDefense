package game;
import java.awt.Rectangle;

public class Enemies extends Rectangle {
	int x;
	int y;
	int width;
	int height;
	int vx;
	int vy;

	Enemies(int x, int y, String type){
		this.x = x;
		this.y = y;
		setEnemyType(type);
	}
	
	void setEnemyType(String type) {
		if (type.equals("small")) {
			width = 10;
			height = 10;
			vx = 1;
			vy = 1;
		}
	}
	
}
