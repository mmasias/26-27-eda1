package listas.nodoDummy;

public class Ejemplo {
    static int fallos = 0;

    public static void main(String[] args) {
        System.out.println("== Reto base: eliminarRepetidos");
        probarEliminarRepetidos(new int[] { 1, 1, 2, 3, 3, 4 }, new int[] { 2, 4 });
        probarEliminarRepetidos(new int[] { 1, 1, 1 }, new int[] {});
        probarEliminarRepetidos(new int[] { 1, 2, 2 }, new int[] { 1 });
        probarEliminarRepetidos(new int[] { 1, 2, 3 }, new int[] { 1, 2, 3 });
        probarEliminarRepetidos(new int[] { 5, 5, 6, 6 }, new int[] {});
        probarEliminarRepetidos(new int[] {}, new int[] {});

        System.out.println("== Reto extendido: fusionar");
        probarFusionar(new int[] { 1, 4, 7 }, new int[] { 2, 3, 8, 9 }, new int[] { 1, 2, 3, 4, 7, 8, 9 });
        probarFusionar(new int[] {}, new int[] { 2, 3 }, new int[] { 2, 3 });
        probarFusionar(new int[] {}, new int[] {}, new int[] {});
        probarFusionar(new int[] { 1, 1 }, new int[] { 1 }, new int[] { 1, 1, 1 });

        System.out.println(fallos == 0 ? "Todos los casos OK" : "Casos con fallo: " + fallos);
    }

    static void probarEliminarRepetidos(int[] datos, int[] esperado) {
        ListaEnlazada conDummy = crear(datos);
        ListaEnlazada sinDummy = crear(datos);

        System.out.println("entrada:   " + conDummy.comoTexto());

        conDummy.eliminarRepetidos();
        sinDummy.eliminarRepetidosSinDummy();

        String textoEsperado = crear(esperado).comoTexto();
        boolean ok = conDummy.comoTexto().equals(textoEsperado)
                && sinDummy.comoTexto().equals(textoEsperado);

        System.out.println("con dummy: " + conDummy.comoTexto());
        System.out.println("sin dummy: " + sinDummy.comoTexto());
        System.out.println("esperada:  " + textoEsperado + "  " + (ok ? "OK" : "FALLO"));
        System.out.println();
        if (!ok) {
            fallos++;
        }
    }

    static void probarFusionar(int[] datosA, int[] datosB, int[] esperado) {
        ListaEnlazada a = crear(datosA);
        ListaEnlazada b = crear(datosB);

        System.out.println("a:         " + a.comoTexto());
        System.out.println("b:         " + b.comoTexto());

        ListaEnlazada resultado = ListaEnlazada.fusionar(a, b);

        String textoEsperado = crear(esperado).comoTexto();
        boolean ok = resultado.comoTexto().equals(textoEsperado) && a.estaVacia() && b.estaVacia();

        System.out.println("resultado: " + resultado.comoTexto());
        System.out.println("a final:   " + a.comoTexto());
        System.out.println("b final:   " + b.comoTexto());
        System.out.println("esperado:  " + textoEsperado + "  " + (ok ? "OK" : "FALLO"));
        System.out.println();
        if (!ok) {
            fallos++;
        }
    }

    static ListaEnlazada crear(int[] datos) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int i = 0; i < datos.length; i++) {
            lista.insertarEnPosicion(i, datos[i]);
        }
        return lista;
    }
}