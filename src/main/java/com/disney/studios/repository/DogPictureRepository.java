package com.disney.studios.repository;

import com.disney.studios.entity.DogPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogPictureRepository extends JpaRepository<DogPicture,Long> {
    List<DogPicture> findByBreedName(String breedName);

    Optional<DogPicture> findById(Long pictureId);
}
