public class SimuladorElecciones {
    public static void main(String[] args) {
        Lista censo = new Lista();
        Lista candidaturas = new Lista();
        Cola urna = new Cola();
        Pila historialRecuento = new Pila();

        int votosBlanco = 0;
        int votosNulo = 0;

        censo.insertar(new Alumno("Ana"));
        censo.insertar(new Alumno("Luis"));
        censo.insertar(new Alumno("Marta"));

        Candidato c1 = new Candidato("Carlos");
        Candidato c2 = new Candidato("Elena");
        Candidato c3 = new Candidato("Rechazado");
        candidaturas.insertar(c1);
        candidaturas.insertar(c2);
        candidaturas.insertar(c3);

        System.out.println("---SUCCESOS DE LA VOTACION---");
        
        censo.insertar(new Alumno("Pedro"));
        System.out.println("Alumno llega tarde: Pedro anadido al censo.");

        censo.eliminar(new Alumno("Luis"));
        System.out.println("Alumno se marcha: Luis eliminado del censo.");

        candidaturas.eliminar(new Candidato("Rechazado"));
        System.out.println("Candidatura rechazada: Candidato 'Rechazado' eliminado.");

        if (censo.buscar(new Alumno("Jorge")) == null) {
            System.out.println("Intento de voto denegado: Jorge no esta presente.");
        }

        Alumno votante = (Alumno) censo.buscar(new Alumno("Ana"));
        if (votante != null && !votante.yaVoto) {
            votante.yaVoto = true;
            urna.encolar(new Voto("Valido", c1));
            System.out.println("Ana vota por Carlos.");
        }

        if (votante != null && votante.yaVoto) {
            System.out.println("Intento de doble voto: Ana es bloqueada.");
        }

        votante = (Alumno) censo.buscar(new Alumno("Marta"));
        if (votante != null && !votante.yaVoto) {
            votante.yaVoto = true;
            urna.encolar(new Voto("Blanco", null));
            System.out.println("Marta emite un voto en Blanco.");
        }

        votante = (Alumno) censo.buscar(new Alumno("Pedro"));
        if (votante != null && !votante.yaVoto) {
            votante.yaVoto = true;
            urna.encolar(new Voto("Nulo", null));
            System.out.println("Pedro emite un voto Nulo.");
        }

        urna.encolar(new Voto("Valido", c2));
        System.out.println("Voto extra (error mesa) por Elena encolado.");
        urna.encolar(new Voto("Valido", c1));
        System.out.println("Voto extra por Carlos para forzar empate.");

        System.out.println("---RECUENTO---");
        Object objExtraido;
        while ((objExtraido = urna.desencolar()) != null) {
            Voto papeleta = (Voto) objExtraido;
            historialRecuento.apilar(papeleta);
            
            if (papeleta.tipo.equals("Valido") && papeleta.candidato != null) {
                papeleta.candidato.votos++;
            } else if (papeleta.tipo.equals("Blanco")) {
                votosBlanco++;
            } else if (papeleta.tipo.equals("Nulo")) {
                votosNulo++;
            }
        }

        Object objError = historialRecuento.desapilar();
        if (objError != null) {
            Voto error = (Voto) objError;
            if (error.tipo.equals("Valido")) {
                error.candidato.votos--;
                System.out.println("Correccion recuento: Se anula el ultimo voto de " + error.candidato.nombre);
            }
        }

        System.out.println("---ANUNCIO---");
        int maxVotos = -1;
        boolean hayEmpate = false;
        Candidato ganador = null;

        Nodo actual = candidaturas.cabeza;
        while (actual != null) {
            Candidato c = (Candidato) actual.dato;
            System.out.println(c.nombre + " obtuvo " + c.votos + " votos.");
            
            if (c.votos > maxVotos) {
                maxVotos = c.votos;
                ganador = c;
                hayEmpate = false;
            } else if (c.votos == maxVotos) {
                hayEmpate = true;
            }
            actual = actual.siguiente;
        }

        System.out.println("Blancos: " + votosBlanco + " | Nulos: " + votosNulo);
        if (hayEmpate) {
            System.out.println("RESULTADO: Empate detectado en el primer puesto con " + maxVotos + " votos.");
        } else {
            System.out.println("RESULTADO: Ganador proclamado: " + ganador.nombre + ".");
        }
    }
}