public class Cola {

    private Persona[] personas;
    private int cantidad;

    public Cola(int maximo) {
        personas = new Persona[maximo];
    }

    public int tamanio() {
        return cantidad;
    }

    public boolean llena() {
        return cantidad == personas.length;
    }

    public boolean vacia() {
        return cantidad == 0;
    }

    public Persona get(int posicion) {
        return personas[posicion];
    }

    public void añadir(Persona persona) {
        if (!llena()) {
            personas[cantidad] = persona;
            cantidad++;
        }
    }

    public Persona sacar() {
        if (vacia()) {
            return null;
        }

        Persona persona = personas[0];

        for (int i = 0; i < cantidad - 1; i++) {
            personas[i] = personas[i + 1];
        }

        cantidad--;
        personas[cantidad] = null;

        return persona;
    }

    public void insertar(int posicion, Persona persona) {
        if (!llena()) {
            for (int i = cantidad; i > posicion; i--) {
                personas[i] = personas[i - 1];
            }

            personas[posicion] = persona;
            cantidad++;
        }
    }

    public void eliminar(int posicion) {
        if (posicion >= 0 && posicion < cantidad) {
            for (int i = posicion; i < cantidad - 1; i++) {
                personas[i] = personas[i + 1];
            }

            cantidad--;
            personas[cantidad] = null;
        }
    }

    public void insertarPreferente(Persona persona) {
        int posicion = -1;

        for (int i = 0; i < cantidad; i++) {
            if (personas[i].esPreferente()
                    && personas[i].getTipo() == persona.getTipo()) {
                posicion = i;
            }
        }

        if (posicion == -1) {
            insertar(0, persona);
        } else {
            insertar(posicion + 1, persona);
        }
    }
}