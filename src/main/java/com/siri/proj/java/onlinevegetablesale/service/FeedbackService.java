package com.siri.proj.java.onlinevegetablesale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siri.proj.java.onlinevegetablesale.entity.Feedback;
import com.siri.proj.java.onlinevegetablesale.repository.FeedbackRepository;

@Service
public class FeedbackService {
	@Autowired
	FeedbackRepository  feedbackRepository;

	public Feedback addFeedback(Feedback feedback) {
		return feedbackRepository.save(feedback);
	}

	public List<Feedback> getFeedback() {
		return feedbackRepository.findAll();
	}

	public boolean deleteAllFeedbacks() {
		try {
			feedbackRepository.deleteAll();
		}
		catch(Exception e) {
		return false;
	}
		return true;
	}

	public Feedback updateFeedback(int customerId, Feedback obj) {
		Optional<Feedback>  fbContainer=feedbackRepository.findById(customerId);
		if(fbContainer.isPresent())
		{
			Feedback oldObj=fbContainer.get();
			oldObj.setRating(obj.getRating());
			System.out.println("Succesfully updated!!!!");
			return feedbackRepository.saveAndFlush(oldObj);
		}
		System.out.println("No object found with these id");
		return obj;
	}

}
