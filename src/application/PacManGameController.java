package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Model.PacManGame;
import Thread.PacManThread;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class PacManGameController implements Initializable {
	
	public PacManGameController() {
	}
	
    @FXML
    private AnchorPane boxM;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu fileBar;

    @FXML
    private MenuItem loader;

    @FXML
    private Menu optionsBar;

    @FXML
    private Label counterOfBounces;

	private PacManGame pacManGame;
	
	@FXML
	private AnchorPane f;
	
	public int getWidth(){
		return (int) f.getWidth();
	}
	
	public int getHigh(){
		return (int) f.getHeight();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pacManGame = new PacManGame();
		f = new AnchorPane();
		f.setPrefSize(300,200);
		counterOfBounces.setText("Bounces:"+pacManGame.countScore());
		boxM.getChildren().add(f);
		counterOfBounces.setText(""+pacManGame.countScore());
		while(!loadTheFile());
		turnInR();
	}
	
	public void gamePacmans() {
		PacManThread[] hilos = new PacManThread[pacManGame.getVectorPacMan().size()];
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new PacManThread(pacManGame.getVectorPacMan().get(i));
			hilos[i].start();
		}	
	}
	public void turnInR() {
		for (int i = 0; pacManGame.getVectorPacMan() != null && i < pacManGame.getVectorPacMan().size(); i++) {
			Arc arc = new Arc();
			arc.setCenterX(pacManGame.getVectorPacMan().get(i).getPosX()); 
			arc.setCenterY(pacManGame.getVectorPacMan().get(i).getPosY()); 
			arc.setRadiusX(20.0f); 
			arc.setRadiusY(20.0f); 
			arc.setStartAngle(40.0f); 
			arc.setLength(239.0f); 
			arc.setFill(Color.YELLOW);
			arc.setType(ArcType.ROUND);
			boxM.getChildren().add(arc);
			gamePacmans();
		}
	}
	public boolean loadTheFile() {
		boolean cargoArchivo = false;
		JFileChooser fileCh = new JFileChooser("./data");
		int opcion = fileCh.showOpenDialog(fileCh);
		
		switch(opcion){	
			case JFileChooser.APPROVE_OPTION:
				File f = fileCh.getSelectedFile();
				try{
					pacManGame.loadGame(f);
					pacManGame.setVectorPacMan(pacManGame.getVectorPacMan());;
					gamePacmans();
					cargoArchivo = true;
				}catch(Exception ioexc){
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			break;
			case JFileChooser.CANCEL_OPTION:				
			break;
			case JFileChooser.ERROR_OPTION:
			break;
		}
		return cargoArchivo;
	}
	
	public void mouseClickCatch(MouseEvent e) {
		if(!pacManGame.getVectorPacMan().isEmpty())
			pacManGame.stapPacMan((int)e.getX(),(int) e.getY());
	}
}

