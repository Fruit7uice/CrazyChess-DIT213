package MVC.controller;

import MVC.model.SpecialMoves.Promotion;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller for the promotion action, with four buttons on which
 * you can make a choice for what piece you want to promote to.
 * @author Joel Leiditz Thorsson
 */
public class PromotionController {

    /**
     * Connected via the chooseBishop onAction in Promotion.fxml.
     * When pressed, run bishopChoice and shut down the PromotionView.
     */
    public void chooseBishop(ActionEvent event) throws IOException {
        System.out.println("you choose bishop");
        Promotion.bishopChoice();
        closeWindow(event);
    }

    /**
     * Connected via the chooseKnight onAction in Promotion.fxml.
     * When pressed, run knightChoice and shut down the PromotionView.
     */
    public void chooseKnight(ActionEvent event) throws IOException {
        System.out.println("you choose Knight");
        Promotion.knightChoice();
        closeWindow(event);
    }

    /**
     * Connected via the chooseQueen onAction in Promotion.fxml.
     * When pressed, run queenChoice and shut down the PromotionView.
     */
    public void chooseQueen(ActionEvent event) throws IOException {
        System.out.println("you choose Queen");
        Promotion.queenChoice();
        closeWindow(event);
    }

    /**
     * Connected via the chooseRook onAction in Promotion.fxml.
     * When pressed, run rookChoice and shut down the PromotionView.
     */
    public void chooseRook(ActionEvent event) throws IOException{
        System.out.println("you choose Rook");
        Promotion.rookChoice();
        closeWindow(event);
    }

    /**
     * Shuts down the promotionView.
     */
    private void closeWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Promotion.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.close();
    }

}
