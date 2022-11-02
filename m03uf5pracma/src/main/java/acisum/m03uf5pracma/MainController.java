package acisum.m03uf5pracma;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class MainController extends Controller implements Initializable {

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
        layoutBottomController.setControllers(controllers);}
    
    
}
