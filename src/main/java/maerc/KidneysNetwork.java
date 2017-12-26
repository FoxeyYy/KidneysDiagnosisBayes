package maerc;

import org.eclipse.recommenders.jayes.*;

import java.util.Arrays;

public class KidneysNetwork {

    public enum Nodes {

        INGESTA_MEDICAMENTOS_DOLOR("ingestaMedicamentosDolor"),
        OBESIDAD("obesidad"),
        PRODUCTOS_INDUSTRIALES("productosIndustriales"),
        VHL("VHL"),
        FUMADOR("fumador"),
        ANTECEDENTES_PERSONALES_PIEDRAS("antecedentesPersonalesPiedras"),
        ANTECEDENTES_FAMILIARES_PIEDRAS("antecedentesFamiliaresPiedras"),
        GENERO("genero"),
        ENFERMEDADES_CARDIOVASCULARES("enfermedadesCardiovasculares"),
        CONSUMO_EXCESIVO_ALCOHOL("consumoExcesivoAlcohol"),
        DIABETES("diabetes"),
        EDAD_RIESGO("edadRiesgo"),
        HISTORIAL_FAMILIAR_QUISTES("historialFamiliarPQRAD"),
        EMBARAZO("embarazo");

        private final String name;

        Nodes(final String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            return name;
        }
    }

    private BayesNet net = new BayesNet();

    public KidneysNetwork () {
        BayesNode ingestaMedicamentosDolor = net.createNode(Nodes.INGESTA_MEDICAMENTOS_DOLOR.toString());
        ingestaMedicamentosDolor.addOutcomes("true", "false");
        ingestaMedicamentosDolor.setProbabilities(0.029835, 0.970165);

        BayesNode obesidad = net.createNode(Nodes.OBESIDAD.toString());
        obesidad.addOutcomes("true", "false");
        obesidad.setProbabilities(0.13, 0.87);

        BayesNode productosIndustriales = net.createNode(Nodes.PRODUCTOS_INDUSTRIALES.toString());
        productosIndustriales.addOutcomes("true", "false");
        productosIndustriales.setProbabilities(0.32, 0.68);

        BayesNode VHL = net.createNode(Nodes.VHL.toString());
        VHL.addOutcomes("true", "false");
        VHL.setProbabilities(1/3600, 3599/3600);

        BayesNode fumador = net.createNode(Nodes.FUMADOR.toString());
        fumador.addOutcomes("true", "false");
        fumador.setProbabilities(0.3, 0.7);

        BayesNode antecedentesPiedrasPersonal = net.createNode(Nodes.ANTECEDENTES_PERSONALES_PIEDRAS.toString());
        antecedentesPiedrasPersonal.addOutcomes("true", "false");
        antecedentesPiedrasPersonal.setProbabilities(0.0507, 0.9493);

        BayesNode antecedentesPiedrasFamilia = net.createNode(Nodes.ANTECEDENTES_FAMILIARES_PIEDRAS.toString());
        antecedentesPiedrasFamilia.addOutcomes("true", "false");
        antecedentesPiedrasFamilia.setProbabilities(0.03, 0.97);

        BayesNode genero = net.createNode(Nodes.GENERO.toString());
        genero.addOutcomes("male", "female");
        genero.setProbabilities(0.4908, 0.5092);

        BayesNode enfermedadesCardiov = net.createNode(Nodes.ENFERMEDADES_CARDIOVASCULARES.toString());
        enfermedadesCardiov.addOutcomes("true", "false");
        enfermedadesCardiov.setProbabilities(0.366, 0.634);

        BayesNode consumoAlcohol = net.createNode(Nodes.CONSUMO_EXCESIVO_ALCOHOL.toString());
        consumoAlcohol.addOutcomes("true", "false");
        consumoAlcohol.setProbabilities(0.033, 0.967);

        BayesNode diabetes = net.createNode(Nodes.DIABETES.toString());
        diabetes.addOutcomes("true", "false");
        diabetes.setProbabilities(0.085, 0.915);

        BayesNode edadRiesgo = net.createNode(Nodes.EDAD_RIESGO.toString());
        edadRiesgo.addOutcomes("true", "false");
        edadRiesgo.setProbabilities(0.151, 0.849);

        BayesNode historialQuistes = net.createNode(Nodes.HISTORIAL_FAMILIAR_QUISTES.toString());
        historialQuistes.addOutcomes("true", "false");
        historialQuistes.setProbabilities(0.00125, 0.99875);

        BayesNode embarazo = net.createNode(Nodes.EMBARAZO.toString());
        embarazo.addOutcomes("true", "false");
        embarazo.setParents(Arrays.asList(genero));
        embarazo.setProbabilities(
                0, 1,
                0.25, 0.75);
    }

    public BayesNet getNet() {
        return net;
    }
}
