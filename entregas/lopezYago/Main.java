package entregas.lopezYago;

public class Main {
    public static void main(String[] args) {
        int[][] casos = {
            {1, 1, 2, 3, 3, 4},
            {1, 1, 1},
            {1, 2, 2},
            {1, 2, 3},
            {5, 5, 6, 6},
            {}
        };
        String[] esperado = {"2 -> 4", "null", "1", "1 -> 2 -> 3", "null", "null"};

        for (int i = 0; i < casos.length; i++) {
            ListaEnlazada conDummy = new ListaEnlazada();
            ListaEnlazada sinDummy = new ListaEnlazada();
            for (int v : casos[i]) {
                conDummy.agregarAlFinal(v);
                sinDummy.agregarAlFinal(v);
            }
            String entrada = conDummy.toString();
            conDummy.eliminarRepetidos();
            sinDummy.eliminarRepetidosSinDummy();

            boolean ok = conDummy.toString().equals(esperado[i])
                      && sinDummy.toString().equals(esperado[i]);
            System.out.printf("%-22s -> dummy: %-12s sinDummy: %-12s %s%n",
                entrada, conDummy, sinDummy, ok ? "OK" : "FALLO");
        }
    }
}