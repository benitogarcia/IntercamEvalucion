package mx.com.intercam.evalucacion.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.intercam.evalucacion.entity.Empleado;
import mx.com.intercam.evalucacion.service.EmpleadoService;
import mx.com.intercam.evalucacion.util.Form;
import mx.com.intercam.evalucacion.vo.ResponseMessage;

@RestController
@RequestMapping(value = "/empleados/")
@CrossOrigin(origins = {"http://localhost:4200"})
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@PostMapping("/")
	public ResponseEntity<?> crear(@Valid @RequestBody Empleado empleado, BindingResult result) {

		if (result.hasErrors()) {
			return new ResponseEntity<ResponseMessage>(new ResponseMessage(Form.errorMessages2(result)),
					HttpStatus.BAD_REQUEST);
		}

		if (empleadoService.existeEmpleado(empleado)) {
			return new ResponseEntity<ResponseMessage>(
					new ResponseMessage("Ya se encuentra registrado un usuario con los mismos datos."),
					HttpStatus.BAD_REQUEST);
		}

		empleado = empleadoService.save(empleado);

		if (empleado == null) {
			return new ResponseEntity<ResponseMessage>(
					new ResponseMessage("Error en el servidor, no se registro el empleado, intento mas tarde."),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Empleado>(empleado, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> findAll(){
		
		List<Empleado> empleados = empleadoService.findAll();
		
		if (empleados == null || empleados.isEmpty() || empleados.size()<=0) {
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("No se encontro ningun registro."), HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Empleado>>(empleados, HttpStatus.OK);
	}
	
	@GetMapping("/search/id/{id}")
	public ResponseEntity<?> findByid(@PathVariable(name = "id") Long id){
		
		if (!empleadoService.existeEmpleado(id)) {
			return new ResponseEntity<ResponseMessage>(
					new ResponseMessage("No se encontro el registro que busca."),
					HttpStatus.BAD_REQUEST);
		}
		
		Empleado _empleado = empleadoService.findById(id);
		
		return new ResponseEntity<Empleado>(_empleado, HttpStatus.OK);
	}
	

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @Valid @RequestBody Empleado empleado, BindingResult result) {
		
		if (!empleadoService.existeEmpleado(id)) {
			return new ResponseEntity<ResponseMessage>(
					new ResponseMessage("No se encontro el registro para actualizar."),
					HttpStatus.BAD_REQUEST);
		}
		
		if (result.hasErrors()) {
			return new ResponseEntity<ResponseMessage>(new ResponseMessage(Form.errorMessages2(result)),
					HttpStatus.BAD_REQUEST);
		}

		if (empleadoService.existeEmpleado(empleado)) {
			return new ResponseEntity<ResponseMessage>(
					new ResponseMessage("Ya se encuentra registrado un usuario con los mismos datos."),
					HttpStatus.BAD_REQUEST);
		}

		empleado.setId(id);
		empleado = empleadoService.save(empleado);

		if (empleado == null) {
			return new ResponseEntity<ResponseMessage>(
					new ResponseMessage("Error en el servidor, no se registro el empleado, intento mas tarde."),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Empleado>(empleado, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id){
		
		if (!empleadoService.existeEmpleado(id)) {
			return new ResponseEntity<ResponseMessage>(
					new ResponseMessage("No se encontro ningun registro para eliminar."),
					HttpStatus.BAD_REQUEST);
		}
		
		String message = "";
		try {
			empleadoService.delete(id);
			message = "El empleado se elimino.";
		} catch (Exception e) {
			message = "El empleado no se elimino, intento mas tarde.";
		}
		
		return new ResponseEntity<ResponseMessage>(
				new ResponseMessage(message),
				HttpStatus.OK);
	}

}
