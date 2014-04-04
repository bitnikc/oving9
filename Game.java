package oving9;

import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;

public class Game {

	public static final int TILE_WIDTH = 64;
	public static final int TILE_HEIGHT = 64;
	public static int mapWidth;
	public static int mapHeight;
	public static int[] player = new int[4];
	
	public static LabyrintRute[][] readFile() {
		mapHeight = 0;
		mapWidth = 0;
		Scanner filLeser = null;
		LabyrintRute[][] map = null;
		char symbol;
		try {
			String linje = "";
			//FileInputStream filStrom = new FileInputStream("res/testlabyrint.txt");
			//FileInputStream filStrom = new FileInputStream("res/test_hampton_court.txt");
			FileInputStream filStrom = new FileInputStream("res/ShiningintheDarkness.txt");
			filLeser = new Scanner(filStrom);
			mapWidth = filLeser.nextInt();
			mapHeight = filLeser.nextInt();
			filLeser.nextLine();
			map = new LabyrintRute[mapHeight][mapWidth];
			
			for (int rows = 0; rows < mapHeight; rows++){
				linje = filLeser.nextLine();
				for (int cols = 0; cols < mapWidth; cols++) {
					symbol = linje.charAt(cols);
					if (symbol == '*') {
						player[1] = rows;
						player[0] = cols;
					}
					if (symbol == '-'){
						player[2] = rows;
						player[3] = cols;
					}
					map[rows][cols] = new LabyrintRute(symbol);
					if (symbol == '-')
						map[rows][cols].setBG();
					
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(filLeser != null) filLeser.close();
		}
		
		//System.out.println(height + " " + width);
		return map;
	}

	public static void main(String[] args) {

		long startTime = System.nanoTime();
		LabyrintRute[][] map = readFile();
		
		JFrame vindu = new JFrame("Labyrint spill");
		
		vindu.setLayout(new GridLayout(mapHeight, mapWidth));
		
		for (LabyrintRute[] row: map)
			for (LabyrintRute square: row) {
				//square.setIkon();
				vindu.add(square);
			}
		
		Controller ctrl = new Controller(map, player, startTime);
		vindu.addKeyListener(ctrl);

		int width = TILE_WIDTH*mapWidth;
		int height = TILE_HEIGHT*mapHeight;
		
		vindu.setSize(width, height);
		//System.out.println("Height: " + height + "\nWidth: "	+ width);
		
		vindu.setResizable(false);
		vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//vindu.pack();
		vindu.setVisible(true);
	}

}
