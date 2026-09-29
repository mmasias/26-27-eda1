public class Persona {

    private int numero;
    private int llegada;
    private boolean preferente;
    private int tipo;

    public Persona(int numero, int llegada, boolean preferente, int tipo) {
        this.numero = numero;
        this.llegada = llegada;
        this.preferente = preferente;
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public int getLlegada() {
        return llegada;
    }

    public boolean esPreferente() {
        return preferente;
    }

    public int getTipo() {
        return tipo;
    }
}