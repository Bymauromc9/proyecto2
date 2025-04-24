package proyecto2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private Stage escenarioPrincipal;
    private Pane contenedorPrincipal;

    @Override
    public void start(Stage escenarioPrincipal) throws IOException {
        this.escenarioPrincipal=escenarioPrincipal;

        this.escenarioPrincipal.setTitle("Ahorcado");
        mostrarAhorcadoPrincipal();
    }

    public void mostrarAhorcadoPrincipal(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("ahorcadoPrincipal.fxml"));
            contenedorPrincipal=(Pane) loader.load();

            // Mostrar la escena
            Scene scene = new Scene(contenedorPrincipal);
            escenarioPrincipal.setScene(scene);
            escenarioPrincipal.show();

            AhorcadoController controlador = loader.getController();
            controlador.setEscenarioPrincipal(escenarioPrincipal);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);   
    }

}