package main.java.publicar;

import main.java.presentacion.Principal;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ip, puertoEvento, puertoUsuario, puertoInstitucion, puertoCategoria;

        Properties properties= new Properties();
        // Cargar parámetros (ip y puertos) desde archivo de configuración
        try {
            properties.load(new FileInputStream(new File(System.getProperty("user.home") + "/.eventosUy/configuracion.properties")));

            ip = (String) properties.get("IP");
            puertoEvento = (String) properties.get("PUERTO_EVENTO");
            puertoUsuario = (String) properties.get("PUERTO_USUARIO");
            puertoInstitucion = (String) properties.get("PUERTO_INSTITUCION");
            puertoCategoria = (String) properties.get("PUERTO_CATEGORIA");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    	Principal.main(args);
    	
        PublicadorEvento pe = new PublicadorEvento(ip, puertoEvento);
        pe.publicar();
        
        PublicadorUsuario pu = new PublicadorUsuario(ip, puertoUsuario);
        pu.publicar();
        
        PublicadorInstitucion pi = new PublicadorInstitucion(ip, puertoInstitucion);
        pi.publicar();
        
        PublicadorCategoria pc = new PublicadorCategoria(ip, puertoCategoria);
        pc.publicar();
    }

} 
