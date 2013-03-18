package vistas;

public class Roles {

	
	private String nombre;
	private int valor;
	
	public Roles(String miNombre, int miValor)
	{
		this.nombre=miNombre;
		this.valor=miValor;
		
	}
	
	
	public int getValor ()
	
	{
		return valor;
	}
	
    public String toString ()
	
	{
		return nombre;
	}
	
	
	
}
