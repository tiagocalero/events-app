package logica;

import java.time.LocalDate;

public class Patrocinio {
	private String codigo;
	private float monto;
	private TipoNivelPatrocinio tipo;
	private LocalDate fecha;
	private int cantRegistros;
	private int cantRegistrosUsados;
	
	private Edicion edicion;
	private Institucion institucion;
	private TRegistro tipoRegistro;
	
	public Patrocinio(
	        String codigo,
	        float monto,
	        TipoNivelPatrocinio tipo,
	        LocalDate fecha,
	        int cantRegistros,
	        Edicion edicion,
	        Institucion institucion,
	        TRegistro tipoRegistro
	) {
		this.codigo = codigo;
		this.monto = monto;
		this.tipo = tipo;
		this.fecha = fecha;
		this.cantRegistros = cantRegistros;
		this.tipoRegistro = tipoRegistro;

		this.edicion = edicion;
		this.institucion = institucion;
		this.cantRegistrosUsados = 0;
		
		institucion.agregarPatrocinio(this);
		edicion.agregarPatrocinio(this);
	}
	
	public DTPatrocinio getDatos() {
		return new DTPatrocinio(
			codigo,
			tipo,
			monto,
			tipoRegistro.getNombre(),
			cantRegistros,
			fecha,
			getNombreInstitucion(),
			institucion.getImagen()
		);
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombreInstitucion() {
		return institucion.getNombre();
	}
	
	public boolean buscarEdicion(Edicion edicionBuscada) {
		return edicionBuscada == edicion;
	}
	
	public int getCantRegistros() {
		return cantRegistrosUsados;
	}
	
	public int getCantRegistrosUsados() {
		return cantRegistrosUsados;
	}
	public void aumentarCantRegistrosUsados() {
		cantRegistrosUsados = cantRegistrosUsados + 1;
	}
}