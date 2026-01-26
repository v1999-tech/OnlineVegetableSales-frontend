package com.siri.proj.java.onlinevegetablesale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.siri.proj.java.onlinevegetablesale.entity.VegetableDTO;
import com.siri.proj.java.onlinevegetablesale.repository.VegetableDTORepository;

@Service
public class VegetableDTOService {
	@Autowired
	VegetableDTORepository  vegetableDTORepository;
	public VegetableDTO addVegetableDTO(VegetableDTO vegetableDTO) {
		return vegetableDTORepository.save(vegetableDTO) ;
	}

	public List<VegetableDTO> getVegetableDTO() {
		return vegetableDTORepository.findAll();
	}

	public boolean deleteAllVegetableDTO() {
		try {
			vegetableDTORepository.deleteAll();
		}
		catch(Exception e) {
		return false;
	}
		return true;
	}

	public VegetableDTO updateVegetableDTO(int vegId, VegetableDTO obj) {
		Optional<VegetableDTO>  vdtoContainer=vegetableDTORepository.findById(vegId);
		if(vdtoContainer.isPresent())
		{
			VegetableDTO oldObj=vdtoContainer.get();
			oldObj.setPrice(obj.getPrice());
			System.out.println("Succesfully updated!!!!");
			return vegetableDTORepository.saveAndFlush(oldObj);
		}
		System.out.println("No object found with these id");
		return obj;
	}

}
