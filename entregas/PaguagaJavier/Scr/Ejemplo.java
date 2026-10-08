class Ejemplo {
    private static final int[][] ENTRADAS = {
        {1, 1, 2, 3, 3, 4},
        {1, 1, 1},
        {1, 2, 2},
        {1, 2, 3},
        {5, 5, 6, 6},
        {}
    };

    private static final String[] SALIDAS_ESPERADAS = {
        "2 -> 4",
        "null",
        "1",
        "1 -> 2 -> 3",
        "null",
        "null"
    };

    public static void main(String[] args) {
        for (int i = 0; i < ENTRADAS.length; i++) {
            ListaEnlazada entrada = crearLista(ENTRADAS[i]);
            ListaEnlazada conDummy = crearLista(ENTRADAS[i]);
            ListaEnlazada sinDummy = crearLista(ENTRADAS[i]);

            conDummy.eliminarRepetidos();
            sinDummy.eliminarRepetidosSinDummy();

            String resultadoConDummy = conDummy.toString();
            String resultadoSinDummy = sinDummy.toString();
            String esperado = SALIDAS_ESPERADAS[i];
            boolean correcto = esperado.equals(resultadoConDummy)
                    && esperado.equals(resultadoSinDummy);

            System.out.println("Entrada: " + entrada);
            System.out.println("Esperada: " + esperado);
            System.out.println("Con dummy: " + resultadoConDummy);
            System.out.println("Sin dummy: " + resultadoSinDummy);
            System.out.println("Comprobacion: " + (correcto ? "OK" : "ERROR"));
            System.out.println();

            if (!correcto) {
                throw new AssertionError("Resultados incorrectos para: " + entrada);
            }
        }
    }

    private static ListaEnlazada crearLista(int[] valores) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int i = valores.length - 1; i >= 0; i--) {
            lista.insertarAlPrincipio(valores[i]);
        }
        return lista;
    }
}
