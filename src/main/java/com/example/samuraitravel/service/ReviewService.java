package com.example.samuraitravel.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Reviewformat;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.repository.UserRepository;

//追加コンテンツ

@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final HouseRepository houseRepository;
	private final UserRepository userRepository;

	public ReviewService(ReviewRepository reviewRepository, HouseRepository houseRepository,
			UserRepository userRepository) {
		this.reviewRepository = reviewRepository;
		this.houseRepository = houseRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public void create(ReviewRegisterForm reviewRegisterForm) {
		Reviewformat review = new Reviewformat();
		House house = houseRepository.getReferenceById(reviewRegisterForm.getHouseId());
		User user = userRepository.getReferenceById(reviewRegisterForm.getUserId());
		review.setHouse(house);
		review.setUser(user);
		review.setRating(reviewRegisterForm.getRating());
		review.setReview(reviewRegisterForm.getReview());

		reviewRepository.save(review);
	}

	
	@Transactional
	public void update(ReviewEditForm reviewEditForm) {
		Reviewformat review = reviewRepository.getReferenceById(reviewEditForm.getId());
	     review.setRating(reviewEditForm.getRating());
	     review.setReview(reviewEditForm.getReview());
	     reviewRepository.save(review);
	}
}
