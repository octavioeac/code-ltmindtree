package com.disney.studios.repository;

import com.disney.studios.entity.DogBreed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DogBreedRepository extends JpaRepository<DogBreed,Long> {
    Optional<Object> findById(Long pictureId);
}
