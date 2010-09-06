package org.neo4j.examples.imdb.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;
import org.neo4j.helpers.Predicate;
import org.neo4j.kernel.Traversal;
import org.neo4j.kernel.impl.traversal.TraversalDescriptionImpl;

public class SimplePathFinder implements PathFinder {
	private static final int MAXIMUM_DEPTH = 5;

	public List<Node> shortestPath(final Node startNode, final Node endNode,
			final RelationshipType relType) {
		return findPath(startNode, endNode, relType);
	}

	private List<Node> findPath(final Node startNode, final Node endNode,
			final RelationshipType relType) {
		final Map<Node, Node> traversedNodes1 = new HashMap<Node, Node>();
		final Map<Node, Node> traversedNodes2 = new HashMap<Node, Node>();
		final PathReturnEval returnEval1 = new PathReturnEval(traversedNodes1,
				traversedNodes2);
		final PathReturnEval returnEval2 = new PathReturnEval(traversedNodes2,
				traversedNodes1);
		TraversalDescription td1 = new TraversalDescriptionImpl()
				.order(Traversal.postorderBreadthFirst())
				.prune(Traversal.pruneAfterDepth(MAXIMUM_DEPTH))
				.filter(returnEval1)
				.expand(Traversal.expanderForTypes(relType, Direction.BOTH));
		final Traverser trav1 = td1
				.traverse(startNode);
		TraversalDescription td2 = new TraversalDescriptionImpl()
				.order(Traversal.postorderBreadthFirst())
				.prune(Traversal.pruneAfterDepth(MAXIMUM_DEPTH))
				.filter(returnEval2)
				.expand(Traversal.expanderForTypes(relType, Direction.BOTH));
		final Traverser trav2 = td2
				.traverse(endNode);
		Iterator<Node> itr1 = trav1.nodes().iterator();
		Iterator<Node> itr2 = trav2.nodes().iterator();
		while (itr1.hasNext() || itr2.hasNext()) {
			if (itr1.hasNext()) {
				itr1.next();
			}
			if (returnEval1.getMatch() != null) {
				List<Node> path = returnEval1.getMatch();
				Collections.reverse(path);
				return path;
			}
			if (itr2.hasNext()) {
				itr2.next();
			}
			if (returnEval2.getMatch() != null) {
				return returnEval2.getMatch();
			}
		}
		return Collections.emptyList();
	}

	private static class PathReturnEval implements Predicate<Path> {
		private final Map<Node, Node> myNodes;
		private final Map<Node, Node> otherNodes;
		private LinkedList<Node> match = null;

		public PathReturnEval(final Map<Node, Node> myNodes,
				final Map<Node, Node> otherNodes) {
			this.myNodes = myNodes;
			this.otherNodes = otherNodes;
		}

		public boolean accept(Path currentPos) {
			Node currentNode = currentPos.endNode();
			Node prevNode = currentPos.lastRelationship().getOtherNode(
					currentNode);
			if (!otherNodes.containsKey(currentNode)) {
				myNodes.put(currentNode, prevNode);
			} else {
				match = new LinkedList<Node>();
				match.add(currentNode);
				while (prevNode != null) {
					match.add(prevNode);
					prevNode = myNodes.get(prevNode);
				}
				Node otherNode = otherNodes.get(currentNode);
				while (otherNode != null) {
					match.addFirst(otherNode);
					otherNode = otherNodes.get(otherNode);
				}
			}
			return true;
		}

		protected List<Node> getMatch() {
			return match;
		}

	}
}