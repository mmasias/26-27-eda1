// Fila.java
import java.util.ArrayList;
import java.util.Random;

public class Fila {
    private ArrayList<Persona> personas = new ArrayList<>();
    private static final Random random = new Random();

    public int getTamaño() {
        return personas.size();
    }

    public boolean agregarPersona(Persona persona) {
        if (personas.size() >= 30) {
            return false; 
        }

        if (persona.esPreferencial) {
            int posicion = 0;
            for (int i = 0; i < personas.size(); i++) {
                if (personas.get(i).esPreferencial) {
                    posicion = i + 1;
                }
            }
            personas.add(posicion, persona); 
        } else {
            personas.add(persona); 
        }
        return true;
    }

    public boolean colarse(Persona colado) {
        if (personas.size() >= 30 || personas.isEmpty()) {
            return false;
        }
        int posConocido = random.nextInt(personas.size());
        personas.add(posConocido + 1, colado);
        return true;
    }

    public boolean transferirCompras() {
        if (personas.size() >= 2) {
            int pos = random.nextInt(personas.size());
            personas.remove(pos);
            return true;
        }
        return false;
    }

    public Persona atender() {
        if (!personas.isEmpty()) {
            return personas.remove(0); 
        } 
        return null;
    }

    public void revisarAburrimiento(int minutoActual) {
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).seAburre(minutoActual)) {
                personas.remove(i);
                i--; 
            }
        }
    }

    public void mostrarEstado() {
        StringBuilder sb = new StringBuilder();
        for (Persona p : personas) {
            sb.append(p.perfil()).append(" ");
        }
        System.out.println("Longitud: " + personas.size() + " | " + sb.toString());
    }
}