package org.neo4j.examples.imdb.domain;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

class MovieImpl implements Movie
{
    private static final String TITLE_PROPERTY = "title";
    private static final String YEAR_PROPERTY = "year";

    private final Node underlyingNode;

    MovieImpl( final Node node )
    {
        this.underlyingNode = node;
    }

    Node getUnderlyingNode()
    {
        return this.underlyingNode;
    }

    public String getTitle()
    {
        return (String) underlyingNode.getProperty( TITLE_PROPERTY );
    }

    public void setTitle( final String title )
    {
        underlyingNode.setProperty( TITLE_PROPERTY, title );
    }

    public int getYear()
    {
        return (Integer) underlyingNode.getProperty( YEAR_PROPERTY );
    }

    public void setYear( final int year )
    {
        underlyingNode.setProperty( YEAR_PROPERTY, year );
    }

    public Iterable<Actor> getActors()
    {
        final List<Actor> actors = new LinkedList<Actor>();
        for ( Relationship rel : underlyingNode.getRelationships(
            RelTypes.ACTS_IN, Direction.INCOMING ) )
        {
            actors.add( new ActorImpl( rel.getStartNode() ) );
        }
        return actors;
    }

    @Override
    public boolean equals( final Object otherMovie )
    {
        if ( otherMovie instanceof MovieImpl )
        {
            return this.underlyingNode.equals( ((MovieImpl) otherMovie)
                .getUnderlyingNode() );
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return this.underlyingNode.hashCode();
    }

    @Override
    public String toString()
    {
        return (String) underlyingNode.getProperty( TITLE_PROPERTY );
    }
}
