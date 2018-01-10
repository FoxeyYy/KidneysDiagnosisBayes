package maerc;

import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.eclipse.recommenders.jayes.BayesNodeBase;

import java.text.DecimalFormat;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {

    @FXML
    private VBox illnessesPane;

    @FXML
    private VBox previousInfoPane;

    @FXML
    private VBox symptonsPane;

    @FXML
    private VBox resultsPane;


    //
    // Toggle groups for previous conditions.
    //

    @FXML
    private ToggleGroup genero;

    @FXML
    private ToggleGroup ingestaMedicamentosDolor;

    @FXML
    private ToggleGroup obesidad;

    @FXML
    private ToggleGroup productosIndustriales;

    @FXML
    private ToggleGroup VHL;

    @FXML
    private ToggleGroup fumador;

    @FXML
    private ToggleGroup antecedentesPersonalesPiedras;

    @FXML
    private ToggleGroup antecedentesFamiliaresPiedras;

    @FXML
    private ToggleGroup enfermedadesCardiovasculares;

    @FXML
    private ToggleGroup consumoExcesivoAlcohol;

    @FXML
    private ToggleGroup diabetes;

    @FXML
    private ToggleGroup edadRiesgo;

    @FXML
    private ToggleGroup historialFamiliarPQRAD;

    @FXML
    private ToggleGroup embarazo;


    //
    // Toggle groups for diseases.
    //

    @FXML
    private ToggleGroup cancer;

    @FXML
    private ToggleGroup piedras;

    @FXML
    private ToggleGroup enfermedadCronicaRenal;

    @FXML
    private ToggleGroup quistesPQRAD;

    @FXML
    private ToggleGroup infeccionesTractoUrinario;


    //
    // Toggle groups for symptons.
    //

    @FXML
    private ToggleGroup faltaDeApetito;

    @FXML
    private ToggleGroup cansancio;

    @FXML
    private ToggleGroup perdidaDePeso;

    @FXML
    private ToggleGroup faltaDeAliento;

    @FXML
    private ToggleGroup biopsiaRenal;

    @FXML
    private ToggleGroup fiebre;

    @FXML
    private ToggleGroup radiografia;

    @FXML
    private ToggleGroup mareosYVomitos;

    @FXML
    private ToggleGroup tomografiaComputerizadaHelicoidal;

    @FXML
    private ToggleGroup anormalidadesMetabolicas;

    @FXML
    private ToggleGroup proteinuria;

    @FXML
    private ToggleGroup anomaliasRenalesTresMeses;

    @FXML
    private ToggleGroup hematuria;

    @FXML
    private ToggleGroup dolor;

    @FXML
    private ToggleGroup aneurismasIntercraneales;

    @FXML
    private ToggleGroup hipertesion;

    @FXML
    private ToggleGroup pruebaEsteresaLeucocitaria;


    private Map<String, Boolean> getInputFromPane (VBox inputPane) {
        FilteredList<Node> userInput = inputPane.getChildren().filtered(node -> node instanceof CheckBox);
        return userInput.stream().map(node -> (CheckBox)node).collect(Collectors.toMap(CheckBox::getId, CheckBox::isSelected));
    }

    private Map<String, String> getInputFromChoiceBoxes (VBox inputPane) {
        FilteredList<Node> userInput = inputPane.getChildren().filtered(node -> node instanceof ChoiceBox);
        return userInput.stream().map(node -> (ChoiceBox<String>)node).collect(Collectors.toMap(ChoiceBox::getId, ChoiceBox::getValue));
    }

    private static Map<String, String> labels =
        Collections.unmodifiableMap(
            Stream.
                of(
                    new SimpleEntry<>(KidneysNetwork.Nodes.CANCER.toString(), "Carcinoma de las células renales"),
                    new SimpleEntry<>(KidneysNetwork.Nodes.PQRAD.toString(), "Quistes en el riñon"),
                    new SimpleEntry<>(KidneysNetwork.Nodes.PIEDRAS.toString(), "Cálculos renales"),
                    new SimpleEntry<>(KidneysNetwork.Nodes.CRONICA_RENAL.toString(), "Enfermedad renal crónica"),
                    new SimpleEntry<>(KidneysNetwork.Nodes.INFECCIONES.toString(), "Pielonefritis")).
                collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)));

    private static Map<KidneysNetwork.Nodes, String> outcomes =
        Collections.unmodifiableMap(
            Stream.
                of(
                    new SimpleEntry<>(KidneysNetwork.Nodes.CANCER, "true"),
                    new SimpleEntry<>(KidneysNetwork.Nodes.PQRAD, "true"),
                    new SimpleEntry<>(KidneysNetwork.Nodes.PIEDRAS, "true"),
                    new SimpleEntry<>(KidneysNetwork.Nodes.CRONICA_RENAL, "true"),
                    new SimpleEntry<>(KidneysNetwork.Nodes.INFECCIONES, "true")).
                collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)));

    /**
     * Retrieves input, both answered and unanswered questions .
     *
     * @param toggleGroups
     * @return Raw user input.
     */
    private Map<String, String> getUserInput(List<ToggleGroup> toggleGroups) {
        return
            toggleGroups.
                stream().
                    map(
                        group ->
                            new SimpleEntry<>(
                                group.getUserData().toString(),
                                ((RadioButton)group.getSelectedToggle()).getText())).
                    collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
    }


    /**
     * Filters out the unanswered questions.
     *
     * @param userInput
     * @return Answered questions.
     */
    private Map<String, String> filterInput(Map<String, String> userInput) {
        return
            userInput.
                entrySet().
                stream().
                filter(entry -> !entry.getValue().equals("N.S. / N.C.")).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    /**
     * Conversions between UI labels and bayesian network outcomes.
     */
    private Map<String, String> convert =
        Map.of(
            "Si", "true",
            "No", "false",
            "Negativo", "negative",
            "Positivo", "positive",
            "Hombre", "male",
            "Mujer", "female");

    /**
     * Converts the answers to the outcomes coded in the bayesian network.
     * @param userInput
     * @return
     */
    private final Map<String, String> convertInput(Map<String, String> userInput) {
        return
            userInput.
                entrySet().
                stream().
                map(entry -> new SimpleEntry<>(entry.getKey(), convert.get(entry.getValue()))).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    /**
     * Returns the evidence base on the outcomes input by the user.
     *
     * @param network
     * @param outcomes
     * @return Evidence base on the user input.
     */
    private Map<BayesNodeBase, String> prepareEvidence(KidneysNetwork network, Map<String, String> outcomes) {
        return
            outcomes.
                entrySet().
                stream().
                map(entry -> new SimpleEntry<>(network.getNet().getNode(entry.getKey()), entry.getValue())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    /**
     * Returns the beliefs generated by a given network for the given outcomes.
     * @param network
     * @param outcomes
     * @return Beliefs generated as the probabilities for the likelihood of developing an illness.
     */
    private Map<String, Double> getBeliefs(KidneysNetwork network, Map<KidneysNetwork.Nodes, String> outcomes) {
        return
            outcomes.
                entrySet().
                stream().
                map(
                    entry ->
                        new SimpleEntry<>(
                            entry.getKey().toString(),
                            network.getInferer().getBeliefs(network.getNet().getNode(entry.getKey().toString()))[1])).
                collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
    }

    /**
     * Sorts the given beliefs by probability in descending order.
     * @param beliefs
     * @return Sorted beliefs.
     */
    private Map<String, Double> sortResults(Map<String, Double> beliefs) {
        return
           beliefs.
                entrySet().
                stream().
                sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    /**
     * Generates the content composing a single result.
     * @param illness
     * @param outcome
     * @return Single result composing a single result.
     */
    private HBox outcomeProbability (String illness, Double outcome) {
        final Label illnessName = new Label();
        illnessName.setText(String.format("%-1.50s", labels.get(illness) + ": "));

        final ProgressBar illnessProgressBar = new ProgressBar();
        illnessProgressBar.setProgress(outcome);

        final Label illnessProbability = new Label();
        illnessProbability.setText(" " + outcome);
        illnessProbability.setText(" " + new DecimalFormat("0.000").format(outcome));

        final HBox illnessBox = new HBox();
        illnessBox.setAlignment(Pos.BOTTOM_RIGHT);

        illnessBox.getChildren().addAll(illnessName, illnessProgressBar, illnessProbability);

        return illnessBox;
    }

    /**
     * Generates the UI elements contained in the results pane.
     * @param results
     * @return Collection of UI elements composing the results pane.
     */
    private HBox[] outcomesProbabilities (Map<String, Double> results) {
        return results.entrySet().stream().map(entry -> outcomeProbability(entry.getKey(), entry.getValue())).toArray(HBox[]::new);
    }

    /**
     * Shows the results on the given pange of the UI.
     *
     * @param resultsPane
     * @param results
     */
    private void outputResults (VBox resultsPane, Map<String, Double> results) {
        resultsPane.getChildren().addAll(outcomesProbabilities(results));
    }


    @FXML
    protected void handleAnalysis(ActionEvent actionEvent) {
        KidneysNetwork network = new KidneysNetwork();

        // Extract user input
        List<ToggleGroup> toggles =
            Arrays.asList(
                genero,
                ingestaMedicamentosDolor,
                obesidad,
                productosIndustriales,
                VHL,
                fumador,
                antecedentesPersonalesPiedras,
                antecedentesFamiliaresPiedras,
                enfermedadesCardiovasculares,
                consumoExcesivoAlcohol,
                diabetes,
                edadRiesgo,
                historialFamiliarPQRAD,
                embarazo,
                cancer,
                piedras,
                enfermedadCronicaRenal,
                quistesPQRAD,
                infeccionesTractoUrinario,
                faltaDeApetito,
                cansancio,
                perdidaDePeso,
                faltaDeAliento,
                biopsiaRenal,
                fiebre,
                radiografia,
                mareosYVomitos,
                tomografiaComputerizadaHelicoidal,
                anormalidadesMetabolicas,
                proteinuria,
                anomaliasRenalesTresMeses,
                hematuria,
                dolor,
                aneurismasIntercraneales,
                hipertesion,
                pruebaEsteresaLeucocitaria);
        Map<String, String> userInput = getUserInput(toggles);

        // Filter out unanswered questions
        Map<String, String> filteredResults = filterInput(userInput);

        // Convert
        Map<String, String> convertedResults = convertInput(filteredResults);

        // Set the evidence
        Map<BayesNodeBase, String> evidence = prepareEvidence(network, convertedResults);
        network.getInferer().setEvidence(evidence);

        // Get each outcome probability
        Map<String, Double> beliefs = getBeliefs(network, outcomes);

        // Sort by probability in descending order
        Map<String, Double> sortedResults = sortResults(beliefs);

        // Display the output on the UI
        resultsPane.getChildren().clear();
        outputResults(resultsPane, sortedResults);
    }
}
