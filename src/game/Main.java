package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

public class Main extends JFrame implements ActionListener {

	final static int WIN = 720;
	final static int SIZE = 16;

	PlayingField panel;
	int[][] field = new int[SIZE][SIZE];
	
	private int boxW, boxH;
	
	Timer timer;
	final int timerSpeed = 1;
	
	final static int path = 1;
	final static int pathStart = 2;
	final static int pathEnd = -1;
	
	int wave = 1;
	
	ArrayList<Enemies> enemies = new ArrayList<Enemies>();

	public static void main(String[] args) {
		new Main();
	}

	Main(){
		this.setTitle("The Tower Defense");
		this.setSize(WIN, WIN);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new PlayingField();
		this.add(panel);

		panel.setPreferredSize(new Dimension(WIN,WIN));
		this.pack();
		this.setLocationRelativeTo(null);

		this.setVisible(true);
		timer = new Timer(timerSpeed,this);
		timer.start();
	}
	
	void initBox() {
		boxW = (int) ((WIN/SIZE) + 0.5);
		boxH = (int) ((WIN/SIZE) + 0.5);
	}
	
	void lvl1() {
		field[0][5] = pathStart;
		for (int i = 1 ; i < 3 ; i++) {
			field[i][5] = path;
		}
		for (int i = 5 ; i > 1 ; i--) {
			field[3][i] = path;
		}
		for (int i = 3 ; i < 7 ; i++) {
			field[i][2] = path;
		}
		for (int i = 2 ; i < 8 ; i++) {
			field[6][i] = path;
		}
		for (int i = 6 ; i < 9 ; i++) {
			field[i][7] = path;
		}
		for (int i = 7 ; i < 10 ; i++) {
			field[9][i] = path;
		}
		for (int i = 9 ; i > 1 ; i--) {
			field[i][9] = path;
		}
		for (int i = 9 ; i < 14 ; i++) {
			field[2][i] = path;
		}
		for (int i = 2 ; i < 15 ; i++) {
			field[i][13] = path;
		}
		field[15][13] = pathEnd;
	}
	
	void spawnEnemies() {
		int spawnX = 0;
		int spawnY = 0;
		for (int x = 0 ; x < SIZE ; x++) {
			for (int y = 0 ; y < SIZE ; y++) {
				if (field[x][y] == 2) {
					spawnX = field[x][y]*boxW;
					spawnY = field[x][y]*boxH;
				}
			}
		}
		if (wave == 1) {
			for (int i = 0 ; i < (Math.pow(2, wave)) ; i++) {
				enemies.add(new Enemies(spawnX,spawnY,"small"));
			}
		}
	}

	private class PlayingField extends JPanel {

		PlayingField(){
			this.setBackground(Color.BLACK); //light gray
			spawnEnemies();
			initBox();
			lvl1();
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			//drawGrid
			g.setColor(Color.WHITE);
			for (int i = 0; i < SIZE ; i++) {
				g.drawLine(i*boxW, 0 , i*boxW, WIN);
				g.drawLine(0, i*boxH, WIN , i*boxH);
			}
			
			for (int x = 0 ; x < SIZE ; x++) {
				for (int y = 0 ; y < SIZE ; y++) {
					if (field[x][y] == path) {
						g.setColor(Color.GRAY);
						g.fillRect(x*boxW, y*boxH, boxW, boxH);
					}
					if (field[x][y] == pathStart) {
						g.setColor(Color.GREEN);
						g.fillRect(x*boxW, y*boxH, boxW, boxH);
					}
					if (field[x][y] == pathEnd) {
						g.setColor(Color.RED);
						g.fillRect(x*boxW, y*boxH, boxW, boxH);
					}
				}
			}
			
			g2.setColor(Color.WHITE);
			for (int i = 0 ; i < enemies.size() ; i++) {
				g2.fill(enemies.get(i));
			}
			
			//GRID DEBUG
			g.setColor(Color.WHITE);
			for (int x = 0 ; x < SIZE ; x++) {
				for (int y = 0 ; y < SIZE ; y ++) {
					g.drawString(x+" "+y, x*boxW+10, y*boxH+20);
				}
			}

		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*for (int i = 0 ; i < enemies.size() ; i++) {
			int x = enemies.get(i).x;
			int y = enemies.get(i).y;
			int vx = enemies.get(i).vx;
			int vy = enemies.get(i).vy;
			
			x = x + vx;
			
			enemies.get(i).x = x;
			enemies.get(i).y = y;
			
		}*/
		
		int x = enemies.get(0).x;
		int y = enemies.get(0).y;
		int vx = enemies.get(0).vx;
		int vy = enemies.get(0).vy;
		
		x = x + vx;
		
		enemies.get(0).x = x;
		enemies.get(0).y = y;
		
		panel.repaint();
	}
}
