import java.util.ArrayList;

public class Fila {

    private final int maximoPersonas;
    private final ArrayList<Cliente> clientes;

    public Fila(int maximoPersonas) {
        this.maximoPersonas = maximoPersonas;
        this.clientes = new ArrayList<Cliente>();
    }

    public int tamano() {
        return clientes.size();
    }

    public boolean estaVacia() {
        return clientes.isEmpty();
    }

    public boolean estaLlena() {
        return clientes.size() >= maximoPersonas;
    }

    public Cliente clienteEn(int posicion) {
        return clientes.get(posicion);
    }

    public void agregarAlFinal(Cliente cliente) {
        clientes.add(cliente);
    }

    public void agregarPreferente(Cliente cliente) {
        int posicion = 0;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).esPreferente()) {
                posicion = i + 1;
            }
        }
        clientes.add(posicion, cliente);
    }

    public void insertarEn(int posicion, Cliente cliente) {
        clientes.add(posicion, cliente);
    }

    public void retirarEn(int posicion) {
        clientes.remove(posicion);
    }

    public void atenderPrimero() {
        clientes.remove(0);
    }
}