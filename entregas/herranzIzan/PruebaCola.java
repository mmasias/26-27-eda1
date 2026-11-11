public class PruebaCola {

    public static void main(String[] args) {

        Cola cola = new Cola();

        assert cola.estaVacia();
        System.out.println("Prueba 1 correcta");

        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);

        assert !cola.estaVacia();

        int obtenido1 = cola.desencolar();
        assert obtenido1 == 1 :
            "Se esperaba 1 y se obtuvo " + obtenido1;

        cola.encolar(4);

        int obtenido2 = cola.desencolar();
        assert obtenido2 == 2 :
            "Se esperaba 2 y se obtuvo " + obtenido2;

        int obtenido3 = cola.desencolar();
        assert obtenido3 == 3 :
            "Se esperaba 3 y se obtuvo " + obtenido3;

        System.out.println("Prueba 2 correcta: salen 1, 2 y 3");

        assert !cola.estaVacia();

        int obtenido4 = cola.desencolar();
        assert obtenido4 == 4 :
            "Se esperaba 4 y se obtuvo " + obtenido4;

        assert cola.estaVacia();
        System.out.println("Prueba 3 correcta: queda el 4");

        cola.encolar(-5);
        cola.encolar(0);
        cola.encolar(-5);

        int valor1 = cola.desencolar();
        int valor2 = cola.desencolar();
        int valor3 = cola.desencolar();

        assert valor1 == -5;
        assert valor2 == 0;
        assert valor3 == -5;
        assert cola.estaVacia();

        System.out.println("Prueba 4 correcta");

        boolean errorDetectado = false;

        try {
            cola.desencolar();
        } catch (AssertionError e) {
            errorDetectado = true;
        }

        assert errorDetectado :
            "Debia producirse un AssertionError";

        System.out.println("Prueba 5 correcta");

        System.out.println("TODAS LAS PRUEBAS SUPERADAS");
    }
}

