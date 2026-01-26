package com.siri.proj.java.onlinevegetablesale.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.siri.proj.java.onlinevegetablesale.entity.VegetableDTO;
import com.siri.proj.java.onlinevegetablesale.service.VegetableDTOService;


@RestController
public class VegetableDTOController {
	@Autowired
	VegetableDTOService  vegetableDTOService;
	@PostMapping("/vegetableDTO")
	public  VegetableDTO   addVegetableDTO(@Valid @RequestBody  VegetableDTO vegetableDTO)
	{
		return  vegetableDTOService.addVegetableDTO(vegetableDTO);
		
	}
	
	@GetMapping("/vegetableDTO")
	public  List<VegetableDTO>  getVegetableDTO()
	{
		return vegetableDTOService.getVegetableDTO();
	}
	
	@DeleteMapping("/vegetableDTO")
	public boolean  deleteAllVegetableDTO()
	{
		return vegetableDTOService.deleteAllVegetableDTO();
	}
	
	@PutMapping("/VegetableDTO/{vegId}")
	public VegetableDTO  updateVegetableDTO(@RequestBody int vegId, @RequestBody VegetableDTO obj)
	{
		return vegetableDTOService.updateVegetableDTO(vegId, obj);
		
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)

	@ExceptionHandler(MethodArgumentNotValidException.class)

	public Map<String, String> handleValidationExceptions(

			MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String fieldName = ((FieldError) error).getField();

			String errorMessage = error.getDefaultMessage();

			errors.put(fieldName, errorMessage);

		});

		return errors;
	}
	
}


