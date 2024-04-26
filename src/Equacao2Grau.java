import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

interface CalcularDelta {
    double calcular(double a, double b, double c);
}

interface CalcularBhaskara {
    double[] calcular(double a, double b, double delta);
}

public class Equacao2Grau {

    private CalcularDelta delta = (a, b, c) -> Math.pow(b, 2) - (4 * a * c);;
    private CalcularBhaskara bhaskara = (a, b, valorDelta) -> {
        double[] res = new double[2];
        res[0] = (-b + Math.sqrt(valorDelta)) / (2 * a);
        res[1] = (-b - Math.sqrt(valorDelta)) / (2 * a);

        return res;
    };

    public void main(String[] args) throws Exception {
        List<Double> CoeficientesDesignados = encontrar_valores();
        Map<String, Double> map_resultado = CALCULAR(CoeficientesDesignados);

        System.out.println(map_resultado);
    }

    public static List<Double> encontrar_valores() {
        List<Double> valoresCoeficientes = new ArrayList<>();
        String[] coeficientes = { "a", "b", "c" };

        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = 0; i < coeficientes.length; i++) {
                System.out.println(String.format("Valor do Coeficiente %s:", coeficientes[i]));
                valoresCoeficientes.add(scanner.nextDouble());
            }
        } catch (NumberFormatException e) {
            System.err.println("Erro na entreda do usuário. Utilize uam entrada válida.");
        }
        return valoresCoeficientes;
    }

    public Map<String, Double> CALCULAR(List<Double> listaValores) {
        Map<String, Double> MapResultado = new HashMap<>();

        double a = listaValores.get(0);
        double b = listaValores.get(1);
        double c = listaValores.get(2);

        double DeltaResultado = this.delta.calcular(a, b, c);
        double[] resultadosBhaskara = this.bhaskara.calcular(a, b, DeltaResultado);

        MapResultado.put("delta", DeltaResultado);
        MapResultado.put("raiz1", resultadosBhaskara[0]);
        MapResultado.put("raiz2", resultadosBhaskara[1]);

        return MapResultado;
    }
}
