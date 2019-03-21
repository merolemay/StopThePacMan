package Model;

import java.awt.Color;

import javafx.scene.shape.Arc;

/**
 * @author duvan
 *
 */
public class PacMan {
	
	public final static String IZQUIERDA = "IZQUIERDA"; 
	public final static String DERECHA   = "DERECHA"; 
	public final static String ARRIBA    = "ARRIBA"; 
	public final static String ABAJO     = "ABAJO";
	public final static int MOVEMENT     = 1;
	
	
	private int radio,posX, posY,wait;
	private String direction;
	private int counter;
	private boolean cathched;
	private Color color;
	
	/**
	 * @param radio
	 * @param posX
	 * @param posY
	 * @param wait
	 * @param direction
	 * @param counter
	 * @param cathched
	 */
	public PacMan(int radio, int posX, int posY, int wait, String direction, int counter, boolean cathched) {
		this.radio = radio;
		this.posX = posX;
		this.posY = posY;
		this.wait = wait;
		this.direction = direction;
		this.counter = counter;
		this.cathched = cathched;
		setColor(new Color(255,255,0));
	}
	
	/**
	 * @return the radio
	 */
	public int getRadio() {
		return radio;
	}

	/**
	 * @param radio the radio to set
	 */
	public void setRadio(int radio) {
		this.radio = radio;
	}

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * @return the wait
	 */
	public int getWait() {
		return wait;
	}

	/**
	 * @param wait the wait to set
	 */
	public void setWait(int wait) {
		this.wait = wait;
	}

	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * @return the cathched
	 */
	public boolean getCathched() {
		return cathched;
	}

	/**
	 * @param cathched the cathched to set
	 */
	public void setCathched(boolean cathched) {
		this.cathched = cathched;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	public boolean catched(int x, int y){
		boolean estaAt = false;
		if(posX<=x && x<=posX+radio && posY<=y && y<=posY+radio){
			estaAt = true;
		}
		return estaAt;
	}
	public void bite(int finalWidth, int finalHigh){
		switch(direction){
			case IZQUIERDA:
				posX -= MOVEMENT;
			break;
			case DERECHA:
				posX += MOVEMENT;
			break;
			case ARRIBA:
				posY -= MOVEMENT;
			break;
			case ABAJO:
				posY += MOVEMENT;
			break;
		}
		isInside(finalWidth, finalHigh);
	}
	private void isInside(int finalWidth, int finalHigh) {
		if(posX+radio>finalWidth){
			direction = IZQUIERDA;
			posX = finalWidth-radio;
			counter++;
		}
		if(posX<0){
			direction = DERECHA;
			posX=0;
			counter++;
		}
		if(posY+radio>finalHigh){
			direction = ARRIBA;
			posY = finalHigh-radio;
			counter++;
		}
		if(posY<0){
			direction = ABAJO;
			posY=0;
			counter++;
		}
	}
}
