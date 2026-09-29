import java.util.Random;

public class Simulacion {

    static final double LLEGADA = 0.6;
    static final double CAJA = 0.4;
    static final double PREFERENTE = 0.2;
    static final double COLARSE = 0.1;
    static final double ENTREGAR_COMPRAS = 0.05;

    public static void main(String[] args) {

        Random random = new Random();
        Cola cola = new Cola(30);

        int numero = 0;
        int atendidas = 0;
        int aburridos = 0;
        int[] longitudes = new int[120];

        for (int minuto = 1; minuto <= 120; minuto++) {

            if (random.nextDouble() < LLEGADA && !cola.llena()) {

                numero++;

                boolean preferente = false;
                int tipo = -1;

                if (minuto >= 20
                        && random.nextDouble() < PREFERENTE) {

                    preferente = true;
                    tipo = random.nextInt(3);
                }

                Persona persona =
                        new Persona(numero, minuto, preferente, tipo);

                if (preferente) {
                    cola.insertarPreferente(persona);
                } else {
                    cola.añadir(persona);
                }
            }

            if (random.nextDouble() < CAJA && !cola.vacia()) {
                cola.sacar();
                atendidas++;
            }

            if (minuto >= 20) {

                if (minuto % 5 == 0) {

                    int i = 0;

                    while (i < cola.tamanio()) {

                        Persona persona = cola.get(i);

                        if (minuto - persona.getLlegada() > 8
                                && random.nextDouble() < 0.3) {

                            cola.eliminar(i);
                            aburridos++;
                        } else {
                            i++;
                        }
                    }
                }

                if (!cola.llena()
                        && !cola.vacia()
                        && random.nextDouble() < COLARSE) {

                    numero++;

                    int posicion = random.nextInt(cola.tamanio());

                    Persona colado =
                            new Persona(numero, minuto, false, -1);

                    cola.insertar(posicion + 1, colado);
                }

                if (!cola.vacia()
                        && random.nextDouble() < ENTREGAR_COMPRAS) {

                    int posicion = random.nextInt(cola.tamanio());
                    Persona persona = cola.get(posicion);

                    System.out.println(
                            "La persona " + persona.getNumero()
                            + " recibe unas compras"
                    );
                }
            }

            if (minuto % 15 == 0 && cola.tamanio() > 25) {
                System.out.println(
                        "Pasen por esta caja en orden de fila"
                );
            }

            longitudes[minuto - 1] = cola.tamanio();

            System.out.println(
                    "Minuto " + minuto
                    + ": " + cola.tamanio() + " metros"
            );
        }

        System.out.println();
        System.out.println("Personas atendidas: " + atendidas);
        System.out.println("Personas en fila: " + cola.tamanio());
        System.out.println("Personas aburridas: " + aburridos);
    }
}