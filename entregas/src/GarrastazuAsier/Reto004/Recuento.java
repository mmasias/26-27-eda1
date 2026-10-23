package GarrastazuAsier.Reto004;

import java.util.ArrayList;

public class Recuento {
    private int[] votosCandidatos;
    private int votosBlancos;
    private int votosNulos;

    public Recuento(ArrayList<Candidato> candidatos) {
        votosCandidatos = new int[candidatos.size()];
        votosBlancos = 0;
        votosNulos = 0;
    }

    public void contarVoto(Voto voto, ArrayList<Candidato> candidatos) {
        String nombre = voto.getCandidato();

        if (nombre == null || nombre.trim().isEmpty()) {
            votosBlancos++;
            return;
        }

        for (int i = 0; i < candidatos.size(); i++) {
            if (candidatos.get(i).getNombre()
                    .equalsIgnoreCase(nombre)) {
                votosCandidatos[i]++;
                return;
            }
        }

        votosNulos++;
    }

    public void mostrarResultados(ArrayList<Candidato> candidatos) {
        System.out.println("=== RESULTADOS ELECTORALES ===");

        for (int i = 0; i < candidatos.size(); i++) {
            System.out.println(
                candidatos.get(i).getNombre() + ": "
                + votosCandidatos[i] + " votos"
            );
        }

        System.out.println("Votos en blanco: " + votosBlancos);
        System.out.println("Votos nulos: " + votosNulos);
    }
}