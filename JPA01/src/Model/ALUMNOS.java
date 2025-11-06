package Model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity

@NamedQuery(name = "ALUMNOS.todos",query = "SELECT d from ALUMNOS d")

public class ALUMNOS implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	String dni;
	String nombre;
	String apellidos;
	int edad;
	
	public ALUMNOS() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ALUMNOS(String dni, String nombre, String apellidos, int edad) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	@Override
	public int hashCode() {
		return Objects.hash(apellidos, dni, edad, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ALUMNOS other = (ALUMNOS) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(dni, other.dni) && edad == other.edad
				&& Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
		return "ALUMNOS [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + "]";
	}
	
	
}
