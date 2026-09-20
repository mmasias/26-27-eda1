public class CajaRegistradora {
    private int totalAtendidos;

    public CajaRegistradora() {
        this.totalAtendidos = 0;
    }

    public void atenderPersona() {
        this.totalAtendidos++;
    }

    public int getTotalAtendidos() {
        return this.totalAtendidos;
    }
}