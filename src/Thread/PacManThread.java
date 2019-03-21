package Thread;

import Model.PacMan;
import application.PacManGameController;

public class PacManThread extends Thread {
	
	private PacMan p;
	public PacManThread(PacMan p) {
		this.p = p;
	}

	@Override
	public void run() {
		while(!p.getCathched()){
			try {
				p.bite(450,400);
				Thread.sleep(p.getWait());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
