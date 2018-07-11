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

    fun canReach(destination: Node) = canReach(destination, noVisitedNodes)

    private fun canReach(destination: Node, visitedNodes: MutableList<Node>): Boolean {
        if (this == destination) return true
        if (visitedNodes.contains(this)) return false
        visitedNodes.add(this)
        return neighbors.any { it.canReach(destination, visitedNodes) }
    }

    fun hopCount(destination: Node): Int {
        val result = hopCount(destination, noVisitedNodes)
        if (result == unreachable) throw IllegalArgumentException("Unreachable destination")
        return result
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