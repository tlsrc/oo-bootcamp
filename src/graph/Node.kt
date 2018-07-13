/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package graph

// Understands its neighbors
class Node {
    private val links = mutableListOf<Link>()

    fun cost(amount: Number) = LinkBuilder(amount, links)

    fun canReach(destination: Node) = path(destination, noVisitedNodes, Path.leastCost) != Path.None

    fun hopCount(destination: Node) = path(destination, Path.hopCount).hopCount

    fun cost(destination: Node) = path(destination).cost

    fun path(destination: Node) = path(destination, Path.leastCost)

    fun paths(destination: Node) = paths(destination, noVisitedNodes)

    internal fun paths(destination: Node, visitedNodes: List<Node>): List<Path> {
        if (this == destination) return listOf(Path.actual)
        if (visitedNodes.contains(this)) return emptyList()
        return links.flatMap { it.paths(destination, visitedNodes + this) }
    }

    private fun path(destination: Node, strategy: PathStrategy): Path {
        return path(destination, noVisitedNodes, strategy).apply {
                if (this == Path.None) throw IllegalArgumentException("Unreachable destination") }
    }

    internal fun path(destination: Node, visitedNodes: List<Node>, strategy: PathStrategy): Path {
        if (this == destination) return Path.actual
        if (visitedNodes.contains(this)) return Path.None
        return links
                .map { it.path(destination, visitedNodes + this, strategy) }
                .minBy { strategy(it) } ?: Path.None
    }

    private val noVisitedNodes get() = listOf<Node>()

    class LinkBuilder internal constructor(private val cost: Number, private val links: MutableList<Link>) {
        fun to(target: Node): Node {
            links.add(Link(cost.toDouble(), target))
            return target
        }
    }
}