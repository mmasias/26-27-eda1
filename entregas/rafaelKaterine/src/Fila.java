import java.util.Random;
public class Fila {
    private Persona[] personas = new Persona[30];
    private int tamaño = 0;

    public int obtenerTamaño() {
        return tamaño;
    }

    public boolean agregarPersona(Persona persona) {
        if (tamaño >= 30) { 
            return false; 
        }

        if (persona.esPreferencial) {
            int posicion = 0;
            for (int i = 0; i < tamaño; i++) {
                if (personas[i].esPreferencial) {
                    posicion = i + 1;
                }
            }
            insertarEn(posicion, persona); 
        } else {
            personas[tamaño] = persona; 
            tamaño++;
        }
        return true;
    }

    public Persona atender() {
        if (tamaño == 0) {
            throw new IllegalStateException("No hay personas en la fila para atender.");
        }
        return eliminarEn(0); 
    }

    public void revisarAburrimiento(int minutoActual) {
        for (int i = 0; i < tamaño; i++) {
            if (personas[i].seAburre(minutoActual)) { 
                eliminarEn(i); 
                i--;
            }
        }
    }

    public void mostrarEstado() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamaño; i++) {
            sb.append(personas[i].perfil()).append(" ");
        }
        System.out.println("Longitud: " + tamaño + " | " + sb.toString());
    }

    public Persona eliminarEn(int indice) {
        Persona removida = personas[indice];
        for (int i = indice; i < tamaño - 1; i++) {
            personas[i] = personas[i + 1];
        }
        personas[tamaño - 1] = null;
        tamaño--;
        return removida;
    }

    public void insertarEn(int indice, Persona persona) {
        for (int i = tamaño; i > indice; i--) {
            personas[i] = personas[i - 1];
        }
        personas[indice] = persona;
        tamaño++;
    }
}