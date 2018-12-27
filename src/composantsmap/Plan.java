package composantsmap;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import geometrie.Vecteur;

public class Plan {
	private Vecteur position;
	private Vecteur posFinale;
	
	private Vecteur directionPlan;

	private Vecteur normale;

		

	private double longueur;

	/**
	 * Constructeur qui calcul la longueur,la direction et la normale d'apres les
	 * deux points dune droite finie
	 * 
	 * @param posInit
	 *            position initiale du plan
	 * @param posFinale
	 *            position finale du plan
	 * 
	 */
	public Plan(Vecteur posInit, Vecteur posFinale) {
	
		this.position = posInit;
		this.posFinale = posFinale;
		
		longueur = posFinale.dist(position);
		// trouverle vecteur direction du plan et le normaliser
		directionPlan = posFinale.soustrait(position);
		directionPlan = directionPlan.normalise();
		// trouver la normale perpendiculaire a la direction du vecteur
		normale = directionPlan.cross(new Vecteur(0, 0, 1));
		normale = normale.normalise();

	}
	/**
	 * Method that draws the platform according to the position of the camera	
	 * @param g2d Object that draws the platform
	 * @param camera vector position of the camera according to the map
	 */
	public void dessinerDansEcran(Graphics2D g2d, Camera camera) {
	
		double xInitEcran = position.getX() - camera.position.getX();
		double yInitEcran = position.getY() - camera.position.getY();
		
		double xFinEcran = posFinale.getX() - camera.position.getX();
		double yFinEcran = posFinale.getY() - camera.position.getY();

		g2d.setStroke(new BasicStroke(1.5f));
		g2d.setColor(Color.white);
		
		Line2D.Double line = new Line2D.Double(xInitEcran, yInitEcran, xFinEcran, yFinEcran);

		g2d.draw(line);
		
		
	}
	
	
	
	
	/**
	 * 
	 * @return La position Initiale du plan
	 */
	public Vecteur getPositionInit() {
		return position;
	}

	/**
	 * retourne le vecteur plan
	 * 
	 * @return La direction du plan
	 */
	public Vecteur getDirection() {
		return directionPlan;
	}

	/**
	 * 
	 * @return La longueur du plan en metres
	 */
	public double getLongueur() {
		return longueur;
	}

	/**
	 * 
	 * @return la position finale du plan
	 */
	public Vecteur getPosFin() {
		return posFinale;
	}
	
	/**
	 * 
	 * @return La normale du vecteur creer d'apres la regle de la main droite
	 */
	public Vecteur getNormale() {

		return normale;
	}
	
	/**
	 * Voir si deux plans sont egales
	 * 
	 * @return vrai si cest le meme plan et faux si ce n'est pas le meme plan
	 */
	public boolean equals(Plan plTemp) {
				
		return (position.equals(plTemp.getPositionInit()) && posFinale.equals(plTemp.getPosFin()));

	}
}
