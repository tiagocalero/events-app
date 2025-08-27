import DTFecha;

public class DTAsistente extends DTUsuario {
	private String apellido;
	private DTFecha nacimiento;
	
	public DTAsistente(String nick, String nom, String c, String ap, DTFecha fecha) {
		super(nick, nom, c);
		this.apellido = ap;
		this.nacimiento = fecha;
	}
	
	public String getNick( ) {return nick; }
	public String getNom( ) {return nom; }
	public String getC( ) {return c; }
	public String getAp( ) {return ap; }
	public DTFecha getFecha( ) {return fecha; }
	
}