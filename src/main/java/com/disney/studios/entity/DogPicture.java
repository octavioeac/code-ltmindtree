package com.disney.studios.entity;

import javax.persistence.*;

@Entity
@Table(name = "DogPicture")
public class DogPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private int favoriteCount;

    @ManyToOne
    @JoinColumn(name = "breed_id", nullable = false)
    private DogBreed breed;

    public DogPicture() {
    }

    public DogPicture(Long id, String url, int favoriteCount, DogBreed breed) {
        this.id = id;
        this.url = url;
        this.favoriteCount = favoriteCount;
        this.breed = breed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public DogBreed getBreed() {
        return breed;
    }

    public void setBreed(DogBreed breed) {
        this.breed = breed;
    }
}