public class Fila {
    private Persona[] personas;
    private int cantidad;
    final private int CAPACIDAD_MAXIMA = 30; 

    public Fila() {
        personas = new Persona[CAPACIDAD_MAXIMA];
        cantidad = 0;
    }

    public int recibir(Persona persona) {
        boolean cabe = cantidad < CAPACIDAD_MAXIMA;
        if (cabe) {
            personas[cantidad] = persona;
            cantidad++;
        }
        return cabe ? 1 : 0; 
    }

    public int despachar() {
        assert cantidad > 0 : "Fila vacia no se puede despachar";
        for (int i = 0; i < cantidad - 1; i++) {
            personas[i] = personas[i + 1];
        }
        cantidad--;
        personas[cantidad] = null; 
        return 1; 
    }

    public boolean tieneGente() {
        return cantidad > 0;
    }

    public int obtenerLongitud() {
        return cantidad;
    }

    public void actualizarTiempos() {
        for (int i = 0; i < cantidad; i++) {
            personas[i].esperarMinuto();
        }
    }

    public int purgarAburridos() {
        int aburridos = 0;
        for (int i = cantidad - 1; i >= 0; i--) {
            boolean seVa = personas[i].seAburre();
            aburridos = aburridos + (seVa ? 1 : 0);
            this.eliminarSiEsNecesario(i, seVa);
        }
        return aburridos;
    }

    private void eliminarSiEsNecesario(int indice, boolean borrar) {
        if (borrar) {
            for (int i = indice; i < cantidad - 1; i++) {
                personas[i] = personas[i + 1];
            }
            cantidad--;
            personas[cantidad] = null;
        }
    }
}