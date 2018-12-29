package geometrie;

import composantsmap.Plan;
import characters.Character;

public class Collision {
	/**
	 * Method that finds the closest point of a plan to a certain character
	 * 
	 * @param character
	 *            The character
	 * @param pl
	 *            The plane that contains the point
	 * @return The point on the plane closeset to the character
	 */
	public static Vecteur getClosestPtInPlane(Character character, Plan pl) {
		Vecteur charPlan = character.centrePos.soustrait(pl.getPositionInit());
		Vecteur projection = Vecteur.projection(charPlan, pl.getDirection());
		Vecteur ptPlan = pl.getPositionInit().additionne(projection);

		Vecteur projectionTest = projection.normalise();

		if (projectionTest.equals(pl.getDirection().multiplie(-1))) {
			// Le point le plus proche de la balle est donc le pointInit du plan
			return pl.getPositionInit();

		} else if (projection.module() > pl.getLongueur()) {
			// Le point le plus proche de la balle est donc le pointFinale du plan
			return pl.getPosFin();
		}

		return ptPlan;
	}

	/**
	 * Method that checks if the character is in collision with a floor
	 * 
	 * @param character The character that may be in collision
	 * @param pl The plane that may be in collision with the character
	 * @return Null if not in collision or the point of collision if there is a collision.
	 */
	public static Vecteur inCollisionFloor(Character character, Plan pl) {
		Vecteur ptPlan = getClosestPtInPlane(character, pl);

		double distance = character.centrePos.dist(ptPlan);
		
		if (distance <= character.HEIGHT / 2) {
			
			correctionFloor(character,ptPlan,pl);
			return ptPlan;
		}
		return null;
	}

	/**
	 * Method that corrects the position of the character according to a floor so
	 * the character does not get stuck inside it.
	 * 
	 * @param c1
	 *            The character
	 * @param pt
	 *            Closest point to the character on the plane
	 */
	public static void correctionFloor(Character c1, Vecteur pt,Plan pl) {
		
		double dist = c1.centrePos.dist(pt);
		double distDesiree = c1.HEIGHT / 2;

		Vecteur correctionDir = getNormaleCollision(c1, pt);
		correctionDir.setModule(distDesiree - dist);

		c1.setPosition(c1.position.additionne(correctionDir));

	}

	/**
	 * Method that gets the normal vector of the plan in collision with the
	 * character
	 * 
	 * @param character
	 * @param pt
	 *            Closest point to the character on the plane
	 * @return Normal vector of the plan in direction of the character in collision
	 */
	public static Vecteur getNormaleCollision(Character character, Vecteur pt) {
		Vecteur normale = character.centrePos.soustrait(pt);
		normale = normale.normalise();
		return normale;

	}
}
