package org.neo4j.examples.imdb.domain;

import org.neo4j.graphdb.Relationship;

class RoleImpl implements Role
{
    private static final String ROLE_PROPERTY = "role";

    private final Relationship underlyingRel;

    RoleImpl( final Relationship rel )
    {
        this.underlyingRel = rel;
    }

    Relationship getUnderlyingRelationship()
    {
        return this.underlyingRel;
    }

    public Actor getActor()
    {
        return new ActorImpl( underlyingRel.getStartNode() );
    }

    public Movie getMovie()
    {
        return new MovieImpl( underlyingRel.getEndNode() );
    }

    public String getName()
    {
        return ( String ) underlyingRel.getProperty( ROLE_PROPERTY, null );
    }

    public void setName( String name )
    {
        underlyingRel.setProperty( ROLE_PROPERTY, name );
    }

    public boolean equals( Object otherRole )
    {
        if ( otherRole instanceof RoleImpl )
        {
            return this.underlyingRel.equals( ((RoleImpl) otherRole)
                .getUnderlyingRelationship() );
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return this.underlyingRel.hashCode();
    }

    @Override
    public String toString()
    {
        String role = this.getName();
        if ( role == null )
        {
            role = "";
        }
        return this.getActor() + "-[" + role + "]->" + this.getMovie();
    }
}
