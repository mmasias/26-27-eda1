public class Mapa {

    
    public void proyectar(int minuto, Fila fila, Caja[] cajas) {
        System.out.println("Minuto: " + (minuto + 1)); 
        System.out.println("------------------------------------------------");
        
        System.out.print("Fila: ");
        
        for (int i = 0; i < fila.getNumeroPersonas(); i++) {
            
            if (fila.getCliente(i).esPreferente()) {
                System.out.print("(V) "); 
            } else {
                System.out.print("\\o/ ");
            }
        }
        System.out.println("\n(Total formados: " + fila.getNumeroPersonas() + ")"); 
        
        System.out.println("------------------------------------------------");
        
        for (int i = 0; i < cajas.length; i++) {
            System.out.print("Caja " + (i + 1) + ": ");
            if (cajas[i].estaVacia()) {
                System.out.println("[ Vacía ]");
            } else {
                System.out.println("\\o/ (Atendiendo)");
            }
        }
        System.out.println("\n");
    }

    
    public void imprimirEvento(String mensaje) {
        System.out.println(">>> EVENTO: " + mensaje + " <<<");
    }
    
    public void pantallaFinal(int atendidos, int enFila, int vips, int colados, int aburridos) {
    System.out.println("================================================");
    System.out.println("--- RESULTADOS DE LA SIMULACION (4 HORAS) ---");
    System.out.println("Total de personas atendidas: " + atendidos);
    System.out.println("Personas que quedaron en la fila: " + enFila);
    System.out.println("------------------------------------------------");
    System.out.println("Eventos especiales registrados:");
    System.out.println("Clientes VIP acomodados: " + vips);
    System.out.println("Colados sin vergüenza: " + colados);
    System.out.println("Personas que se aburrieron y se fueron: " + aburridos);
    System.out.println("================================================");
}
}