package oving9;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class LabyrintRute extends JLabel {
	private Type type;
	private char symbol;
	private boolean oppdaget;
	private boolean harSpiller;
	
	public LabyrintRute(char symbol) {
		this.symbol = symbol;
		oppdaget = false;
		harSpiller = false;
		setOpaque(true);
		setBackground(Color.DARK_GRAY);
		
		// Set Type based on symbol
		setType();
		//setBackground(type.getColor());
	}
	
	public Type getType() {
		return type;
	}

	private void setType() {
		switch(symbol) {
			case ' ':	type = Type.GANG;		break;
			case '#':	type = Type.VEGG;		break;
			case '-':	type = Type.UTGANG;		break;
			case '*':	type = Type.SPILLER;	break;
			default:	type = null;
			JOptionPane.showMessageDialog(getParent(), "Illegal Tile Type. "
					+ " The character \""
					+ symbol + "\" is not an accepted type of tile. Please fix the"
							+ " text file.");
			System.exit(0);
		}
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public void setIkon() {
		setIcon(new ImageIcon("res/" + type.getPath()));
	}
	
	public void setBG() {
		setBackground(type.getColor());
	}

}
