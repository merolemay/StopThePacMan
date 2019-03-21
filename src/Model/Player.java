package Model;

import java.io.Serializable;
/**
 * @author Duvan Ricardo Cuero
 * @version 1.0 [03/2019]
 */
public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int score;
	
	/**
	 * @param name
	 * @param score
	 */
	public Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * @param A the player score board that will be ordered from the smallest number to the biggest
	 */
	public void topScorer(Player[] A) {
        int i, j;
		Player aux;
        for (i = 0; i < A.length - 1; i++) {
            for (j = 0; j < A.length - i - 1; j++) {
                if (A[j + 1].getScore() < A[j].getScore()) {
                    aux = A[j + 1];
                    A[j + 1] = A[j];
                    A[j] = aux;
                }
            }
        }
	}
}
