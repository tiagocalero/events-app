package main.java.publicar;

import main.java.presentacion.Principal;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	Principal.main(args);
    	
        PublicadorEvento pe = new PublicadorEvento();
        pe.publicar();
        
        PublicadorUsuario pu = new PublicadorUsuario();
        pu.publicar();
        
        PublicadorInstitucion pi = new PublicadorInstitucion();
        pi.publicar();
        
        PublicadorCategoria pc = new PublicadorCategoria();
        pc.publicar();
    }

} 
