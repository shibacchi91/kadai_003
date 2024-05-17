package com.example.samuraitravel.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.example.samuraitravel.entity.Favorite;
import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.FavoriteRegisterForm;
import com.example.samuraitravel.repository.FavoriteRepository;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.UserRepository;

@Service
public class FavoriteService {
	private final FavoriteRepository favoriteRepository;
	private final HouseRepository houseRepository;
	private final UserRepository userRepository;

	public FavoriteService(FavoriteRepository favoriteRepository, HouseRepository houseRepository,
			UserRepository userRepository) {
		this.favoriteRepository = favoriteRepository;
		this.houseRepository = houseRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public void create(FavoriteRegisterForm favoriteRegisterForm, BindingResult bindingResult) {
		House house = houseRepository.getReferenceById(favoriteRegisterForm.getHouseId());
		User user = userRepository.getReferenceById(favoriteRegisterForm.getUserId());

		// 重複チェック
		if (favoriteRepository.existsByHouseIdAndUserId(house.getId(), user.getId())) {
			bindingResult.reject("duplicate", "This favorite already exists.");
			return;
		}

		Favorite favorite = new Favorite();
		favorite.setHouse(house);
		favorite.setUser(user);

		favoriteRepository.save(favorite);
	}

	
	public boolean isFavorite(House house, User user) {
		Favorite isFavorited = favoriteRepository.findByHouseAndUser(house, user);
		return isFavorited != null;
	}
}
