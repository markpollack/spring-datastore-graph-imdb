package org.neo4j.examples.imdb.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ReturnableEvaluator;
import org.neo4j.graphdb.StopEvaluator;
import org.neo4j.graphdb.TraversalPosition;
import org.neo4j.graphdb.Traverser;
import org.neo4j.graphdb.Traverser.Order;

public class SimplePathFinder implements PathFinder
{
    private static final int MAXIMUM_DEPTH = 5;

    public List<Node> shortestPath( final Node startNode, final Node endNode,
        final RelationshipType relType )
    {
        return findPath( startNode, endNode, relType );
    }

    private List<Node> findPath( final Node startNode, final Node endNode,
        final RelationshipType relType )
    {
        final Map<Node,Node> traversedNodes1 = new HashMap<Node,Node>();
        final Map<Node,Node> traversedNodes2 = new HashMap<Node,Node>();
        final StopEvaluator stopEval = new PathStopEval();
        final PathReturnEval returnEval1 = new PathReturnEval( traversedNodes1,
            traversedNodes2 );
        final PathReturnEval returnEval2 = new PathReturnEval( traversedNodes2,
            traversedNodes1 );
        final Traverser trav1 = startNode.traverse( Order.BREADTH_FIRST,
            stopEval, returnEval1, relType, Direction.BOTH );
        final Traverser trav2 = endNode.traverse( Order.BREADTH_FIRST,
            stopEval, returnEval2, relType, Direction.BOTH );
        Iterator<Node> itr1 = trav1.iterator();
        Iterator<Node> itr2 = trav2.iterator();
        while ( itr1.hasNext() || itr2.hasNext() )
        {
            if ( itr1.hasNext() )
            {
                itr1.next();
            }
            if ( returnEval1.getMatch() != null )
            {
                List<Node> path = returnEval1.getMatch();
                Collections.reverse( path );
                return path;
            }
            if ( itr2.hasNext() )
            {
                itr2.next();
            }
            if ( returnEval2.getMatch() != null )
            {
                return returnEval2.getMatch();
            }
        }
        return Collections.emptyList();
    }

    private static class PathStopEval implements StopEvaluator
    {
        public boolean isStopNode( TraversalPosition currentPos )
        {
            return currentPos.depth() >= MAXIMUM_DEPTH;
        }
    }

    private static class PathReturnEval implements ReturnableEvaluator
    {
        private final Map<Node,Node> myNodes;
        private final Map<Node,Node> otherNodes;
        private LinkedList<Node> match = null;

        public PathReturnEval( final Map<Node,Node> myNodes,
            final Map<Node,Node> otherNodes )
        {
            this.myNodes = myNodes;
            this.otherNodes = otherNodes;
        }

        public boolean isReturnableNode( TraversalPosition currentPos )
        {
            Node currentNode = currentPos.currentNode();
            Node prevNode = currentPos.previousNode();
            if ( !otherNodes.containsKey( currentNode ) )
            {
                myNodes.put( currentNode, prevNode );
            }
            else
            {
                match = new LinkedList<Node>();
                match.add( currentNode );
                while ( prevNode != null )
                {
                    match.add( prevNode );
                    prevNode = myNodes.get( prevNode );
                }
                Node otherNode = otherNodes.get( currentNode );
                while ( otherNode != null )
                {
                    match.addFirst( otherNode );
                    otherNode = otherNodes.get( otherNode );
                }
            }
            return true;
        }

        protected List<Node> getMatch()
        {
            return match;
        }
    }
}