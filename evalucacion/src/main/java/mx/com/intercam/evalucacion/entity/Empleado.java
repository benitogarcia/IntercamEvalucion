package mx.com.intercam.evalucacion.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "empleados")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Pattern(regexp = "[ A-Za-zñÑáéíóúÁÉÍÓÚ]{1,512}", message = "El Nombre solo debe contener letras [aA-zZ] y espacios.")
	@NotEmpty(message = "El Nombre no deve ser vacio.")
	@Size(max = 512, message = "El Nombre tiene una longitud maxima de 512 caracteres.")
	private String nombre;

	@NotEmpty(message = "El Apellido Paterno no deve ser vacio.")
	@Pattern(regexp = "[ A-Za-zñÑáéíóúÁÉÍÓÚ]{0,512}", message = "El Apellido Paterno solo debe contener letras [aA-zZ] y espacios")
	@Size(max = 512, message = "El Apellido Paterno tiene una longitud maxima de 512 caracteres.")
	private String apellidoPaterno;

	@Pattern(regexp = "[ A-Za-zñÑáéíóúÁÉÍÓÚ]{0,512}", message = "El Apellido Materno solo debe contener letras [aA-zZ] y espacios.")
	@Size(max = 512, message = "El Apellido Materno tiene una longitud maxima de 512 caracteres.")
	private String apellidoMaterno;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@Past(message = "La Fecha de nacimiento debe ser menor a la fecha actual.")
	private Date fechaNacimiento;

    @PositiveOrZero(message = "El ingreso debe ser positivo.")
	private float ingresos;

	@Pattern(regexp = "[0-9]{5}", message = "El Código postal debe tener una longitud de 6 digitos [0-9]")
	private String codigoPostal;

	public Empleado() {
		super();
	}

	public Empleado(String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, float ingresos,
			String codigoPostal) {
		super();
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaNacimiento = fechaNacimiento;
		this.ingresos = ingresos;
		this.codigoPostal = codigoPostal;
	}

	public Empleado(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento,
			float ingresos, String codigoPostal) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaNacimiento = fechaNacimiento;
		this.ingresos = ingresos;
		this.codigoPostal = codigoPostal;
	}
	
	/*
	 * @PrePersist public void antesPersistir() { this.nombre =
	 * this.formatearTexto(this.nombre); this.apellidoPaterno =
	 * this.formatearTexto(this.apellidoPaterno); this.apellidoMaterno =
	 * this.formatearTexto(this.apellidoMaterno); }
	 */
	
	private String formatearTexto(String texto) {
		texto=texto.trim();
        texto=texto.replaceAll("\\s{2,}", " ");
        texto = texto.equals(" ") ? "" : texto;
        return texto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = this.formatearTexto(nombre);
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = this.formatearTexto(apellidoPaterno);
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = this.formatearTexto(apellidoMaterno);
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public float getIngresos() {
		return ingresos;
	}

	public void setIngresos(float ingresos) {
		this.ingresos = ingresos;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", ingresos="
				+ ingresos + ", codigoPostal=" + codigoPostal + "]";
	}

}
