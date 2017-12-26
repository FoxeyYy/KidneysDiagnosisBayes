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
        EMBARAZO("embarazo"),
        CANCER("cancer"),
        FALTA_APETITO("faltaDeApetito"),
        CANSANCIO("cansancio"),
        PERDIDA_PESO("perdidaDePeso"),
        FALTA_ALIENTO("faltaDeAliento"),
        BIOPSIA_RENAL("biopsiaRenal");

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

        //Previa
        BayesNodeBase ingestaMedicamentosDolor = net.createNode(Nodes.INGESTA_MEDICAMENTOS_DOLOR.toString(), BayesNodeBase.TYPE.DEFAULT);
        ingestaMedicamentosDolor.addOutcomes("true", "false");
        ingestaMedicamentosDolor.setProbabilities(0.029835, 0.970165);

        BayesNodeBase obesidad = net.createNode(Nodes.OBESIDAD.toString(), BayesNodeBase.TYPE.DEFAULT);
        obesidad.addOutcomes("true", "false");
        obesidad.setProbabilities(0.13, 0.87);

        BayesNodeBase productosIndustriales = net.createNode(Nodes.PRODUCTOS_INDUSTRIALES.toString(), BayesNodeBase.TYPE.DEFAULT);
        productosIndustriales.addOutcomes("true", "false");
        productosIndustriales.setProbabilities(0.32, 0.68);

        BayesNodeBase VHL = net.createNode(Nodes.VHL.toString(), BayesNodeBase.TYPE.DEFAULT);
        VHL.addOutcomes("true", "false");
        VHL.setProbabilities(1/3600, 3599/3600);

        BayesNodeBase fumador = net.createNode(Nodes.FUMADOR.toString(), BayesNodeBase.TYPE.DEFAULT);
        fumador.addOutcomes("true", "false");
        fumador.setProbabilities(0.3, 0.7);

        BayesNodeBase antecedentesPiedrasPersonal = net.createNode(Nodes.ANTECEDENTES_PERSONALES_PIEDRAS.toString(), BayesNodeBase.TYPE.DEFAULT);
        antecedentesPiedrasPersonal.addOutcomes("true", "false");
        antecedentesPiedrasPersonal.setProbabilities(0.0507, 0.9493);

        BayesNodeBase antecedentesPiedrasFamilia = net.createNode(Nodes.ANTECEDENTES_FAMILIARES_PIEDRAS.toString(), BayesNodeBase.TYPE.DEFAULT);
        antecedentesPiedrasFamilia.addOutcomes("true", "false");
        antecedentesPiedrasFamilia.setProbabilities(0.03, 0.97);

        BayesNodeBase genero = net.createNode(Nodes.GENERO.toString(), BayesNodeBase.TYPE.DEFAULT);
        genero.addOutcomes("male", "female");
        genero.setProbabilities(0.4908, 0.5092);

        BayesNodeBase enfermedadesCardiov = net.createNode(Nodes.ENFERMEDADES_CARDIOVASCULARES.toString(), BayesNodeBase.TYPE.DEFAULT);
        enfermedadesCardiov.addOutcomes("true", "false");
        enfermedadesCardiov.setProbabilities(0.366, 0.634);

        BayesNodeBase consumoAlcohol = net.createNode(Nodes.CONSUMO_EXCESIVO_ALCOHOL.toString(), BayesNodeBase.TYPE.DEFAULT);
        consumoAlcohol.addOutcomes("true", "false");
        consumoAlcohol.setProbabilities(0.033, 0.967);

        BayesNodeBase diabetes = net.createNode(Nodes.DIABETES.toString(), BayesNodeBase.TYPE.DEFAULT);
        diabetes.addOutcomes("true", "false");
        diabetes.setProbabilities(0.085, 0.915);

        BayesNodeBase edadRiesgo = net.createNode(Nodes.EDAD_RIESGO.toString(), BayesNodeBase.TYPE.DEFAULT);
        edadRiesgo.addOutcomes("true", "false");
        edadRiesgo.setProbabilities(0.151, 0.849);

        BayesNodeBase historialQuistes = net.createNode(Nodes.HISTORIAL_FAMILIAR_QUISTES.toString(), BayesNodeBase.TYPE.DEFAULT);
        historialQuistes.addOutcomes("true", "false");
        historialQuistes.setProbabilities(0.00125, 0.99875);

        BayesNodeBase embarazo = net.createNode(Nodes.EMBARAZO.toString(), BayesNodeBase.TYPE.DEFAULT);
        embarazo.addOutcomes("true", "false");
        embarazo.setParents(Arrays.asList(genero));
        embarazo.setProbabilities(
                0, 1,
                0.25, 0.75);

        //TODO Enfermedades
        //Enfermedades
        BayesNodeBase cancer = net.createNode(Nodes.CANCER.toString(), BayesNodeBase.TYPE.DEFAULT);
        cancer.addOutcomes("false", "true");

        //Sintomas y pruebas
        BayesNodeBase faltaApetito = net.createNode(Nodes.FALTA_APETITO.toString(), BayesNodeBase.TYPE.NOISY_OR);
        faltaApetito.addOutcomes("Negative", "Positive");
        faltaApetito.setParents(Arrays.asList(cancer));
        faltaApetito.setProbabilities(0.467);

        BayesNodeBase cansancio = net.createNode(Nodes.CANSANCIO.toString(), BayesNodeBase.TYPE.NOISY_OR);
        cansancio.addOutcomes("Negative", "Positive");
        cansancio.setParents(Arrays.asList(cancer));
        cansancio.setProbabilities(0.73);

        BayesNodeBase perdidaPeso = net.createNode(Nodes.PERDIDA_PESO.toString(), BayesNodeBase.TYPE.NOISY_OR);
        perdidaPeso.addOutcomes("Negative", "Positive");
        perdidaPeso.setParents(Arrays.asList(cancer));
        perdidaPeso.setProbabilities(0.967);

        BayesNodeBase faltaAliento = net.createNode(Nodes.FALTA_ALIENTO.toString(), BayesNodeBase.TYPE.NOISY_OR);
        faltaAliento.addOutcomes("Negative", "Positive");
        faltaAliento.setParents(Arrays.asList(cancer));
        faltaAliento.setProbabilities(0.964);

        BayesNodeBase biopsiaRenal = net.createNode(Nodes.BIOPSIA_RENAL.toString(), BayesNodeBase.TYPE.DEFAULT);
        biopsiaRenal.addOutcomes("Positive", "Negative");
        biopsiaRenal.setParents(Arrays.asList(cancer));
        biopsiaRenal.setProbabilities(
                0.013, 0.987,
                0.96, 0.04
            );

    }

    public BayesNet getNet() {
        return net;
    }
}
