public class CentroComercial {

    private Fila fila;
    private Tiempo tiempo;

    private Caja caja1;
    private Caja caja2;
    private Caja caja3;
    private Caja caja4;

    private final double PROBABILIDAD_LLEGADA = 0.60;
    private final double PROBABILIDAD_APERTURA_CAJA = 0.40;

    private final double PROBABILIDAD_PREFERENTE = 0.20;
    private final double PROBABILIDAD_COLARSE = 0.15;
    private final double PROBABILIDAD_ENTREGAR_COMPRAS = 0.10;

    public CentroComercial() {

        fila = new Fila();
        tiempo = new Tiempo();

        caja1 = new Caja();
        caja2 = new Caja();
        caja3 = new Caja();
        caja4 = new Caja();
    }

    public void ejecutar() {

        while (!tiempo.haFinalizado()) {

            tiempo.avanzar();

            procesarLlegada();

            procesarAccionesEspeciales();

            abrirCaja();

            atenderClientes();

            mostrarEstado();
        }

        mostrarResumen();
    }

    private void procesarLlegada() {

        if (Math.random() < PROBABILIDAD_LLEGADA) {

            Cliente cliente = crearCliente();

            if (fila.estaLlena()) {

                System.out.println(
                        "La fila está llena. "
                                + "El cliente no entra."
                );

            } else if (cliente.tieneAtencionPreferente()) {

                fila.añadirPreferente(cliente);

            } else {

                fila.añadirCliente(cliente);
            }
        }
    }

    private Cliente crearCliente() {

        boolean preferente =
                Math.random() < PROBABILIDAD_PREFERENTE;

        return new Cliente(
                tiempo.obtenerMinuto(),
                preferente
        );
    }

    private void procesarAccionesEspeciales() {

        if (tiempo.obtenerMinuto() < 20) {
            return;
        }

        if (tiempo.obtenerMinuto() % 5 == 0) {

            fila.comprobarAburrimiento(
                    tiempo.obtenerMinuto()
            );
        }

        if (Math.random() < PROBABILIDAD_COLARSE) {

            if (!fila.estaLlena() && fila.hayGente()) {

                Cliente cliente = new Cliente(
                        tiempo.obtenerMinuto(),
                        false
                );

                fila.colocarDetrasDeConocido(cliente);
            }
        }

        if (Math.random()
                < PROBABILIDAD_ENTREGAR_COMPRAS) {

            fila.entregarCompras();
        }

        if (tiempo.obtenerMinuto() % 15 == 0) {

            if (fila.obtenerNumeroClientes() > 25) {

                System.out.println(
                        "AVISO: pasen por esta caja "
                                + "en orden de fila"
                );
            }
        }
    }

    private void abrirCaja() {

        if (Math.random() < PROBABILIDAD_APERTURA_CAJA) {

            if (!caja1.estaAbierta()) {

                caja1.abrir();

            } else if (!caja2.estaAbierta()) {

                caja2.abrir();

            } else if (!caja3.estaAbierta()) {

                caja3.abrir();

            } else if (!caja4.estaAbierta()) {

                caja4.abrir();
            }
        }
    }

    private void atenderClientes() {

        caja1.atender();
        caja2.atender();
        caja3.atender();
        caja4.atender();

        asignarCliente(caja1);
        asignarCliente(caja2);
        asignarCliente(caja3);
        asignarCliente(caja4);
    }

    private void asignarCliente(Caja caja) {

        if (caja.puedeAtender() && fila.hayGente()) {

            Cliente cliente = fila.sacarPrimero();

            caja.añadirCliente(cliente);
        }
    }

    private void mostrarEstado() {

        System.out.println();
        System.out.println("----------------------------------");
        System.out.println(
                "MINUTO: " + tiempo.obtenerMinuto()
        );
        System.out.println("----------------------------------");

        fila.mostrar();

        System.out.println();

        mostrarCaja(caja1, 1);
        mostrarCaja(caja2, 2);
        mostrarCaja(caja3, 3);
        mostrarCaja(caja4, 4);

        System.out.println(
                "Longitud de la fila: "
                        + fila.obtenerNumeroClientes()
                        + " metros"
        );
    }

    private void mostrarCaja(Caja caja, int numero) {

        System.out.print("Caja " + numero + ": ");

        if (!caja.estaAbierta()) {

            System.out.println("cerrada");

        } else if (caja.estaLibre()) {

            System.out.println("libre");

        } else {

            System.out.println("atendiendo");
        }
    }

    private void mostrarResumen() {

        int clientesAtendidos =
                caja1.obtenerClientesAtendidos()
                + caja2.obtenerClientesAtendidos()
                + caja3.obtenerClientesAtendidos()
                + caja4.obtenerClientesAtendidos();

        System.out.println();
        System.out.println("==================================");
        System.out.println("RESUMEN FINAL");
        System.out.println("==================================");

        System.out.println(
                "Personas atendidas: "
                        + clientesAtendidos
        );

        System.out.println(
                "Personas que quedan en fila: "
                        + fila.obtenerNumeroClientes()
        );
    }
}