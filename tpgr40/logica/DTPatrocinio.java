package logica;

public class DTPatrocinio {
	private String codigo;
	private TipoNivelPatrocinio tipo;
	private float monto;
	private String tipoRegistro;
	private int cantRegistros;
	
	public DTPatrocinio(String codigo, TipoNivelPatrocinio tipo, float monto, String tipoRegistro, int cantRegistros) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.monto = monto;
		this.tipoRegistro = tipoRegistro;
		this.cantRegistros = cantRegistros;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public TipoNivelPatrocinio getTipo() {
		return tipo;
	}
	
	public float getMonto() {
		return monto;
	}
	
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	
	public int getCantRegistros() {
		return cantRegistros;
	}
}