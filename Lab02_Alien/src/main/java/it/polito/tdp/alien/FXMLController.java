package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private AlienDictionary alienDictionary = new AlienDictionary();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnTranslate;

    @FXML
    private TextArea txtArea;

    @FXML
    private Button btnReset;

    @FXML
    void doReset(ActionEvent event) {
    	txtParola.clear();
    	txtArea.clear();
    	alienDictionary.resetDictionary();  	

    }

    @FXML
    void doTranslate(ActionEvent event) {
    	txtArea.clear();
    	String riga = txtParola.getText().toLowerCase();
    	
    	//Controllo input
    	if(riga == null || riga.length() == 0) {
    		txtArea.setText("Inserire una o due parole.");
    		return;
    	}
    	
    	StringTokenizer st = new StringTokenizer(riga, " ");
    	
    	//Controllo su String Tokenizer (superfluo)
    	if(!st.hasMoreElements()) {
    		txtArea.setText("Inserire una o due parole.");
    		return;
    	}
    	
    	//Estraggo la parola
    	String alienWord = st.nextToken();
    	
    	if(st.hasMoreTokens()) {
    		//Estraggo la traduzione
    		String translation = st.nextToken();
    		
    		if(!alienWord.matches("[a-zA-Z]*") || !translation.matches("[a-zA-Z]*")) {
    			txtArea.setText("Inserire solo caratteri alfabetici.");
    			return;
    		}
    		
    		//Aggiungo la parola aliena e traduzione nel dizionario
    		alienDictionary.addWord(alienWord, translation);
    		txtArea.setText("La parola: " + alienWord + " con traduzione: " + translation + " è stata inserita nel dizionario.");
    		
    	} else {
    		//Controllo che non ci siano caratteri non ammessi
    		if(!alienWord.matches("[a-zA-Z]*")) {
    			txtArea.setText("Inserire solo caratteri alfabetici.");
    			return;
    		}
    		
    		String translation;
    		
    		if(alienWord.matches("[a-zA-Z]*") && !alienWord.matches("[a-zA-Z]*")) {
    			
    			translation = alienDictionary.translateWordWildCard(alienWord);
    		} else {
    			
    			translation = alienDictionary.translateWord(alienWord);    			
    		}
    		 		
    		
    		if(translation != null) {
    			txtArea.setText(translation);
    		} else {
    			txtArea.setText("La parola cercata non esiste nel dizionario.");
    		}
    	}

    }

    @FXML
    void initialize() {
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTranslate != null : "fx:id=\"btnTranslate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
