/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package graph

// Understands a connection to a specific Node
class Link(private val cost: Double, private val target: Node) {
    companion object {
        internal fun totalCost(links: List<Link>) = links.sumByDouble { it.cost }
    }

    internal fun path(destination: Node, visitedNodes: List<Node>, strategy: PathStrategy): Path {
        return target.path(destination, visitedNodes, strategy).apply { this.prepend(this@Link) }
    }
}