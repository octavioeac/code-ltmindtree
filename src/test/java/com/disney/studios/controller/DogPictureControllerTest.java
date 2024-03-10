package com.disney.studios.controller;


import com.disney.studios.entity.DogPicture;
import com.disney.studios.service.DogPictureService;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DogPictureControllerTest {


    @Mock
    private DogPictureService pictureService;

    private DogPictureController dogPictureController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dogPictureController = new DogPictureController(pictureService);
    }

    @Test
    public void testFindAllPicturesGroupedByBreed() {
        Map<String, List<DogPicture>> groupedPictures = new HashMap<>();
        List<DogPicture> pictures = new ArrayList<>();
        DogPicture picture = new DogPicture();
        picture.setId(1L);
        picture.setUrl("https://example.com/picture1.jpg");
        pictures.add(picture);
        groupedPictures.put("Labrador Retriever", pictures);
        when(pictureService.findAllPicturesGroupedByBreed()).thenReturn(groupedPictures);
        ResponseEntity<Map<String, List<DogPicture>>> response = dogPictureController.findAllPicturesGroupedByBreed();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(groupedPictures, response.getBody());
    }


    @Test
    public void testFindPicturesByBreed() {
        String breedName = "Labrador Retriever";
        List<DogPicture> pictures = new ArrayList<>();
        DogPicture picture = new DogPicture();
        picture.setId(1L);
        picture.setUrl("https://example.com/picture1.jpg");
        pictures.add(picture);
        when(pictureService.findPicturesByBreed(breedName)).thenReturn(pictures);
        ResponseEntity<List<DogPicture>> response = dogPictureController.findPicturesByBreed(breedName);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pictures, response.getBody());
    }

    @Test
   public void testVoteUp() {
        Long pictureId = 1L;
        ResponseEntity<String> response = dogPictureController.voteUp(pictureId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("The picture with id : " + pictureId + " has a new vote", response.getBody());
        verify(pictureService).voteUp(pictureId);
    }

    @Test
    public void testVoteDown() {
        Long pictureId = 1L;
        ResponseEntity<String> response = dogPictureController.voteDown(pictureId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("The picture with id : " + pictureId + " has a new vote", response.getBody());
        verify(pictureService).voteDown(pictureId);
    }




}




