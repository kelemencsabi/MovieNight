package org.fasttrackit.MovieNight.transfer.movie;

import javax.validation.constraints.NotNull;

public class SaveMovieRequest {
    @NotNull
    private String name;
    @NotNull
    private String description;
    private String imagePath;
    private int releaseYear;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "SaveMovieRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
