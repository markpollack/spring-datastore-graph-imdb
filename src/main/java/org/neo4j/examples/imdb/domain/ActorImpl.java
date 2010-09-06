package org.neo4j.examples.imdb.domain;

import org.springframework.datastore.graph.api.GraphEntity;
import org.springframework.datastore.graph.api.GraphEntityRelationship;

import java.util.Set;

@GraphEntity(useShortNames = true)
class ActorImpl implements Actor {
    private String name;

    @GraphEntityRelationship(type="ACTS_IN",elementClass = MovieImpl.class)
    private Set<Movie> movies;

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
        return (Role)getRelationshipTo((MovieImpl)inMovie, RoleImpl.class, RelTypes.ACTS_IN.name());
    }

    @Override
    public String toString() {
        return "Actor '" + this.getName() + "'";
    }
}
