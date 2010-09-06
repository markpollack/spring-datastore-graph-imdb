package org.neo4j.examples.imdb.domain;

public interface Actor
{
    /**
     * Returns this actors imdb-encoded name.
     * @return actor name
     */
    String getName();

    /**
     * Sets the actors name.
     * @param name
     *            name of actor
     */
    void setName( String name );

    /**
     * Returns all movies this actor acted in.
     * @return all movies
     */
    Iterable<Movie> getMovies();

    /**
     * Returns the specific role an actor had in a movie or null if actor didn't
     * have a role in the movie.
     * @param inMovie
     *            the movie to get role for
     * @return the role or null
     */
    Role getRole( Movie inMovie );
}
