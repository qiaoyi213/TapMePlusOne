package TapMePlusOne;

import javafx.scene.paint.Color;

public class BlockColor {
	private final static String[] bgColor = {
			"",
			"FFB8D4",
			"A8B4FF",
			"C8C8C8",
			"FFF4D9",
			"FFAC9C",
			"FAC0E8",
			"A0B8FF",
			"D4D4D4",
			"FFEDCC",
			"FFA49C",
			"F4A8E2",
			"9CB8F7",
			"E0E0E0",
			"FFE8BF",
			"FF9C9C"
			
			
	};
	private final static String[] numColor = {
			"",
			"FF6B9B",
			"4E5CFF",
			"646464",
			"FF955A",
			"FF6868",
			"ED5EBF",
			"4869FF",
			"646464",
			"FF955A",
			"FA4F4F",
			"D740B8",
			"4469E5",
			"646464",
			"FF9154",
			"FF5353"
	};
	public static Color getBackgroundColor(int i) {
		return Color.web(bgColor[i]);
	}
	public static Color getNumberColor(int i) {
		return Color.web(numColor[i]);
	}
}
