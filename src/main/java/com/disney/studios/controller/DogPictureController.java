package com.disney.studios.controller;

import com.disney.studios.entity.DogPicture;
import com.disney.studios.service.DogPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dog-pictures")
public class DogPictureController {
    private final DogPictureService pictureService;

    @Autowired
    public DogPictureController(DogPictureService pictureService) {
        this.pictureService = pictureService;
    }

    @RequestMapping( value = "/grouped-by-breed",method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<DogPicture>>> findAllPicturesGroupedByBreed() {
        return ResponseEntity.ok(pictureService.findAllPicturesGroupedByBreed());
    }

    @RequestMapping(value="/by-breed/{breedName}",method = RequestMethod.GET)
    public ResponseEntity<List<DogPicture>> findPicturesByBreed(@PathVariable String breedName) {
        return ResponseEntity.ok(pictureService.findPicturesByBreed(breedName));
    }

    @RequestMapping(value = "/{pictureId}/vote-up",method = RequestMethod.POST)
    public ResponseEntity<String> voteUp(@PathVariable Long pictureId) {
        pictureService.voteUp(pictureId);
        return new ResponseEntity<>(String.format("The picture with id : %s has a new vote",pictureId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{pictureId}/vote-down",method = RequestMethod.POST)
    public ResponseEntity<String> voteDown(@PathVariable Long pictureId) {
        pictureService.voteDown(pictureId);
        return new ResponseEntity<>(String.format("The picture with id : %s has a new vote",pictureId),HttpStatus.OK);
    }
}