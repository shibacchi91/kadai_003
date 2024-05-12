package com.example.samuraitravel.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Reviewformat;
import com.example.samuraitravel.entity.User;


public interface ReviewRepository extends JpaRepository<Reviewformat, Integer>{
	Reviewformat getReferenceById(Reviewformat review);
	public Page<Reviewformat> findByUserOrderByCreatedAtDesc(User user,Pageable pageable);

	public Page<Reviewformat> findByHouseOrderByCreatedAtDesc(House house, Pageable pageable);

}
