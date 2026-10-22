package entregas;
 
public class Ejemplo {
 
    public static void main(String[] args) {
 
        System.out.println("=== INICIO DEL PROCESO ELECTORAL ===");
 
        Lista presentes = new Lista();
        presentes.insertEnd("Juanito");
        presentes.insertEnd("Pepito");
        presentes.insertEnd("Anita");
        presentes.insertEnd("Marta");
 
        System.out.println("Llega tarde: Carlos");
        presentes.insertEnd("Carlos");
 
        System.out.println("Se marcha del aula: Marta");
        presentes.delete("Marta");
 
        imprimirArreglo("PRESENTES EN AULA", presentes.listAll());
 
        Lista candidaturas = new Lista();
        proponer(candidaturas, presentes, "Anita");
        proponer(candidaturas, presentes, "Pepito");
 
        System.out.println("Intento de propuesta de alumna ausente (Marta):");
        proponer(candidaturas, presentes, "Marta");
 
        System.out.println("Candidatura voluntariamente retirada: Pepito");
        candidaturas.delete("Pepito");
 
        System.out.println("Llega tarde y se propone: Luisa");
        presentes.insertEnd("Luisa");
        proponer(candidaturas, presentes, "Luisa");
 
        String[] candidatosDefinitivos = candidaturas.listAll();
        imprimirArreglo("CANDIDATOS DEFINITIVOS (PLAZO CERRADO)", candidatosDefinitivos);
 
        Pizarra pizarra = new Pizarra(candidatosDefinitivos);
        Queue urna = new Queue();
        Lista votantesRegistrados = new Lista();
 
        System.out.println("\n=== FASE DE VOTACION ===");
 
        votar(urna, votantesRegistrados, presentes, "Juanito", "Anita");
        votar(urna, votantesRegistrados, presentes, "Carlos", "Luisa");
        votar(urna, votantesRegistrados, presentes, "Anita", "");
        votar(urna, votantesRegistrados, presentes, "Luisa", "Superman");
 
        System.out.println("Intento de voto de persona ausente (Marta):");
        votar(urna, votantesRegistrados, presentes, "Marta", "Anita");
 
        System.out.println("Intento de voto doble (Juanito):");
        votar(urna, votantesRegistrados, presentes, "Juanito", "Luisa");
 
        reportarAbstencion(presentes, votantesRegistrados, urna);
 
        System.out.println("\n=== FASE DE ESCRUTINIO Y LECTURA DE PAPELETAS ===");
        while (!urna.isEmpty()) {
            String voto = urna.dequeue();
            System.out.println("Secretario canta: " + (voto.equals("") ? "[En blanco]" : voto));
            pizarra.anotar(voto);
        }
 
        pizarra.mostrar();
 
        System.out.println("\n=== CORRECCION DE ERROR EN PIZARRA ===");
        System.out.println("El secretario se equivoca y anota un palito de mas a Anita.");
        pizarra.anotar("Anita");
        pizarra.mostrar();
 
        System.out.println("El presidente detecta el fallo y ordena corregirlo.");
        pizarra.corregir("Anita");
        pizarra.mostrar();
 
        pizarra.proclamar();
    }
 
    static void proponer(Lista candidaturas, Lista presentes, String alumno) {
        if (!presentes.contains(alumno)) {
            System.out.println("Candidatura rechazada: " + alumno + " no esta presente en el aula.");
        } else if (candidaturas.contains(alumno)) {
            System.out.println("Candidatura rechazada: " + alumno + " ya esta propuesto.");
        } else {
            candidaturas.insertEnd(alumno);
            System.out.println("Candidatura aceptada: " + alumno);
        }
    }
 
    static void votar(Queue urna, Lista votantes, Lista presentes, String alumno, String papeleta) {
        if (!presentes.contains(alumno)) {
            System.out.println("Voto rechazado: " + alumno + " no esta presente en el aula.");
            return;
        }
        if (votantes.contains(alumno)) {
            System.out.println("Voto rechazado: " + alumno + " ya ha ejercido su voto.");
            return;
        }
        votantes.insertEnd(alumno);
        urna.enqueue(papeleta);
        System.out.println("Voto emitido correctamente por: " + alumno);
    }
 
    static void reportarAbstencion(Lista presentes, Lista votantes, Queue urna) {
        String[] lista = presentes.listAll();
        int abstenciones = 0;
        int i = 0;
        while (i < lista.length) {
            if (!votantes.contains(lista[i])) {
                abstenciones++;
            }
            i++;
        }
 
        System.out.println("\n--- INFORME DE ABSTENCION ---");
        System.out.println("Presentes en el aula : " + lista.length);
        System.out.println("Votos en la urna     : " + urna.size());
        System.out.println("Abstenciones         : " + abstenciones);
    }
 
    static void imprimirArreglo(String titulo, String[] elementos) {
        System.out.println("\n" + titulo + "--------------------");
        int i = 0;
        while (i < elementos.length) {
            System.out.println("- " + elementos[i]);
            i++;
        }
        System.out.println("------------------------------------");
    }
}
 