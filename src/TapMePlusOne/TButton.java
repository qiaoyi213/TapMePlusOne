package TapMePlusOne;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class TButton extends Button{
	private int i, j;
	public TButton() {
		
		super();
		this.setOnMousePressed(e ->{
			darkerColor();
		});
		this.setOnMouseReleased(e ->{
			updateColor();
		});
	}
	
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
	
	public void setVal(int val) {
		this.setText(Integer.toString(val));
		updateColor();
	}
	
	public void updateColor() {
		this.setBackground(Background.fill(BlockColor.getBackgroundColor(Integer.parseInt(this.getText()))));
		this.setTextFill(BlockColor.getNumberColor(Integer.parseInt(this.getText())));
	}
	
	public void darkerColor() {
		this.setBackground(Background.fill(BlockColor.getBackgroundColor(Integer.parseInt(this.getText())).darker()));
		this.setTextFill(BlockColor.getNumberColor(Integer.parseInt(this.getText())).darker());
	}
	public int getButtonNumber() {
		return Integer.parseInt(this.getText());
	}
	
}
