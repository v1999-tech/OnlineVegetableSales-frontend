package com.siri.proj.java.onlinevegetablesale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.siri.proj.java.onlinevegetablesale.entity.BillingDetails;
import com.siri.proj.java.onlinevegetablesale.repository.BillingDetailsRepository;


@Service
public class BillingDetailsService {
	
	@Autowired
	BillingDetailsRepository  billingDetailsRepository;

	public BillingDetails createBillingDetails(BillingDetails  billingDetails) {
		return  billingDetailsRepository.save(billingDetails);
	}

	public List<BillingDetails> getBillingDetails() {
		return billingDetailsRepository.findAll();
	}

	public boolean deleteAllBillingDetails() {
		try {
			billingDetailsRepository.deleteAll();
		}
		catch(Exception e) {
		return false;
	}
		return true;
	}

	public BillingDetails updateBillingDetails(int orderId, BillingDetails obj) {
		Optional<BillingDetails>  bdContainer=billingDetailsRepository.findById(orderId);
		if(bdContainer.isPresent())
		{
			BillingDetails oldObj=bdContainer.get();
			oldObj.setBuildingId(obj.getBuildingId());
			System.out.println("Succesfully updated!!!!");
			return billingDetailsRepository.saveAndFlush(oldObj);
		}
		System.out.println("No object found with these id");
		return obj;
	}
	}


