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

    fun cost(amount: Number): LinkBuilder {
        return LinkBuilder(amount, links)
    }

    fun canReach(destination: Node) = hopCount(destination, noVisitedNodes) != unreachable

    fun hopCount(destination: Node): Int {
        return cost(destination, noVisitedNodes, Link.fewestHops).apply {
            if (this == unreachable) throw IllegalArgumentException("Unreachable destination")}.toInt()
    }

    internal fun hopCount(destination: Node, visitedNodes: List<Node>): Double {
        if (this == destination) return 0.0
        if (visitedNodes.contains(this)) return unreachable
        return links
                .map { it.hopCount(destination, visitedNodes + this) }
                .min() ?: unreachable
    }

    fun cost(destination: Node): Double {
        return cost(destination, noVisitedNodes, Link.leastCost).apply {
            if (this == unreachable) throw IllegalArgumentException("Unreachable destination")}
    }

    internal fun cost(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy): Double {
        if (this == destination) return 0.0
        if (visitedNodes.contains(this)) return unreachable
        return links
                .map { it.cost(destination, visitedNodes + this, strategy) }
                .min() ?: unreachable
    }

    private val noVisitedNodes get() = listOf<Node>()

    class LinkBuilder internal constructor(private val cost: Number, private val links: MutableList<Link>) {
        fun to(target: Node): Node {
            links.add(Link(cost.toDouble(), target))
            return target
        }
    }
}