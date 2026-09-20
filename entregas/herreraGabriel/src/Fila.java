import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Fila {
    private List<Persona> listaPersonas;

    public Fila() {
        this.listaPersonas = new ArrayList<>();
    }

    public boolean formarPersona(Persona p) {
        if (this.listaPersonas.size() >= 30) {
            if (Math.random() < 0.5) {
                return false;
            }
        }

        if (p.tienePrioridad()) {
            insertarPreferente(p);
        } else {
            this.listaPersonas.add(p);
        }
        return true;
    }

    private void insertarPreferente(Persona p) {
        for (int i = this.listaPersonas.size() - 1; i >= 0; i--) {
            if (this.listaPersonas.get(i).tienePrioridad()) {
                this.listaPersonas.add(i + 1, p);
                return;
            }
        }
        this.listaPersonas.add(0, p);
    }

    public void colarConocido(Persona p) {
        if (this.listaPersonas.size() >= 30 && Math.random() < 0.5) {
            return; 
        }
        if (this.listaPersonas.isEmpty()) {
            this.listaPersonas.add(p);
            return;
        }
        int posicionConocido = (int) (Math.random() * this.listaPersonas.size());
        this.listaPersonas.add(posicionConocido + 1, p);
    }

    public void entregarComprasAConocido() {
        if (this.listaPersonas.size() > 1) {
            int indiceQueSeVa = (int) (Math.random() * this.listaPersonas.size());
            this.listaPersonas.remove(indiceQueSeVa);
        }
    }

    public void actualizarTiempos() {
        for (Persona p : this.listaPersonas) {
            p.esperarUnMinuto();
        }
    }

    public void revisarAburridos() {
        Iterator<Persona> iterador = this.listaPersonas.iterator();
        while (iterador.hasNext()) {
            Persona p = iterador.next();
            if (p.getTiempoEnFila() > 8) {
                if (Math.random() < 0.30) {
                    iterador.remove();
                }
            }
        }
    }

    public Persona salirPersona() {
        if (!estaVacia()) {
            return this.listaPersonas.remove(0);
        }
        return null;
    }

    public boolean estaVacia() {
        return this.listaPersonas.isEmpty();
    }

    public int getLongitud() {
        return this.listaPersonas.size();
    }
}