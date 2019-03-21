package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Duvan Ricardo Cuero
 * @version 1.0 [03/2019]
 */
public class PacManGame {
	public final static String SCORE="./data";
	
	private int level;
	private ArrayList <PacMan> vectorPacMan;
	private Player [] scores;
	
	public PacManGame ()  {
		setVectorPacMan(new ArrayList<PacMan>());
	}
	/**
	 * @return the scores
	 */
	public Player [] getScores() {
		return scores;
	}

	/**
	 * @param scores the scores to set
	 */
	/**
	 * @param scores
	 */
	public void setScores(Player [] scores) {
		this.scores = scores;
	}
	/**
	 * @return the vectorPacman
	 */
	public ArrayList <PacMan> getVectorPacMan() {
		return vectorPacMan;
	}
	
	/**
	 * @param vectorPacMan the vectorPacMan to set
	 */
	public void setVectorPacMan(ArrayList <PacMan> vectorPacMan) {
		this.vectorPacMan = vectorPacMan;
	}
	
	public void loadGame(File file) throws IOException{
		vectorPacMan = new ArrayList<>();
		FileReader f = new FileReader(file);
		BufferedReader in = new BufferedReader(f);
		String linea = in.readLine().trim();
		level = (linea.charAt(0) == '#')? Integer.parseInt(in.readLine().trim()): Integer.parseInt(linea);
		linea = in.readLine();
		while (linea != null && !linea.equals("")) {
			if(linea.charAt(0) == '#')
				linea = in.readLine();
			
			String[] datos = linea.split("\t");
			boolean catched = Boolean.parseBoolean(datos[6]);
			int rebotes = Integer.parseInt(datos[5]);
			PacMan p = new PacMan(Integer.parseInt(datos[0])*2, Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), datos[4],rebotes , catched);
			vectorPacMan.add(p);
			linea = in.readLine();
		}
	
	}
	public String registScore(String r,int j) {
		boolean flag=true;
		String msj="";
		Player jk=new Player(r,j);
		for(int n=0;n<scores.length-1;n++) {
			for(int i=0;i<scores.length-1-n && flag;i++) {
				if(j > scores[i].getScore() ) {
					Player tmp = scores[i];
					scores[i] =jk;
					scores[i+1] = tmp;
					msj="The Score have been save in the position:"+i;
					flag=false;
					}
				}
			}
		return msj;
	}
	
	public boolean isInTheTop(int r) {
		boolean flag=false;
			if(r < scores[scores.length-1].getScore()) {
				flag=true;
			}
		return flag;
	}
	
	public void saveGame(File f) throws IOException{
		PrintWriter p = new PrintWriter(f);
		
		p.println("#nivel");
		p.println(level);
		p.println("#radio posX posY espera direccion rebotes");
		for(PacMan b: vectorPacMan)
			p.println((b.getRadio()/2)+"\t"+b.getPosX()+"\t"+b.getPosY()+"\t"+b.getWait()+"\t"
		+b.getDirection()+"\t"+b.getCounter()+"\t"+b.getCathched());
		
		p.close();
	}
	
	public void stapPacMan(int x, int y) {
		for (int i = 0; i < vectorPacMan.size(); i++) {
			PacMan p = vectorPacMan.get(i);
			if(p.catched(x, y)){
				p.setCathched(true);;
			}
		}
	}
	public int countScore(){
		int totalReb = 0;
		for(PacMan p: vectorPacMan){
			totalReb += p.getCounter();
		}
		return totalReb;
	}
	
}