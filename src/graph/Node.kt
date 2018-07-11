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

    private fun hopCount(destination: Node, visitedNodes: List<Node>): Int {
        if (this == destination) return 0
        if (visitedNodes.contains(this)) return unreachable
        return neighborHopCount(destination, visitedNodes)
    }

    private fun neighborHopCount(destination: Node, visitedNodes: List<Node>): Int {
        var champion = unreachable
        for (n in neighbors) {
            val challenger = n.hopCount(destination, visitedNodes + this)
            if (challenger == unreachable) continue
            if (champion == unreachable || challenger + 1 < champion) champion = challenger + 1
        }
        return champion
    }

    private val noVisitedNodes get() = listOf<Node>()
}