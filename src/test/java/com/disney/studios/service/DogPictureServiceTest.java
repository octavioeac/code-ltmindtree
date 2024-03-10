package com.disney.studios.service;

import com.disney.studios.entity.DogBreed;
import com.disney.studios.entity.DogPicture;
import com.disney.studios.repository.DogBreedRepository;
import com.disney.studios.repository.DogPictureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DogPictureServiceTest {
    @Mock
    private DogPictureRepository dogPictureRepository;

    @Mock
    private DogBreedRepository dogBreedRepository;

    @InjectMocks
    private DogPictureService dogPictureService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllPicturesGroupedByBreed() {
        DogBreed labrador = new DogBreed();
        labrador.setName("Labrador Retriever");
        DogPicture picture1 = new DogPicture();
        picture1.setId(1L);
        picture1.setUrl("https://example.com/picture1.jpg");
        picture1.setBreed(labrador);
        DogPicture picture2 = new DogPicture();
        picture2.setId(2L);
        picture2.setUrl("https://example.com/picture2.jpg");
        picture2.setBreed(labrador);
        List<DogPicture> allPictures = new ArrayList<>();
        allPictures.add(picture1);
        allPictures.add(picture2);
        when(dogPictureRepository.findAll()).thenReturn(allPictures);
        Map<String, List<DogPicture>> result = dogPictureService.findAllPicturesGroupedByBreed();
        assertEquals(1, result.size());
        assertEquals(allPictures, result.get("Labrador Retriever"));
    }

    @Test
    public void testFindPicturesByBreed() {
        String breedName = "Labrador Retriever";
        DogBreed labrador = new DogBreed();
        labrador.setName(breedName);
        DogPicture picture1 = new DogPicture();
        picture1.setId(1L);
        picture1.setUrl("https://example.com/picture1.jpg");
        picture1.setBreed(labrador);
        DogPicture picture2 = new DogPicture();
        picture2.setId(2L);
        picture2.setUrl("https://example.com/picture2.jpg");
        picture2.setBreed(labrador);
        List<DogPicture> pictures = new ArrayList<>();
        pictures.add(picture1);
        pictures.add(picture2);
        when(dogPictureRepository.findByBreedName(breedName)).thenReturn(pictures);
        List<DogPicture> result = dogPictureService.findPicturesByBreed(breedName);
        assertEquals(pictures, result);
    }

    @Test
   public void testVoteUp() {
        Long pictureId = 1L;
        DogPicture picture = new DogPicture();
        picture.setId(pictureId);
        picture.setFavoriteCount(2);
        when(dogPictureRepository.findById(pictureId)).thenReturn(java.util.Optional.of(picture));
        dogPictureService.voteUp(pictureId);
        assertEquals(3, picture.getFavoriteCount());
        verify(dogPictureRepository, times(1)).save(picture);
    }

    @Test
   public void testVoteDown() {
        Long pictureId = 1L;
        DogPicture picture = new DogPicture();
        picture.setId(pictureId);
        picture.setFavoriteCount(2);
        when(dogPictureRepository.findById(pictureId)).thenReturn(java.util.Optional.of(picture));
        dogPictureService.voteDown(pictureId);
        assertEquals(1, picture.getFavoriteCount());
        verify(dogPictureRepository, times(1)).save(picture);
    }
}
