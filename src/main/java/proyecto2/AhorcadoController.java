package proyecto2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.LoadListener;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class AhorcadoController implements Initializable{

    @FXML 
    private ImageView imgCabeza;
    @FXML
    private ImageView imgCuerpo;
    @FXML
    private ImageView imgBrazoIzq;
    @FXML
    private ImageView imgBrazoDer;
    @FXML
    private ImageView imgPiernaIzq;
    @FXML
    private ImageView imgPiernaDer;
    @FXML
    private FlowPane panelBotones;
    @FXML
    private Label palabraSecreta;

    private int errores=0;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        botones();
        palabraFicheroRandomSecreta();
    }

    private Stage escenarioPrincipal;

    // Establecer el escenario principal
    public void setEscenarioPrincipal(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void palabraFicheroRandomSecreta(){
        List<String> palabras = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("util/palabras.txt")))){

            String linea;

            while ((linea = br.readLine())!=null) {
                palabras.add(linea.trim().toUpperCase());
            }

        } catch (IOException | NullPointerException e) {
            System.out.println("ERROR al cargar el archivo: "+e.getMessage());
        }

        String random = palabras.get(ThreadLocalRandom.current().nextInt(palabras.size()));
        String secreta="";

        for (int i = 0; i < random.length(); i++) {
            secreta+="_ ";
        }

        palabraSecreta.setText(secreta.trim());

    }

    public void botones(){

        String letras="ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

        for (int i = 0; i < letras.length(); i++) {
            Button boton = new Button(""+letras.charAt(i));
            boton.setOnAction(e-> System.out.println(boton.getText()));
            panelBotones.getChildren().add(boton);
        }
    }

    public void mostrarErrores(){

        errores++;

        switch (errores) {
            case 1 : imgCabeza.setVisible(true);break;
            case 2 : imgCuerpo.setVisible(true);break;
            case 3 : imgBrazoIzq.setVisible(true);break;
            case 4 : imgBrazoDer.setVisible(true);break;
            case 5 : imgPiernaIzq.setVisible(true);break;
            case 6 : imgPiernaDer.setVisible(true);
                perder();
            break;
        }
    }

    public void perder(){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Ahorcado");
        alerta.setContentText("Lo siento, has perdido");

        Image img = new Image(getClass().getResource("img/ahorcado.png").toExternalForm());
        ImageView imageView = new ImageView(img);

        alerta.setGraphic(imageView);
        alerta.showAndWait();
    }
    
}
