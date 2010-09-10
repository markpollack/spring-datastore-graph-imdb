package org.neo4j.examples.imdb.domain;

import org.springframework.datastore.graph.api.GraphRelationship;
import org.springframework.datastore.graph.api.GraphRelationshipEndNode;
import org.springframework.datastore.graph.api.GraphRelationshipStartNode;

@GraphRelationship
public class Role {
    String role;
    @GraphRelationshipStartNode
    Actor actor;
    @GraphRelationshipEndNode
    Movie movie;

    @Override

    public String toString() {
        return String.format("%s-[%s]->%s", this.getActor(), role, this.getMovie());
    }

    public Actor getActor() {
        return actor;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getName() {
        return role;
    }

    public void setName(String name) {
        this.role = name;
    }
}
