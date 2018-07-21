/**
 * Sample Skeleton for 'SerieA.fxml' Controller Class
 */

package it.polito.tdp.seriea;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.model.Model;
import it.polito.tdp.seriea.model.PuntiSquadra;
import it.polito.tdp.seriea.model.Season;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class SerieAController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxSeason"
    private ChoiceBox<Season> boxSeason; // Value injected by FXMLLoader

    @FXML // fx:id="boxTeam"
    private ChoiceBox<?> boxTeam; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    Model model;

    @FXML
    void handleCarica(ActionEvent event) {
    	
    	try {
    		txtResult.clear();
			Season s = boxSeason.getValue();
			if (s == null) {
				System.out.println("Selezionare una stagione!");
				txtResult.setText("Selezionare una stagione!");
				return;
			}

			List<PuntiSquadra> parziale = model.creaGrafo(s);
			
			List<PuntiSquadra> classificaFinale = model.calcolaClassifica(parziale);
			
			for (PuntiSquadra ps : classificaFinale) {
				txtResult.appendText(ps.toString());
			}
			

		} catch (RuntimeException e) {
			e.printStackTrace();
			txtResult.setText("Errore di connessione al DB!");
		}

    }

    @FXML
    void handleDomino(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxSeason != null : "fx:id=\"boxSeason\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert boxTeam != null : "fx:id=\"boxTeam\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	boxSeason.getItems().addAll(model.getSeasons());
    }
}
