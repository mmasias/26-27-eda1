public class SimuladorSinCola {

    private static final int MINUTOS_TOTALES = 120;
    private static final double PROBABILIDAD_LLEGADA = 0.6;
    private static final double PROBABILIDAD_ATENCION = 0.4;
    private static final double PROBABILIDAD_PRIORIDAD = 0.2;
    private static final double PROBABILIDAD_COLADA = 0.05;
    private static final int LIMITE_FILA = 30;

    private Cliente primerCliente;
    private Cliente ultimoCliente;
    private int personasAtendidas;
    private int personasAburridas;
    private int personasDesistidas;
    private int personasColadas;
    private String tituloSimulacion;
    private Console console;

    public SimuladorSinCola(String tituloSimulacion) {
        this.tituloSimulacion = tituloSimulacion;
        this.primerCliente = null;
        this.ultimoCliente = null;
        this.personasAtendidas = 0;
        this.personasAburridas = 0;
        this.personasDesistidas = 0;
        this.personasColadas = 0;
        this.console = new Console();
    }

    public void ejecutar() {
        console.writeln("========================================");
        console.writeln(tituloSimulacion);
        console.writeln("========================================");
        for (int minutoActual = 1; minutoActual <= MINUTOS_TOTALES; minutoActual++) {
            evaluarLlegadaCliente(minutoActual);
            evaluarColada(minutoActual);
            evaluarAbandonoPorAburrimiento(minutoActual);
            evaluarAperturaCaja();
            mostrarEstadoMinuto(minutoActual);
        }
        mostrarResultados();
    }

    private boolean hayClientes() {
        return primerCliente != null;
    }

    private void evaluarLlegadaCliente(int minutoActual) {
        if (Math.random() > PROBABILIDAD_LLEGADA) {
            return;
        }

        if (contarClientes() >= LIMITE_FILA && Math.random() <= 0.6) {
            personasDesistidas++;
            return;
        }

        boolean esPrioritario = Math.random() <= PROBABILIDAD_PRIORIDAD;
        Cliente nuevo = new Cliente(esPrioritario, minutoActual);

        if (esPrioritario) {
            encolarPrioritario(nuevo);
        } else {
            encolarNormal(nuevo);
        }
    }

    private void encolarNormal(Cliente nuevo) {
        if (!hayClientes()) {
            primerCliente = nuevo;
            ultimoCliente = nuevo;
        } else {
            nuevo.conectarAdelante(ultimoCliente);
            ultimoCliente = nuevo;
        }
    }

    private void encolarPrioritario(Cliente nuevo) {
        if (!hayClientes()) {
            primerCliente = nuevo;
            ultimoCliente = nuevo;
            return;
        }

        Cliente detrasDePrioritario = null;
        Cliente ultimoPrioritario = null;
        Cliente actual = ultimoCliente;

        while (actual != null) {
            if (actual.tienePrioridad()) {
                ultimoPrioritario = actual;
                break;
            }
            detrasDePrioritario = actual;
            if (actual.tieneAdelante()) {
                actual = actual.obtenerAdelante();
            } else {
                actual = null;
            }
        }

        if (ultimoPrioritario != null) {
            nuevo.conectarAdelante(ultimoPrioritario);
            if (detrasDePrioritario == null) {
                ultimoCliente = nuevo;
            } else {
                detrasDePrioritario.conectarAdelante(nuevo);
            }
        } else {
            primerCliente.conectarAdelante(nuevo);
            primerCliente = nuevo;
        }
    }

    private void evaluarColada(int minutoActual) {
        if (minutoActual < 20 || Math.random() > PROBABILIDAD_COLADA || !hayClientes()) {
            return;
        }

        if (contarClientes() >= LIMITE_FILA && Math.random() <= 0.6) {
            personasDesistidas++;
            return;
        }

        int cantidad = contarClientes();
        int pasos = (int) (Math.random() * cantidad);

        Cliente conocido = ultimoCliente;
        for (int i = 0; i < pasos && conocido.tieneAdelante(); i++) {
            conocido = conocido.obtenerAdelante();
        }

        Cliente colado = new Cliente(false, minutoActual);

        if (conocido.esElMismo(ultimoCliente)) {
            colado.conectarAdelante(ultimoCliente);
            ultimoCliente = colado;
        } else {
            Cliente detras = ultimoCliente;
            while (detras != null && detras.tieneAdelante() && !detras.obtenerAdelante().esElMismo(conocido)) {
                detras = detras.obtenerAdelante();
            }
            if (detras != null) {
                colado.conectarAdelante(conocido);
                detras.conectarAdelante(colado);
            }
        }

        personasColadas++;
        console.writeln(">>> Un conocido se ha colado detras de otra persona en la fila <<<");
    }

    private void evaluarAbandonoPorAburrimiento(int minutoActual) {
        if (minutoActual < 20 || minutoActual % 5 != 0 || !hayClientes()) {
            return;
        }

        Cliente previo = null;
        Cliente actual = ultimoCliente;

        while (actual != null) {
            Cliente siguienteEnlace = actual.tieneAdelante() ? actual.obtenerAdelante() : null;
            if (minutoActual - actual.obtenerMinutoLlegada() > 8 && Math.random() <= 0.30) {
                if (actual.esElMismo(ultimoCliente)) {
                    ultimoCliente = siguienteEnlace;
                } else if (previo != null) {
                    if (siguienteEnlace != null) {
                        previo.conectarAdelante(siguienteEnlace);
                    } else {
                        previo.desconectarAdelante();
                    }
                }

                if (actual.esElMismo(primerCliente)) {
                    primerCliente = previo;
                }

                actual.desconectarAdelante();
                personasAburridas++;
                console.writeln(">>> Un cliente que esperaba mas de 8 minutos se canso y se fue <<<");
            } else {
                previo = actual;
            }
            actual = siguienteEnlace;
        }
    }

    private void evaluarAperturaCaja() {
        if (Math.random() > PROBABILIDAD_ATENCION || !hayClientes()) {
            return;
        }

        if (primerCliente.esElMismo(ultimoCliente)) {
            primerCliente = null;
            ultimoCliente = null;
        } else {
            Cliente actual = ultimoCliente;
            while (actual != null && actual.tieneAdelante() && !actual.obtenerAdelante().esElMismo(primerCliente)) {
                actual = actual.obtenerAdelante();
            }
            if (actual != null) {
                actual.desconectarAdelante();
                primerCliente = actual;
            }
        }
        personasAtendidas++;
    }

    private int contarClientes() {
        int total = 0;
        Cliente actual = ultimoCliente;
        while (actual != null) {
            total++;
            actual = actual.tieneAdelante() ? actual.obtenerAdelante() : null;
        }
        return total;
    }

    private void mostrarEstadoMinuto(int minutoActual) {
        int total = contarClientes();
        console.write("Minuto " + minutoActual + " - Longitud: " + total + " m ");
        mostrarFila(total);
    }

    private void mostrarFila(int total) {
        Cliente[] arreglo = new Cliente[total];
        Cliente actual = ultimoCliente;
        int idx = total - 1;
        while (actual != null && idx >= 0) {
            arreglo[idx] = actual;
            actual = actual.tieneAdelante() ? actual.obtenerAdelante() : null;
            idx--;
        }
        for (int i = 0; i < total; i++) {
            arreglo[i].mostrar();
        }
        console.writeln();
    }

    private void mostrarResultados() {
        console.writeln("----------------------------------------");
        console.writeln("Resultados de " + tituloSimulacion + " (" + MINUTOS_TOTALES + " minutos):");
        console.writeln("Personas atendidas: " + personasAtendidas);
        console.writeln("Personas en fila al cierre: " + contarClientes());
        console.writeln("Personas que se cansaron y se fueron (>8 min): " + personasAburridas);
        console.writeln("Personas que lograron colarse: " + personasColadas);
        console.writeln("Personas que desistieron por ver la fila larga (>=30): " + personasDesistidas);
        console.writeln("========================================\n");
    }
}
