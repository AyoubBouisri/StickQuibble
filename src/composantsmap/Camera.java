package composantsmap;

import geometrie.Vecteur;

public class Camera {
	public Vecteur position;
	public int width,height;
	
	public int vitesse = 3;
	/**
	 * 
	 * @param pos position vector  of the upper-left corner of the camera
	 * @param width the width of the vision of the camera in pixels
	 * @param height the height of the vision of the camera in pixels
	 */
	public Camera(Vecteur pos,int width,int height) {
		this.position = pos;
		this.width = width;
		this.height = height;
	}
}
