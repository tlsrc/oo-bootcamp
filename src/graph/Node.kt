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

    fun cost(amount: Number) = LinkBuilder(amount, links)

    fun canReach(destination: Node) = cost(destination, noVisitedNodes, Link.leastCost) != unreachable

    fun hopCount(destination: Node) = cost(destination, Link.fewestHops).toInt()

    fun cost(destination: Node) = cost(destination, Link.leastCost)

    private fun cost(destination: Node, strategy: CostStrategy): Double {
        return cost(destination, noVisitedNodes, strategy).apply {
            if (this == unreachable) throw IllegalArgumentException("Unreachable destination")}
    }

    internal fun cost(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy): Double {
        if (this == destination) return 0.0
        if (visitedNodes.contains(this)) return unreachable
        return links
                .map { it.cost(destination, visitedNodes + this, strategy) }
                .min() ?: unreachable
    }

    fun path(destination: Node): Path {
        return path(destination, noVisitedNodes, Path.leastCost).apply {
            if (this == null) throw IllegalArgumentException("Unreachable destination")}!!
    }

    internal fun path(destination: Node, visitedNodes: List<Node>, strategy: PathStrategy): Path? {
        if (this == destination) return Path()
        if (visitedNodes.contains(this)) return null
        return links
                .map { it.path(destination, visitedNodes + this, strategy) }
                .minBy { strategy(it) }
    }

    private val noVisitedNodes get() = listOf<Node>()

    class LinkBuilder internal constructor(private val cost: Number, private val links: MutableList<Link>) {
        fun to(target: Node): Node {
            links.add(Link(cost.toDouble(), target))
            return target
        }
    }
}