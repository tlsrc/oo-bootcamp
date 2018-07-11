/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package graph

// Understands its neighbors
class Node {
    private val unreachable = -1
    private val neighbors = mutableListOf<Node>()

    fun to(neighbor: Node): Node {
        neighbors.add(neighbor)
        return neighbor
    }

    fun canReach(destination: Node) = hopCount(destination, noVisitedNodes) != unreachable

    fun hopCount(destination: Node): Int {
        return hopCount(destination, noVisitedNodes).apply {
            if (this == unreachable) throw IllegalArgumentException("Unreachable destination")}
    }

    private fun hopCount(destination: Node, visitedNodes: MutableList<Node>): Int {
        if (this == destination) return 0
        if (visitedNodes.contains(this)) return unreachable
        for (n in neighbors) {
            val neighborHopCount = n.hopCount(destination, visitedNodes)
            if (neighborHopCount != unreachable) return neighborHopCount + 1
        }
        return unreachable
    }

    private val noVisitedNodes get() = mutableListOf<Node>()
}