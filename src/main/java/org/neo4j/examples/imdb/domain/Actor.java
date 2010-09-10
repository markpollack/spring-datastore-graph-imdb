package org.neo4j.examples.imdb.domain;

import org.springframework.datastore.graph.api.GraphEntity;
import org.springframework.datastore.graph.api.GraphEntityProperty;
import org.springframework.datastore.graph.api.GraphEntityRelationship;

import java.util.Set;

@GraphEntity
public class Actor {
    @GraphEntityProperty(index = true)
    private String name;

    @GraphEntityRelationship(type="ACTS_IN",elementClass = Movie.class)
    private Set<Movie> movies;
    static final String NAME_INDEX = "name";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Iterable<Movie> getMovies() {
        return movies;
    }

    public Role getRole(final Movie inMovie) {
        return (Role)getRelationshipTo((Movie)inMovie, Role.class, RelTypes.ACTS_IN.name());
    }

    @Override
    public String toString() {
        return "Actor '" + this.getName() + "'";
    }
}
