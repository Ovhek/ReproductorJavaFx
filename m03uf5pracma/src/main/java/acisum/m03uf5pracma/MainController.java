package acisum.m03uf5pracma;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainController extends Controller implements Initializable {

    @FXML
    BorderPane layoutMain;
    @FXML
    VBox responsiveVbox;
    @FXML
    AnchorPane layoutTop;
    @FXML
    AnchorPane layoutBottom;
    @FXML
    AnchorPane layoutLeft;
    @FXML
    AnchorPane layoutCenter;
    
    @FXML
    LayoutTopController layoutTopController;
    @FXML
    LayoutLeftController layoutLeftController;
    @FXML
    LayoutCenterController layoutCenterController;
    @FXML
    LayoutBottomController layoutBottomController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //inicialitzem hashmap que ha de contenir tots els controllers
        this.controllers = new HashMap<>();
        
        //els afegim
        this.controllers.put(layoutTopController.getClass().getSimpleName(), layoutTopController);
        this.controllers.put(layoutLeftController.getClass().getSimpleName(), layoutLeftController);
        this.controllers.put(layoutCenterController.getClass().getSimpleName(), layoutCenterController);
        this.controllers.put(layoutBottomController.getClass().getSimpleName(), layoutBottomController);
        
        // injectem el hashmap amb tots els controllers a cadascun dels controllers
        // d'aquesta forma tots poden accedir a tots
        layoutTopController.setControllers(controllers);
        layoutLeftController.setControllers(controllers);
        layoutCenterController.setControllers(controllers);
        layoutBottomController.setControllers(controllers);
        
        layoutMain.widthProperty().addListener((ov,oldVal, newVal) ->{
            if (newVal.intValue() < 600){
                responsiveVbox.getChildren().add(layoutLeft);
            }
            else{
                layoutMain.setLeft(layoutLeft);
                layoutLeft.setTopAnchor(layoutLeft, 100.0);
            }
        });
    }    
}
