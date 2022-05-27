package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

public class Main extends JFrame {

	final static int WIN = 720;
	final static int SIZE = 10;

	PlayingField panel;
	int[][] field = new int[SIZE][SIZE];

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
	}

	private class PlayingField extends JPanel {

		private int boxW, boxH; //size of one box/square
		final static int path = 1;

		PlayingField(){
			this.setBackground(Color.BLACK); //light gray
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			initGraphics();
			lvl1();

			//drawGrid
			g.setColor(Color.WHITE);
			for (int i = 0; i < SIZE ; i++) {
				g.drawLine(i*boxW, 0 , i*boxW, WIN);
				g.drawLine(0, i*boxH, WIN , i*boxH);
			}
			
			for (int x = 0 ; x < SIZE ; x++) {
				for (int y = 0 ; y < SIZE ; y++) {
					if (field[x][y] == 1) {
						g.setColor(Color.GRAY);
						g.fillRect(x*boxW, y*boxH, boxW, boxH);
					}
				}
			}
			
			//GRID DEBUG
			g.setColor(Color.WHITE);
			for (int x = 0 ; x < SIZE ; x++) {
				for (int y = 0 ; y < SIZE ; y ++) {
					g.drawString(x+" "+y, x*boxW+20, y*boxH+20);
				}
			}

		}
		
		void initGraphics() {
			boxW = (int) ((WIN/SIZE) + 0.5);
			boxH = (int) ((WIN/SIZE) + 0.5);
		}
		
		void lvl1() {
			for (int i = 0 ; i < 3 ; i++) {
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
			for (int i = 6 ; i < 10 ; i++) {
				field[i][7] = path;
			}
			
		}

	}
}
