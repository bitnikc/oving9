package oving9;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Controller implements KeyListener {
	private LabyrintRute[][] map;
	private int[] p;
	private long startTime;
	private JTextArea time;
	
	public Controller(LabyrintRute[][] map, int[] p, long startTime) {
		this.map = map;
		this.p = p;
		this.startTime = startTime;
		time = new JTextArea(String.valueOf((System.nanoTime()-startTime)/1000000));
		//map[p[2]][p[3]].add(time);
		removeFog();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		switch (arg0.getKeyCode()) {
			case KeyEvent.VK_UP:	newPosition(1, -1);	break;
			case KeyEvent.VK_DOWN: 	newPosition(1, 1);	break;
			case KeyEvent.VK_LEFT: 	newPosition(0, -1);	break;
			case KeyEvent.VK_RIGHT:	newPosition(0, 1);	break;
			default: break;
		}
		
		//time.setText(String.valueOf((System.nanoTime()-startTime)/1000000));

	}
	
	public void newPosition(int coordinate, int change) {

		if(safe(coordinate, change)){
			changeType(Type.GANG);
			p[coordinate] += change;
			changeType(Type.SPILLER);
			removeFog();
		}
		
	}
	
	public void removeFog(){
		int x, y;
		x = p[0];
		y = p[1];
		
		for(int i = -1; i < 2;i++){
			for (int j = -1; j<2; j++)
				map[y+i][x+j].setIkon();
		}
	}
	
	public boolean safe(int coordinate, int change) {
		int x = p[0];
		int y = p[1];
		if (coordinate == 1)
			y += change;
		else
			x += change;
		Type type = map[y][x].getType();
		if(type == Type.GANG)
			return true;
		else if (type == Type.UTGANG) {
			JOptionPane.showMessageDialog(null, "You WON!!! You made it "
					+ "in " + ((System.nanoTime()-startTime)/1000000000) + " seconds!!!");
			System.exit(0);
			return true;
		} else
			return false;
	}
	
	public void changeType(Type t) {
		int y = p[1];
		int x = p[0];
		map[y][x].setType(t);
		map[y][x].setIkon();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
