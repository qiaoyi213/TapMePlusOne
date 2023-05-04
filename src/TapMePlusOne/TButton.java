package TapMePlusOne;
import javafx.scene.control.Button;

public class TButton extends Button{
	private int i, j;
	public void setPos(int x,int y) {
		this.i = x;
		this.j = y;
	}
	public int getI() {
		return this.i;
	}
	public int getJ() 
	{
		return this.j;
	}
}
