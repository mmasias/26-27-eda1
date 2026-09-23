public class Cola {
    private Persona[] elementos;
    private int tamano;

    public Cola(int capacidadInicial) {
        this.elementos = new Persona[capacidadInicial];
        this.tamano = 0;
    }

    public int getTamano() {
        return tamano;
    }

    private void redimensionar() {
        Persona[] nuevo = new Persona[elementos.length * 2];
        for (int i = 0; i < tamano; i++) {
            nuevo[i] = elementos[i];
        }
        elementos = nuevo;
    }

    public void encolar(Persona p) {
        if (tamano == elementos.length) {
            redimensionar();
        }
        elementos[tamano] = p;
        tamano++;
    }

    public Persona desencolar() {
        if (tamano == 0) return null;
        Persona atendida = elementos[0];
        for (int i = 0; i < tamano - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        elementos[tamano - 1] = null;
        tamano--;
        return atendida;
    }

    public Persona get(int indice) {
        if (indice < 0 || indice >= tamano) return null;
        return elementos[indice];
    }

    public void insertarEn(int indice, Persona p) {
        if (tamano == elementos.length) {
            redimensionar();
        }
        for (int i = tamano; i > indice; i--) {
            elementos[i] = elementos[i - 1];
        }
        elementos[indice] = p;
        tamano++;
    }

    public Persona removerEn(int indice) {
        if (indice < 0 || indice >= tamano) return null;
        Persona eliminada = elementos[indice];
        for (int i = indice; i < tamano - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        elementos[tamano - 1] = null;
        tamano--;
        return eliminada;
    }

    public void insertarPreferente(Persona p) {
        int posInse = 0;
        for (int i = tamano - 1; i >= 0; i--) {
            if (elementos[i].esPreferente()) {
                posInse = i + 1;
                break;
            }
        }
        insertarEn(posInse, p);
    }
}