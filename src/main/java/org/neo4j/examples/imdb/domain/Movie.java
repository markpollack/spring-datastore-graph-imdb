package org.neo4j.examples.imdb.domain;

import org.springframework.datastore.graph.api.Direction;
import org.springframework.datastore.graph.api.GraphEntity;
import org.springframework.datastore.graph.api.GraphEntityProperty;
import org.springframework.datastore.graph.api.GraphEntityRelationship;

import java.util.Set;

@GraphEntity
public class Movie {
    @GraphEntityProperty(index = true)
    String title;
    int year;

    @GraphEntityRelationship(type="ACTS_IN",elementClass = Actor.class, direction = Direction.INCOMING)
    Set<Actor> actors;
    static final String TITLE_INDEX = "title";

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
