package org.fasttrackit.MovieNight.transfer.tvShow;

public class GetTvShowsRequest {
    public String partialName;
    public Integer releaseYear;

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "GetTvShowsRequest{" +
                "partialName='" + partialName + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
