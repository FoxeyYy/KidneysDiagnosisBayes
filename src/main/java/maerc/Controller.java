package maerc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    private VBox resultsPane;

    @FXML
    protected void handleAnalysis(ActionEvent actionEvent) {
        final Label illnessName = new Label();
        illnessName.setText("Illness" + ":");

        final ProgressBar illnessProbability = new ProgressBar();
        illnessProbability.setProgress(0.9);

        final HBox illness = new HBox();
        illness.setAlignment(Pos.CENTER);

        illness.getChildren().addAll(illnessName, illnessProbability);

        resultsPane.getChildren().add(illness);
    }
}
