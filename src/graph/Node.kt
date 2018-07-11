/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package graph

// Understands its neighbors
class Node {
    private val unreachable = Double.POSITIVE_INFINITY
    private val links = mutableListOf<Link>()

    fun to(neighbor: Node): Node {
        links.add(Link(neighbor))
        return neighbor
    }

    fun canReach(destination: Node) = hopCount(destination, noVisitedNodes) != unreachable

    fun hopCount(destination: Node): Int {
        return hopCount(destination, noVisitedNodes).apply {
            if (this == unreachable) throw IllegalArgumentException("Unreachable destination")}.toInt()
    }

    internal fun hopCount(destination: Node, visitedNodes: List<Node>): Double {
        if (this == destination) return 0.0
        if (visitedNodes.contains(this)) return unreachable
        return links
                .map { it.hopCount(destination, visitedNodes + this) }
                .min() ?: unreachable
    }

    private val noVisitedNodes get() = listOf<Node>()
}