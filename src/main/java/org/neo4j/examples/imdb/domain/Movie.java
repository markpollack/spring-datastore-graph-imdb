package org.neo4j.examples.imdb.domain;

public interface Movie
{
    /**
     * Returns the title of this movie.
     * @return title of this movie.
     */
    String getTitle();

    /**
     * Set the title of this movie.
     * @param title
     *            title of movie
     */
    void setTitle( String title );

    /**
     * Returns the year this movie was released.
     * @return the year this movie was released
     */
    int getYear();

    /**
     * Set the year of this movie.
     * @param year
     *            year of movie
     */
    void setYear( int year );

    /**
     * Returns all actors that acted in this movie.
     * @return actors that acted in this movie
     */
    Iterable<Actor> getActors();
}
