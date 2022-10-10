package MVC.model.SpecialMoves;

import MVC.controller.PromotionController;
import MVC.model.Pieces.Piece;
import MVC.view.PromotionView;
import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PromotionHandler extends Application {

    PromotionView pv;
    PromotionController pc;
    PromotionChecker pcheck;
    public void buttonPressed(MouseEvent event, Piece p) {

    }

    public PromotionHandler() {
        Application.launch();
    }

    @Override
    public void init(){
        pv = new PromotionView();
        pc = new PromotionController();
        pcheck = new PromotionChecker();
    }

    @Override
    public void start(Stage stage) throws Exception {


    }
}
