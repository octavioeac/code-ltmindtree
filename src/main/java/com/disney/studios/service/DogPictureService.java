package com.disney.studios.service;

import com.disney.studios.entity.DogPicture;
import com.disney.studios.repository.DogBreedRepository;
import com.disney.studios.repository.DogPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DogPictureService {


    @Autowired
    private DogPictureRepository dogPictureRepository;


    @Autowired
    private DogBreedRepository dogBreedRepository;


    public Map<String, List<DogPicture>> findAllPicturesGroupedByBreed() {
        List<DogPicture> allPictures = dogPictureRepository.findAll();
        Map<String, List<DogPicture>> picturesGroupedByBreed = new HashMap<>();
        for (DogPicture picture : allPictures) {
            picturesGroupedByBreed
                    .computeIfAbsent(picture.getBreed().getName(), k -> new ArrayList<>())
                    .add(picture);
        }
        return picturesGroupedByBreed;
    }

    public List<DogPicture> findPicturesByBreed(String breedName) {
        // Retrieve dog pictures by breed name
        return dogPictureRepository.findByBreedName(breedName);
    }

    public void voteUp(Long pictureId) {
        DogPicture picture = dogPictureRepository.findById(pictureId).orElse(null);
        if (picture != null) {
            picture.setFavoriteCount(picture.getFavoriteCount() + 1);
            dogPictureRepository.save(picture);
        }
    }

    public void voteDown(Long pictureId) {
        DogPicture picture = dogPictureRepository.findById(pictureId).orElse(null);
        if (picture != null && picture.getFavoriteCount() > 0) {
            picture.setFavoriteCount(picture.getFavoriteCount() - 1);
            dogPictureRepository.save(picture);
        }
    }
}
