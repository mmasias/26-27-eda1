package entregas.garciaJacobo;

public class Votacion {
    private String[] opciones;
    private Queue papeletas;
    private int[] recuento;
    private Console console;

    public Votacion(String[] opciones) {
        this.opciones = opciones;
        papeletas = new Queue();
        recuento = new int[opciones.length];
        console = new Console();
    }

    public void recogerPapeleta(String voto) {
        papeletas.insertarAlFinal(voto);
    }

    public String leerPapeleta() {
        assert !papeletas.estaVacia();

        return papeletas.sacarPrimero();
    }

    public void contarVoto(String voto) {
        boolean encontrado = false;

        if (voto.equals("")) {
            recuento[opciones.length - 2]++;
        } else {
            for (int i = 0; i < opciones.length - 2; i++) {
                if (voto.equals(opciones[i])) {
                    recuento[i]++;
                    encontrado = true;
                }
            }

            if (encontrado == false) {
                recuento[opciones.length - 1]++;
            }
        }
    }

    public boolean quedanPapeletas() {
        return !papeletas.estaVacia();
    }

    public void mostrarRecuento() {
        for (int i = 0; i < opciones.length; i++) {
            console.writeln(opciones[i] + " -->" + recuento[i]);
        }
    }

    public int obtenerMaximo() {
        int maximo = recuento[0];

        for (int i = 1; i < recuento.length - 2; i++) {
            if (recuento[i] > maximo) {
                maximo = recuento[i];
            }
        }

        return maximo;
    }

    public int contarGanadores(int maximo) {
        int cantidad = 0;

        for (int i = 0; i < recuento.length - 2; i++) {
            if (recuento[i] == maximo) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public void mostrarResultado() {
        int maximo = obtenerMaximo();
        int cantidad = contarGanadores(maximo);

        if (cantidad == 1) {
            console.writeln("Ganador:");

            for (int i = 0; i < recuento.length - 2; i++) {
                if (recuento[i] == maximo) {
                    console.writeln(opciones[i]);
                }
            }
        } else {
            console.writeln("Empate entre:");

            for (int i = 0; i < recuento.length - 2; i++) {
                if (recuento[i] == maximo) {
                    console.writeln(opciones[i]);
                }
            }
        }
    }
}
