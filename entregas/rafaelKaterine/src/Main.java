import java.util.Random;

public class Main {

    public static void limpiarPantalla() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    public static void main(String[] args) {
        Tiempo tiempo = new Tiempo(120);
        Fila fila = new Fila();
        Caja caja = new Caja();
        Random random = new Random();

        int contadorPersonas = 1;

        while (tiempo.trabajando()) {
            tiempo.avanzarMinuto();
            int minActual = tiempo.mostrarMinutoActual();

            limpiarPantalla();

            System.out.println("Minuto " + minActual);

            if (random.nextDouble() < 0.60) {
                Persona nueva = new Persona(minActual, contadorPersonas++);
                boolean entro = fila.agregarPersona(nueva);
                
                if (entro) {
                    System.out.println("Llega persona: " + nueva.perfil());
                } else {
                    System.out.println("Persona " + nueva.perfil() + " no entra (fila llena)");
                }
            }

            if (caja.estaDisponible()) {
                Persona atendida = fila.atender();
                if (atendida != null) {
                    System.out.println("Caja atiende a: " + atendida.perfil());
                }
            }

            if (tiempo.reglasNuevasActivas()) {
                
                fila.revisarAburrimiento(minActual);

                if (random.nextDouble() < 0.10) {
                    Persona colado = new Persona(minActual, contadorPersonas++);
                    if (fila.colarse(colado)) {
                        System.out.println("Se cuela persona: " + colado.perfil());
                    }
                }

                if (random.nextDouble() < 0.05) {
                    if (fila.transferirCompras()) {
                        System.out.println("Una persona transfiere sus compras y sale");
                    }
                }

                if (tiempo.esTiempoDeParlante() && fila.getTamaño() > 25) {
                    Persona atendidaRapida = fila.atender();
                    if (atendidaRapida != null) {
                        System.out.println("Parlante (Fila > 25): Atendida en caja rápida " + atendidaRapida.perfil());
                    }
                }
            }

            fila.mostrarEstado();

            try {
                Thread.sleep(3000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}