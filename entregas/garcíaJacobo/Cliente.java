public class Cliente {
    private int minutoLlegada;

    private Cliente proximo;

    public Cliente(int minutoLlegada) {
        this.minutoLlegada = minutoLlegada;
    }

    public int minutosEnCola(int minutoActual) {
        return minutoActual - minutoLlegada;
    }

    public void proximoCliente(Cliente cliente){
        proximo=cliente;
    }

    public Cliente obtenerProximo(){
        return proximo;
    }
}
