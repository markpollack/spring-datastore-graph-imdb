package org.neo4j.examples.imdb.domain;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

class ActorImpl implements Actor
{
    private static final String NAME_PROPERTY = "name";

    private final Node underlyingNode;

    ActorImpl( final Node node )
    {
        this.underlyingNode = node;
    }

    Node getUnderlyingNode()
    {
        return this.underlyingNode;
    }

    public final String getName()
    {
        return (String) underlyingNode.getProperty( NAME_PROPERTY );
    }

    public void setName( final String name )
    {
        underlyingNode.setProperty( NAME_PROPERTY, name );
    }

    public Iterable<Movie> getMovies()
    {
        final List<Movie> movies = new LinkedList<Movie>();
        for ( Relationship rel : underlyingNode.getRelationships(
            RelTypes.ACTS_IN, Direction.OUTGOING ) )
        {
            movies.add( new MovieImpl( rel.getEndNode() ) );
        }
        return movies;
    }

    public Role getRole( final Movie inMovie )
    {
        final Node movieNode = ((MovieImpl) inMovie).getUnderlyingNode();
        for ( Relationship rel : underlyingNode.getRelationships(
            RelTypes.ACTS_IN, Direction.OUTGOING ) )
        {
            if ( rel.getEndNode().equals( movieNode ) )
            {
                return new RoleImpl( rel );
            }
        }
        return null;
    }

    @Override
    public boolean equals( final Object otherActor )
    {
        if ( otherActor instanceof ActorImpl )
        {
            return this.underlyingNode.equals( ((ActorImpl) otherActor)
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
        return "Actor '" + this.getName() + "'";
    }
}
