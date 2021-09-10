package mx.com.intercam.evalucacion.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Form {

	private static final Logger log = LoggerFactory.getLogger(Form.class);

	/**
	 * Crea un String con el mensaje del campo.
	 * @param result
	 * @return
	 */
	public static String errorMessages2(BindingResult result) {
		String errores = "";

		for (FieldError error : result.getFieldErrors()) {

			errores += error.getDefaultMessage() + "<br>";
		}
		return errores;
	}

	/**
	 * Crear un string con formato json.
	 * @param result
	 * @return
	 */
	public static String errorMessages(BindingResult result) {

		List<Map<String, String>> messages = result.getFieldErrors().stream().map(error -> {
			Map<String, String> _error = new HashMap<>();

			_error.put(error.getField(), error.getDefaultMessage());

			return _error;
		}).collect(Collectors.toList());

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";

		try {
			jsonString = mapper.writeValueAsString(messages);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		return jsonString;
	}

}
