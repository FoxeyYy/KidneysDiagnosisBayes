package maerc;

import org.eclipse.recommenders.jayes.BayesNet;
import org.eclipse.recommenders.jayes.BayesNodeBase;
import org.eclipse.recommenders.jayes.BayesNodeNoisyOR;
import org.eclipse.recommenders.jayes.inference.IBayesInferer;
import org.eclipse.recommenders.jayes.inference.junctionTree.JunctionTreeAlgorithm;

import java.util.Arrays;

public class KidneysNetwork {

    public enum Nodes {

        //Previa
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

        //Enfermedades
        CANCER("cancer"),
        PIEDRAS("piedras"),
        CRONICA_RENAL("enfermedadCronicaRenal"),
        PQRAD("quistesPQRAD"),
        INFECCIONES("infeccionesTractoUrinario"),

        //Síntomas y pruebas
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

    /**
     * The bayesian net.
     */
    private BayesNet net = new BayesNet();

    /**
     * The inferer mechanism.
     */
    private IBayesInferer inferer;

    /**
     * Outcomes constants.
     */
    public static final String TRUE = "true",
            FALSE = "false",
            MALE = "male",
            FEMALE = "female",
            NEGATIVE = "negative",
            POSITIVE = "positive";


    public KidneysNetwork () {

        //Previa
        BayesNodeBase ingestaMedicamentosDolor = net.createNode(Nodes.INGESTA_MEDICAMENTOS_DOLOR.toString(), BayesNodeBase.TYPE.DEFAULT);
        ingestaMedicamentosDolor.addOutcomes(FALSE, TRUE);
        ingestaMedicamentosDolor.setProbabilities(0.970165, 0.029835);

        BayesNodeBase obesidad = net.createNode(Nodes.OBESIDAD.toString(), BayesNodeBase.TYPE.DEFAULT);
        obesidad.addOutcomes(FALSE, TRUE);
        obesidad.setProbabilities(0.87, 0.13);

        BayesNodeBase productosIndustriales = net.createNode(Nodes.PRODUCTOS_INDUSTRIALES.toString(), BayesNodeBase.TYPE.DEFAULT);
        productosIndustriales.addOutcomes(FALSE, TRUE);
        productosIndustriales.setProbabilities(0.68, 0.32);

        BayesNodeBase VHL = net.createNode(Nodes.VHL.toString(), BayesNodeBase.TYPE.DEFAULT);
        VHL.addOutcomes(FALSE, TRUE);
        VHL.setProbabilities(3599.0/3600, 1.0/3600);

        BayesNodeBase fumador = net.createNode(Nodes.FUMADOR.toString(), BayesNodeBase.TYPE.DEFAULT);
        fumador.addOutcomes(FALSE, TRUE);
        fumador.setProbabilities(0.7, 0.3);

        BayesNodeBase antecedentesPiedrasPersonal = net.createNode(Nodes.ANTECEDENTES_PERSONALES_PIEDRAS.toString(), BayesNodeBase.TYPE.DEFAULT);
        antecedentesPiedrasPersonal.addOutcomes(FALSE, TRUE);
        antecedentesPiedrasPersonal.setProbabilities(0.9493, 0.0507);

        BayesNodeBase antecedentesPiedrasFamilia = net.createNode(Nodes.ANTECEDENTES_FAMILIARES_PIEDRAS.toString(), BayesNodeBase.TYPE.DEFAULT);
        antecedentesPiedrasFamilia.addOutcomes(FALSE, TRUE);
        antecedentesPiedrasFamilia.setProbabilities(0.97, 0.03);

        BayesNodeBase genero = net.createNode(Nodes.GENERO.toString(), BayesNodeBase.TYPE.DEFAULT);
        genero.addOutcomes(MALE, FEMALE);
        genero.setProbabilities(0.4908, 0.5092);

        BayesNodeBase enfermedadesCardiov = net.createNode(Nodes.ENFERMEDADES_CARDIOVASCULARES.toString(), BayesNodeBase.TYPE.DEFAULT);
        enfermedadesCardiov.addOutcomes(FALSE, TRUE);
        enfermedadesCardiov.setProbabilities(0.634, 0.366);

        BayesNodeBase consumoAlcohol = net.createNode(Nodes.CONSUMO_EXCESIVO_ALCOHOL.toString(), BayesNodeBase.TYPE.DEFAULT);
        consumoAlcohol.addOutcomes(FALSE, TRUE);
        consumoAlcohol.setProbabilities(0.967, 0.033);

        BayesNodeBase diabetes = net.createNode(Nodes.DIABETES.toString(), BayesNodeBase.TYPE.DEFAULT);
        diabetes.addOutcomes(FALSE, TRUE);
        diabetes.setProbabilities(0.9091, 0.0909);

        BayesNodeBase edadRiesgo = net.createNode(Nodes.EDAD_RIESGO.toString(), BayesNodeBase.TYPE.DEFAULT);
        edadRiesgo.addOutcomes(FALSE, TRUE);
        edadRiesgo.setProbabilities(0.849, 0.151);

        BayesNodeBase historialQuistes = net.createNode(Nodes.HISTORIAL_FAMILIAR_QUISTES.toString(), BayesNodeBase.TYPE.DEFAULT);
        historialQuistes.addOutcomes(FALSE, TRUE);
        historialQuistes.setProbabilities(0.99875, 0.00125);

        BayesNodeBase embarazo = net.createNode(Nodes.EMBARAZO.toString(), BayesNodeBase.TYPE.DEFAULT);
        embarazo.addOutcomes(FALSE, TRUE);
        embarazo.setParents(Arrays.asList(genero));
        embarazo.setProbabilities(
                1, 0,
                0.75, 0.25);

        //Enfermedades
        BayesNodeBase cancer = net.createNode(Nodes.CANCER.toString(), BayesNodeBase.TYPE.DEFAULT);
        cancer.addOutcomes(FALSE, TRUE);
        cancer.setParents(Arrays.asList(ingestaMedicamentosDolor, obesidad, productosIndustriales, VHL, fumador, genero));
        //double[] placeholder = new double[128];
        //Arrays.fill(placeholder, .5);
        cancer.setProbabilities(
                0.970, 0.030,
                0.984, 0.016,
                0.940, 0.060,
                0.968, 0.032,
                0.588, 0.412,
                0.5936, 0.4064,
                0.576, 0.424,
                0.5872, 0.4128,
                0.940, 0.060,
                0.968, 0.032,
                0.9364, 0.0636,
                0.967, 0.033,
                0.5633, 0.4367,
                0.5806, 0.4194,
                0.5371, 0.4629,
                0.5672, 0.4328,
                0.940, 0.060,
                0.968, 0.032,
                0.880, 0.120,
                0.936, 0.064,
                0.176, 0.824,
                0.1872, 0.8128,
                0.152, 0.848,
                0.1744, 0.8256,
                0.880, 0.120,
                0.936, 0.064,
                0.8728, 0.1272,
                0.934, 0.066,
                0.1266, 0.8734,
                0.1612, 0.8388,
                0.0742, 0.9258,
                0.1344, 0.8656,
                0.841, 0.159,
                0.8452, 0.1548,
                0.8315, 0.1685,
                0.8403, 0.1597,
                0.5225, 0.4775,
                0.5312, 0.4688,
                0.4897, 0.5103,
                0.5157, 0.4843,
                0.8315, 0.1685,
                0.8403, 0.1597,
                0.8214, 0.1786,
                0.8352, 0.1648,
                0.4939, 0.5061,
                0.5162, 0.4838,
                0.4591, 0.5409,
                0.5003, 0.4997,
                0.682, 0.318,
                0.6904, 0.3096,
                0.6315, 0.3685,
                0.6425, 0.3575,
                0.045, 0.955,
                0.0614, 0.9386,
                0.0172, 0.9828,
                0.0466, 0.9534,
                0.6315, 0.3685,
                0.6425, 0.3575,
                0.6094, 0.3906,
                0.6311, 0.3689,
                0.0172, 0.9828,
                0.0466, 0.9534,
                0.0002, 0.9998);

        BayesNodeBase quistes = net.createNode(Nodes.PQRAD.toString(), BayesNodeBase.TYPE.DEFAULT);
        quistes.addOutcomes(FALSE, TRUE);
        quistes.setParents(Arrays.asList(historialQuistes, edadRiesgo, fumador, genero));
        //placeholder = new double[32];
        //Arrays.fill(placeholder, .5);
        quistes.setProbabilities(
                0.9999922, 0.0000078,
                0.9999931, 0.0000069,
                0.99999025, 0.00000975,
                0.999991375, 0.000008625,
                0.99999025, 0.00000975,
                0.999991375, 0.000008625,
                0.9999878125, 0.0000121875,
                0.99998921875, 0.00001078125,
                0.4999961, 0.5000039,
                0.49999655, 0.50000345,
                0.374991374970244, 0.625008625029756,
                0.374995125, 0.625004875,
                0.374991374970244, 0.625008625029756,
                0.374995125, 0.625004875,
                0.218739218712805, 0.781260781287195,
                0.21874390625, 0.78125609375);

        BayesNodeBase piedras = net.createNode(Nodes.PIEDRAS.toString(), BayesNodeBase.TYPE.DEFAULT);
        piedras.addOutcomes(FALSE, TRUE);
        piedras.setParents(Arrays.asList(quistes, antecedentesPiedrasPersonal, antecedentesPiedrasFamilia, fumador, genero));
        //placeholder = new double[64];
        //Arrays.fill(placeholder, .5);
        piedras.setProbabilities(
                0.88, 0.12,
                0.9, 0.1,
                0.7993, 0.2007,
                0.8327, 0.1673,
                0.856, 0.144,
                0.88, 0.12,
                0.7591, 0.2409,
                0.7993, 0.2007,
                0.82, 0.18,
                0.85, 0.15,
                0.6989, 0.3011,
                0.7491, 0.2509,
                0.784, 0.216,
                0.82, 0.18,
                0.6386, 0.3614,
                0.6989, 0.3011,
                0.75, 0.25,
                0.79, 0.21,
                0.5817, 0.4183,
                0.6487, 0.3513,
                0.7, 0.3,
                0.748, 0.252,
                0.4981, 0.5019,
                0.5785, 0.4215,
                0.625, 0.375,
                0.685, 0.315,
                0.3726, 0.6274,
                0.473, 0.527,
                0.55, 0.45,
                0.622, 0.378,
                0.2471, 0.7529,
                0.3676, 0.6324);

        BayesNodeBase cronica = net.createNode(Nodes.CRONICA_RENAL.toString(), BayesNodeBase.TYPE.DEFAULT);
        cronica.addOutcomes(FALSE, TRUE);
        cronica.setParents(Arrays.asList(enfermedadesCardiov, diabetes, consumoAlcohol, fumador, genero));
        //placeholder = new double[64];
        //Arrays.fill(placeholder, .5);
        cronica.setProbabilities(
                0.879, 0.121,
                0.849, 0.151,
                0.8482, 0.1518,
                0.8105, 0.1895,
                0.758, 0.242,
                0.698, 0.302,
                0.395, 0.605,
                0.245, 0.755,
                0.8679, 0.1321,
                0.8352, 0.1648,
                0.8343, 0.1657,
                0.7931, 0.2069,
                0.7358, 0.2642,
                0.6703, 0.3297,
                0.3394, 0.6606,
                0.1756, 0.8244,
                0.8185, 0.1815,
                0.7735, 0.2265,
                0.7723, 0.2277,
                0.7158, 0.2842,
                0.637, 0.363,
                0.547, 0.453,
                0.0925, 0.9075,
                0.01, 0.99,
                0.8019, 0.1981,
                0.7527, 0.2473,
                0.7515, 0.2485,
                0.6897, 0.3103,
                0.6037, 0.3963,
                0.5055, 0.4945,
                0.0091, 0.9909,
                0.0001, 0.9999);


        BayesNodeBase infecciones = net.createNode(Nodes.INFECCIONES.toString(), BayesNodeBase.TYPE.DEFAULT);
        infecciones.addOutcomes(FALSE, TRUE);
        infecciones.setParents(Arrays.asList(diabetes, embarazo, genero));
        //placeholder = new double[16];
        //Arrays.fill(placeholder, .5);
        infecciones.setProbabilities(
                0.83, 0.17,
                0.63, 0.37,
                0.83, 0.17,
                0.26, 0.74,
                0.8, 0.2,
                0.075, 0.925,
                0.8, 0.2,
                0.001, 0.999);

        //Sintomas y pruebas
        BayesNodeBase faltaApetito = net.createNode(Nodes.FALTA_APETITO.toString(), BayesNodeBase.TYPE.NOISY_OR);
        faltaApetito.addOutcomes(NEGATIVE, POSITIVE);
        faltaApetito.setParents(Arrays.asList(cancer));
        ((BayesNodeNoisyOR) faltaApetito).setLeak(0.2);
        faltaApetito.setProbabilities(0.467);

        BayesNodeBase cansancio = net.createNode(Nodes.CANSANCIO.toString(), BayesNodeBase.TYPE.NOISY_OR);
        cansancio.addOutcomes(NEGATIVE, POSITIVE);
        cansancio.setParents(Arrays.asList(cancer));
        ((BayesNodeNoisyOR) cansancio).setLeak(0.35);
        cansancio.setProbabilities(0.73);

        BayesNodeBase perdidaPeso = net.createNode(Nodes.PERDIDA_PESO.toString(), BayesNodeBase.TYPE.NOISY_OR);
        perdidaPeso.addOutcomes(NEGATIVE, POSITIVE);
        perdidaPeso.setParents(Arrays.asList(cancer));
        ((BayesNodeNoisyOR) perdidaPeso).setLeak(0.3);
        perdidaPeso.setProbabilities(0.967);

        BayesNodeBase faltaAliento = net.createNode(Nodes.FALTA_ALIENTO.toString(), BayesNodeBase.TYPE.NOISY_OR);
        faltaAliento.addOutcomes(NEGATIVE, POSITIVE);
        faltaAliento.setParents(Arrays.asList(cancer));
        ((BayesNodeNoisyOR) faltaAliento).setLeak(0.15);
        faltaAliento.setProbabilities(0.964);

        BayesNodeBase biopsiaRenal = net.createNode(Nodes.BIOPSIA_RENAL.toString(), BayesNodeBase.TYPE.DEFAULT);
        biopsiaRenal.addOutcomes(POSITIVE, NEGATIVE);
        biopsiaRenal.setParents(Arrays.asList(cancer));
        biopsiaRenal.setProbabilities(
                0.013, 0.987,
                0.96, 0.04
            );

        BayesNodeBase fiebre = net.createNode(Nodes.FIEBRE.toString(), BayesNodeBase.TYPE.NOISY_OR);
        fiebre.addOutcomes(NEGATIVE, POSITIVE);
        fiebre.setParents(Arrays.asList(cancer, quistes, infecciones));
        ((BayesNodeNoisyOR) fiebre).setLeak(0.15);
        fiebre.setProbabilities(0.967, 0.32, 0.07);

        BayesNodeBase radiografia = net.createNode(Nodes.RADIOGRAFIA.toString(), BayesNodeBase.TYPE.DEFAULT);
        radiografia.addOutcomes(POSITIVE, NEGATIVE);
        radiografia.setParents(Arrays.asList(piedras));
        radiografia.setProbabilities(
                0.26, 0.74,
                0.52, 0.48
        );

        BayesNodeBase mareosVomitos = net.createNode(Nodes.MAREOS_VOMITOS.toString(), BayesNodeBase.TYPE.NOISY_OR);
        mareosVomitos.addOutcomes(NEGATIVE, POSITIVE);
        mareosVomitos.setParents(Arrays.asList(piedras));
        ((BayesNodeNoisyOR) mareosVomitos).setLeak(0.15);
        mareosVomitos.setProbabilities(0.55);

        BayesNodeBase tomografia = net.createNode(Nodes.TOMOGRAFIA_HELICOIDAL.toString(), BayesNodeBase.TYPE.DEFAULT);
        tomografia.addOutcomes(POSITIVE, NEGATIVE);
        tomografia.setParents(Arrays.asList(piedras));
        tomografia.setProbabilities(
                0.05, 0.95,
                0.975, 0.025
        );

        BayesNodeBase anormalidadesMetabolicas = net.createNode(Nodes.ANORMALIDADES_METABOLICAS.toString(), BayesNodeBase.TYPE.NOISY_OR);
        anormalidadesMetabolicas.addOutcomes(NEGATIVE, POSITIVE);
        anormalidadesMetabolicas.setParents(Arrays.asList(piedras));
        ((BayesNodeNoisyOR) anormalidadesMetabolicas).setLeak(.03);
        anormalidadesMetabolicas.setProbabilities(0.48);

        BayesNodeBase proteinuria = net.createNode(Nodes.PROTEINURIA.toString(), BayesNodeBase.TYPE.DEFAULT);
        proteinuria.addOutcomes(POSITIVE, NEGATIVE);
        proteinuria.setParents(Arrays.asList(piedras));
        proteinuria.setProbabilities(
                0.13, 0.87,
                0.96, 0.04
        );

        BayesNodeBase anomaliasMeses = net.createNode(Nodes.ANOMALIAS_RENALES_MESES.toString(), BayesNodeBase.TYPE.DEFAULT);
        anomaliasMeses.addOutcomes(POSITIVE, NEGATIVE);
        anomaliasMeses.setParents(Arrays.asList(piedras));
        anomaliasMeses.setProbabilities(
                0, 1,
                1, 0
        );

        BayesNodeBase hematuria = net.createNode(Nodes.HEMATURIA.toString(), BayesNodeBase.TYPE.NOISY_OR);
        hematuria.addOutcomes(NEGATIVE, POSITIVE);
        hematuria.setParents(Arrays.asList(cancer, piedras, cronica, quistes, infecciones));
        ((BayesNodeNoisyOR) hematuria).setLeak(0.02);
        hematuria.setProbabilities(0.934, 0.9, 0.025, 0.6, 0.4);

        BayesNodeBase dolor = net.createNode(Nodes.DOLOR.toString(), BayesNodeBase.TYPE.NOISY_OR);
        dolor.addOutcomes(NEGATIVE, POSITIVE);
        dolor.setParents(Arrays.asList(cancer, piedras, quistes));
        ((BayesNodeNoisyOR) dolor).setLeak(0.2);
        dolor.setProbabilities(0.968, 0.56, 0.175);

        BayesNodeBase aneurismas = net.createNode(Nodes.ANEURISMAS_INTERCRANEALES.toString(), BayesNodeBase.TYPE.NOISY_OR);
        aneurismas.addOutcomes(NEGATIVE, POSITIVE);
        aneurismas.setParents(Arrays.asList(quistes));
        ((BayesNodeNoisyOR) aneurismas).setLeak(0.04);
        aneurismas.setProbabilities(0.8);

        BayesNodeBase hipertesion = net.createNode(Nodes.HIPERTENSION.toString(), BayesNodeBase.TYPE.NOISY_OR);
        hipertesion.addOutcomes(NEGATIVE, POSITIVE);
        hipertesion.setParents(Arrays.asList(quistes));
        ((BayesNodeNoisyOR) hipertesion).setLeak(0.3);
        hipertesion.setProbabilities(0.6);

        BayesNodeBase esteresa = net.createNode(Nodes.ESTERESA_LEUCOCITARIA.toString(), BayesNodeBase.TYPE.DEFAULT);
        esteresa.addOutcomes(POSITIVE, NEGATIVE);
        esteresa.setParents(Arrays.asList(infecciones));
        esteresa.setProbabilities(
                0.06, .94,
                0.75, 0.25
        );

        inferer = new JunctionTreeAlgorithm();
        inferer.setNetwork(net);

    }

    public BayesNet getNet() {
        return net;
    }

    public double getProbabilityFor(Nodes nodeName, String outcome) {
        BayesNodeBase node = net.getNode(nodeName.toString());
        return inferer.getBeliefs(node)[node.getOutcomeIndex(outcome)];
    }
}
