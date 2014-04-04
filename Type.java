package oving9;

import java.awt.Color;

public enum Type {
	GANG ("gang.png", Color.BLACK),
	VEGG ("vegg.png", Color.GRAY),
	UTGANG ("animated_woodencastledoor.gif", Color.RED),
	SPILLER (null, Color.CYAN);
	
	private final String path;
	private final Color color;
	
	Type (String path, Color color) {
		this.path = path;
		this.color = color;
	}
	
	public String getPath() {
		return path;
	}

	public Color getColor() {
		return color;
	}
}
