package mx.com.intercam.evalucacion.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.intercam.evalucacion.entity.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

	Optional<Empleado> findByNombreAndApellidoPaternoAndApellidoMaternoAndFechaNacimientoAndCodigoPostalAndIngresos(String nombre,
			String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String  codigoPostal, float ingresos);

}
