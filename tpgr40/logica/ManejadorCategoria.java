import Categoria;

public class ManejadorCategoria {
	private Map<String, Categoria> categorias;
    private static ManejadorCategoria instance;

    private ManejadorCategoria() {}

    public static ManejadorCategoria getInstance() {
        if (instance == null) {
            instance = new ManejadorCategoria();
        }
        return instance;
    }

	// Devuelve una copia del map de eventos sobre el que se puede iterar
	public Map<String, Categoria> categorias() {
        return Map.copyOf(categorias);
    }
}