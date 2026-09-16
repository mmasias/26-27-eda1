public class LaFila {

    public static void main(String[] args) {

        Fila fila = new Fila();

        int atendidas = 0;
        int siguientePersona = 1;

        for (int minuto = 1; minuto <= 120; minuto++) {

            System.out.println("----- MINUTO " + minuto + " -----");

            // ------------------------------------------------
            // LLEGADA NORMAL
            // Probabilidad 0.6
            // ------------------------------------------------

            double llegada = Math.random();

            if (llegada < 0.6) {

                boolean preferente = Math.random() < 0.20;

                Persona persona = new Persona(
                        siguientePersona,
                        preferente,
                        minuto
                );

                if (minuto < 20) {

                    if (fila.entrar(persona)) {
                        System.out.println("Ha llegado " + persona);
                        siguientePersona++;
                    } else {
                        System.out.println("La fila está llena. No entra nadie.");
                    }

                } else {

                    if (preferente) {

                        if (fila.entrarPreferente(persona)) {
                            System.out.println("Ha llegado " + persona);
                            siguientePersona++;
                        } else {
                            System.out.println("La fila está llena. No entra.");
                        }

                    } else {

                        if (fila.entrar(persona)) {
                            System.out.println("Ha llegado " + persona);
                            siguientePersona++;
                        } else {
                            System.out.println("La fila está llena. No entra.");
                        }
                    }
                }
            }

            // ------------------------------------------------
            // APERTURA DE CAJA
            // Probabilidad 0.4
            // ------------------------------------------------

            double caja = Math.random();

            if (caja < 0.4) {

                Persona atendida = fila.atender();

                if (atendida != null) {

                    atendidas++;

                    System.out.println(
                            "Atendida: " + atendida
                    );
                }
            }

            // ------------------------------------------------
            // ABANDONO POR ABURRIMIENTO
            // Desde el minuto 20
            // Cada 5 minutos
            // ------------------------------------------------

            if (minuto >= 20 && minuto % 5 == 0) {

                for (int i = fila.tamanio() - 1; i >= 0; i--) {

                    Persona persona = fila.obtener(i);

                    if (minuto - persona.getMinutoLlegada() > 8) {

                        double aburrimiento = Math.random();

                        if (aburrimiento < 0.30) {

                            fila.abandonar(i);

                            System.out.println(
                                    persona + " se ha ido por aburrimiento."
                            );
                        }
                    }
                }
            }

            // ------------------------------------------------
            // AVISO CADA 15 MINUTOS
            // ------------------------------------------------

            if (minuto % 15 == 0 && fila.tamanio() > 25) {

                System.out.println(
                        "AVISO: pasen por esta caja en orden de fila"
                );
            }

            // ------------------------------------------------
            // MOSTRAR LONGITUD
            // ------------------------------------------------

            System.out.println(
                    "Personas en fila: " + fila.tamanio()
            );

            System.out.println(
                    "Longitud: " + fila.tamanio() + " metros"
            );

            System.out.println();
        }

        // ------------------------------------------------
        // RESULTADO FINAL
        // ------------------------------------------------

        System.out.println("========== RESULTADO ==========");

        System.out.println(
                "Personas atendidas: " + atendidas
        );

        System.out.println(
                "Personas que quedan en fila: " + fila.tamanio()
        );

        System.out.println(
                "Longitud final de la fila: "
                        + fila.tamanio()
                        + " metros"
        );
    }
}
