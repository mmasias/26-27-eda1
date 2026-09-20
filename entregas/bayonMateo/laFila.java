package entregas.bayonMateo;

import java.util.ArrayList;

class laFila {
    public static void main(String[] args) {
        Fila filaActual = new Fila(); 

        int personasAtendidas = 0;
        final int TIEMPO_SIMULACION = 240;

        for(int i= 0; i <= TIEMPO_SIMULACION; i++){
            if(Math.random() < 0.6) {
                Cliente c = new Cliente(i); 
                filaActual.llegaCliente(c);
            }

            if(Math.random() < 0.4) {
                Cliente atendido = filaActual.atenderCliente();
                if (atendido != null){
                    personasAtendidas++;
                }
            }
        }

        System.out.println("Personas atendidas: " + personasAtendidas);
        System.out.println("En fila: " + filaActual.getTamaño());
    }

    public static class Fila {
        private ArrayList<Cliente> fila = new ArrayList<>();
        private final int CAPACIDAD_MAXIMA = 15;

        public boolean estaLlena() {
            return fila.size() >= CAPACIDAD_MAXIMA;
        }

        public void llegaCliente(Cliente c) {
            if(!estaLlena()){
                fila.add(c);
            }
        }

        public Cliente atenderCliente() {
            return fila.remove(0);
        }

        public int getTamaño() {
            return fila.size();
        }
    }

    public static class Cliente {
        private int llegadaMinuto;

        public Cliente(int llegadaMinuto) {
            this.llegadaMinuto = llegadaMinuto;
        }

        public int getLlegadaMinuto() {
            return llegadaMinuto;
        }
     }
}
