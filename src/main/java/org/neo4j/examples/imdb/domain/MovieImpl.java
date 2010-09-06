package org.neo4j.examples.imdb.domain;

import org.springframework.datastore.graph.api.Direction;
import org.springframework.datastore.graph.api.GraphEntity;
import org.springframework.datastore.graph.api.GraphEntityRelationship;

import java.util.Set;

@GraphEntity(useShortNames = true)
class MovieImpl implements Movie {
    String title;
    int year;

    @GraphEntityRelationship(type="ACTS_IN",elementClass = ActorImpl.class, direction = Direction.INCOMING)
    Set<Actor> actors;

    public Iterable<Actor> getActors() {
        return actors;
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", getTitle(), getYear());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
