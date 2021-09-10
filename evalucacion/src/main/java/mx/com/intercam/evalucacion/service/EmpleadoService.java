package mx.com.intercam.evalucacion.service;

import java.util.List;

import mx.com.intercam.evalucacion.entity.Empleado;

public interface EmpleadoService {
	
	Empleado save(Empleado empleado);
	
	List<Empleado> findAll();
	
	Empleado findById(Long id);
	
	void delete(Long id);
	
	boolean existeEmpleado(Empleado empleado);
	
	boolean existeEmpleado(Long id);

}
