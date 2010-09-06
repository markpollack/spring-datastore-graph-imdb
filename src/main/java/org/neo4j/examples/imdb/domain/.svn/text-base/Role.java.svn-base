package org.neo4j.examples.imdb.domain;

/**
 * A role holds role information of an actor in a specific movie.
 */
public interface Role
{
    /**
     * Returns the role name. Ex: "Reeves, Keanu" acted in "Matrix, The (1999)"
     * as "Neo". This method would then return "Neo" as name.
     * @return name of this role or null if no role found
     */
    String getName();

    /**
     * Sets the role name.
     * @param name
     *            role name
     */
    void setName( String name );

    /**
     * Returns the movie this role is connected to.
     * @return movie for this role
     */
    Movie getMovie();

    /**
     * Returns the actor for this role.
     * @return actor for this role
     */
    Actor getActor();
}
