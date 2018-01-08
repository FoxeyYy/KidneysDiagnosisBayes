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
        PIEDRAS("piedras"),
        CRONICA_RENAL("enfermedadCronicaRenal"),
        PQRAD("quistesPQRAD"),
        INFECCIONES("infeccionesTractoUrinario"),
        FALTA_APETITO("faltaDeApetito"),
        CANSANCIO("cansancio"),
        PERDIDA_PESO("perdidaDePeso"),
        FALTA_ALIENTO("faltaDeAliento"),
        BIOPSIA_RENAL("biopsiaRenal"),
        FIEBRE("fiebre"),
        RADIOGRAFIA("radiografia"),
        MAREOS_VOMITOS("mareosYVomitos"),
        TOMOGRAFIA_HELICOIDAL("tomografiaComputerizadaHelicoidal"),
        ANORMALIDADES_METABOLICAS("anormalidadesMetabolicas"),
        PROTEINURIA("proteinuria"),
        ANOMALIAS_RENALES_MESES("anomaliasRenalesTresMeses"),
        HEMATURIA("hematuria"),
        DOLOR("dolor"),
        ANEURISMAS_INTERCRANEALES("aneurismasIntercraneales"),
        HIPERTENSION("hipertesion"),
        ESTERESA_LEUCOCITARIA("pruebaEsteresaLeucocitaria");

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
        cancer.setParents(Arrays.asList(ingestaMedicamentosDolor, obesidad, productosIndustriales, VHL, fumador, genero));
        double[] placeholder = new double[128];
        Arrays.fill(placeholder, .5);
        cancer.setProbabilities(placeholder);

        BayesNodeBase quistes = net.createNode(Nodes.PQRAD.toString(), BayesNodeBase.TYPE.DEFAULT);
        quistes.addOutcomes("false", "true");
        quistes.setParents(Arrays.asList(fumador, genero, edadRiesgo, historialQuistes));
        placeholder = new double[32];
        Arrays.fill(placeholder, .5);
        quistes.setProbabilities(placeholder);

        BayesNodeBase piedras = net.createNode(Nodes.PIEDRAS.toString(), BayesNodeBase.TYPE.DEFAULT);
        piedras.addOutcomes("false", "true");
        piedras.setParents(Arrays.asList(fumador, antecedentesPiedrasPersonal, antecedentesPiedrasFamilia, genero, quistes));
        placeholder = new double[64];
        Arrays.fill(placeholder, .5);
        piedras.setProbabilities(placeholder);

        BayesNodeBase cronica = net.createNode(Nodes.CRONICA_RENAL.toString(), BayesNodeBase.TYPE.DEFAULT);
        cronica.addOutcomes("false", "true");
        cronica.setParents(Arrays.asList(fumador, genero, enfermedadesCardiov, consumoAlcohol, diabetes));
        placeholder = new double[64];
        Arrays.fill(placeholder, .5);
        cronica.setProbabilities(placeholder);


        BayesNodeBase infecciones = net.createNode(Nodes.INFECCIONES.toString(), BayesNodeBase.TYPE.DEFAULT);
        infecciones.addOutcomes("false", "true");
        infecciones.setParents(Arrays.asList(genero, diabetes, embarazo));
        placeholder = new double[16];
        Arrays.fill(placeholder, .5);
        infecciones.setProbabilities(placeholder);

        //Sintomas y pruebas
        BayesNodeBase faltaApetito = net.createNode(Nodes.FALTA_APETITO.toString(), BayesNodeBase.TYPE.NOISY_OR);
        faltaApetito.addOutcomes("Negative", "Positive");
        faltaApetito.setParents(Arrays.asList(cancer));
        ((BayesNodeNoisyOR) faltaApetito).setLeak(0.2);
        faltaApetito.setProbabilities(0.467);

        BayesNodeBase cansancio = net.createNode(Nodes.CANSANCIO.toString(), BayesNodeBase.TYPE.NOISY_OR);
        cansancio.addOutcomes("Negative", "Positive");
        cansancio.setParents(Arrays.asList(cancer));
        ((BayesNodeNoisyOR) cansancio).setLeak(0.35);
        cansancio.setProbabilities(0.73);

        BayesNodeBase perdidaPeso = net.createNode(Nodes.PERDIDA_PESO.toString(), BayesNodeBase.TYPE.NOISY_OR);
        perdidaPeso.addOutcomes("Negative", "Positive");
        perdidaPeso.setParents(Arrays.asList(cancer));
        ((BayesNodeNoisyOR) perdidaPeso).setLeak(0.3);
        perdidaPeso.setProbabilities(0.967);

        BayesNodeBase faltaAliento = net.createNode(Nodes.FALTA_ALIENTO.toString(), BayesNodeBase.TYPE.NOISY_OR);
        faltaAliento.addOutcomes("Negative", "Positive");
        faltaAliento.setParents(Arrays.asList(cancer));
        ((BayesNodeNoisyOR) faltaAliento).setLeak(0.15);
        faltaAliento.setProbabilities(0.964);

        BayesNodeBase biopsiaRenal = net.createNode(Nodes.BIOPSIA_RENAL.toString(), BayesNodeBase.TYPE.DEFAULT);
        biopsiaRenal.addOutcomes("Positive", "Negative");
        biopsiaRenal.setParents(Arrays.asList(cancer));
        biopsiaRenal.setProbabilities(
                0.013, 0.987,
                0.96, 0.04
            );

        BayesNodeBase fiebre = net.createNode(Nodes.FIEBRE.toString(), BayesNodeBase.TYPE.NOISY_OR);
        fiebre.addOutcomes("Negative", "Positive");
        fiebre.setParents(Arrays.asList(cancer, quistes, infecciones));
        ((BayesNodeNoisyOR) fiebre).setLeak(0.15);
        fiebre.setProbabilities(0.967, 0.32, 0.07);

        BayesNodeBase radiografia = net.createNode(Nodes.RADIOGRAFIA.toString(), BayesNodeBase.TYPE.DEFAULT);
        radiografia.addOutcomes("Positive", "Negative");
        radiografia.setParents(Arrays.asList(piedras));
        radiografia.setProbabilities(
                0.26, 0.74,
                0.52, 0.48
        );

        BayesNodeBase mareosVomitos = net.createNode(Nodes.MAREOS_VOMITOS.toString(), BayesNodeBase.TYPE.NOISY_OR);
        mareosVomitos.addOutcomes("Negative", "Positive");
        mareosVomitos.setParents(Arrays.asList(piedras));
        ((BayesNodeNoisyOR) mareosVomitos).setLeak(0.15);
        mareosVomitos.setProbabilities(0.55);

        BayesNodeBase tomografia = net.createNode(Nodes.TOMOGRAFIA_HELICOIDAL.toString(), BayesNodeBase.TYPE.DEFAULT);
        tomografia.addOutcomes("Positive", "Negative");
        tomografia.setParents(Arrays.asList(piedras));
        tomografia.setProbabilities(
                0.05, 0.95,
                0.975, 0.025
        );

        BayesNodeBase anormalidadesMetabolicas = net.createNode(Nodes.ANORMALIDADES_METABOLICAS.toString(), BayesNodeBase.TYPE.NOISY_OR);
        anormalidadesMetabolicas.addOutcomes("Negative", "Positive");
        anormalidadesMetabolicas.setParents(Arrays.asList(piedras));
        ((BayesNodeNoisyOR) anormalidadesMetabolicas).setLeak(.03);
        anormalidadesMetabolicas.setProbabilities(0.48);

        BayesNodeBase proteinuria = net.createNode(Nodes.PROTEINURIA.toString(), BayesNodeBase.TYPE.DEFAULT);
        proteinuria.addOutcomes("Positive", "Negative");
        proteinuria.setParents(Arrays.asList(piedras));
        proteinuria.setProbabilities(
                0.13, 0.87,
                0.96, 0.04
        );

        BayesNodeBase anomaliasMeses = net.createNode(Nodes.ANOMALIAS_RENALES_MESES.toString(), BayesNodeBase.TYPE.DEFAULT);
        anomaliasMeses.addOutcomes("Positive", "Negative");
        anomaliasMeses.setParents(Arrays.asList(piedras));
        anomaliasMeses.setProbabilities(
                0, 1,
                1, 0
        );

        BayesNodeBase hematuria = net.createNode(Nodes.HEMATURIA.toString(), BayesNodeBase.TYPE.NOISY_OR);
        hematuria.addOutcomes("Negative", "Positive");
        hematuria.setParents(Arrays.asList(cancer, piedras, cronica, quistes, infecciones));
        ((BayesNodeNoisyOR) hematuria).setLeak(0.02);
        hematuria.setProbabilities(0.934, 0.9, 0.025, 0.6, 0.4);

        BayesNodeBase dolor = net.createNode(Nodes.DOLOR.toString(), BayesNodeBase.TYPE.NOISY_OR);
        dolor.addOutcomes("Negative", "Positive");
        dolor.setParents(Arrays.asList(cancer, piedras, quistes));
        ((BayesNodeNoisyOR) dolor).setLeak(0.2);
        dolor.setProbabilities(0.968, 0.56, 0.175);

        BayesNodeBase aneurismas = net.createNode(Nodes.ANEURISMAS_INTERCRANEALES.toString(), BayesNodeBase.TYPE.NOISY_OR);
        aneurismas.addOutcomes("Negative", "Positive");
        aneurismas.setParents(Arrays.asList(quistes));
        ((BayesNodeNoisyOR) aneurismas).setLeak(0.04);
        aneurismas.setProbabilities(0.8);

        BayesNodeBase hipertesion = net.createNode(Nodes.HIPERTENSION.toString(), BayesNodeBase.TYPE.NOISY_OR);
        hipertesion.addOutcomes("Negative", "Positive");
        hipertesion.setParents(Arrays.asList(quistes));
        ((BayesNodeNoisyOR) hipertesion).setLeak(0.3);
        hipertesion.setProbabilities(0.6);

        BayesNodeBase esteresa = net.createNode(Nodes.ESTERESA_LEUCOCITARIA.toString(), BayesNodeBase.TYPE.DEFAULT);
        esteresa.addOutcomes("Positive", "Negative");
        esteresa.setParents(Arrays.asList(infecciones));
        esteresa.setProbabilities(
                0.06, .94,
                0.75, 0.25
        );

    }

    public BayesNet getNet() {
        return net;
    }
}
