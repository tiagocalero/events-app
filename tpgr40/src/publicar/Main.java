package publicar;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
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