package org.neo4j.examples.imdb.domain;

import org.neo4j.graphdb.Relationship;
import org.springframework.datastore.graph.api.GraphRelationship;
import org.springframework.datastore.graph.api.GraphRelationshipEndNode;
import org.springframework.datastore.graph.api.GraphRelationshipStartNode;

@GraphRelationship(useShortNames = true)
class RoleImpl implements Role
{
    String role;
    @GraphRelationshipStartNode
    Actor actor;
    @GraphRelationshipEndNode
    Movie movie;
    @Override

    public String toString()
    {
        return String.format("%s-[%s]->%s", this.getActor(), role, this.getMovie());
    }

    public Actor getActor() {
        return actor;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public String getName() {
        return role;
    }

    @Override
    public void setName(String name) {
        this.role=name;
    }
}
