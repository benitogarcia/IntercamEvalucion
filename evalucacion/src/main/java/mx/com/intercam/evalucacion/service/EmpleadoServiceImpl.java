package mx.com.intercam.evalucacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.intercam.evalucacion.entity.Empleado;
import mx.com.intercam.evalucacion.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRep;

	@Transactional
	@Override
	public Empleado save(Empleado empleado) {
		return empleadoRep.save(empleado);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Empleado> findAll() {
		return empleadoRep.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Empleado findById(Long id) {
		return empleadoRep.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		empleadoRep.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean existeEmpleado(Empleado empleado) {
		boolean existe = false;

		Empleado _empleado = empleadoRep
				.findByNombreAndApellidoPaternoAndApellidoMaternoAndFechaNacimientoAndCodigoPostalAndIngresos(empleado.getNombre(),
						empleado.getApellidoPaterno(), empleado.getApellidoMaterno(), empleado.getFechaNacimiento(),
						empleado.getCodigoPostal(), empleado.getIngresos())
				.orElse(null);

		existe = !(_empleado == null);

		return existe;
	}

	@Override
	public boolean existeEmpleado(Long id) {
		return empleadoRep.existsById(id);
	}

}
