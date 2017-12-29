package maerc;

import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    @FXML
    private VBox illnessesPane;

    @FXML
    private VBox previousInfoPane;

    @FXML
    private VBox symptonsPane;

    @FXML
    private VBox resultsPane;

    private Map<String, Boolean> getInputFromPane (VBox inputPane) {
        FilteredList<Node> userInput = inputPane.getChildren().filtered(node -> node instanceof CheckBox);
        return userInput.stream().map(node -> (CheckBox)node).collect(Collectors.toMap(CheckBox::getId, CheckBox::isSelected));
    }

    @FXML
    protected void handleAnalysis(ActionEvent actionEvent) {
        System.out.println(getInputFromPane(illnessesPane));
        System.out.println(getInputFromPane(previousInfoPane));
        System.out.println(getInputFromPane(symptonsPane));

        // Dummy result
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
