public class Persona {

    private int numero;
    private boolean preferente;
    private int minutoLlegada;

    public Persona(int numero, boolean preferente, int minutoLlegada) {
        this.numero = numero;
        this.preferente = preferente;
        this.minutoLlegada = minutoLlegada;
    }

    public int getNumero() {
        return numero;
    }

    public boolean esPreferente() {
        return preferente;
    }

    public int getMinutoLlegada() {
        return minutoLlegada;
    }

    @Override
    public String toString() {
        if (preferente) {
            return "P" + numero + "(preferente)";
        }

        return "P" + numero;
    }
}