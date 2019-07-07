package pattern_maker;

import java.awt.Color;

public class ColorDetail extends Color{
	private String name;
	private String symbol;

	public ColorDetail(int r, int g, int b, String name, String symbol) {
		super(r, g, b);
		this.name = name;
		this.symbol = symbol;
		
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

}
