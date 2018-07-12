/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package graph

// Understands a connection to a specific Node
class Link(private val cost: Double, private val target: Node) {
    companion object {
        internal val leastCost = { cost: Double -> cost }
        internal val fewestHops = { _: Double -> 1.0 }
        internal fun totalCost(links: List<Link>) = links.sumByDouble { it.cost }
    }

    internal fun cost(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy): Double {
        return target.cost(destination, visitedNodes, strategy) + strategy(cost)
    }

    internal fun path(destination: Node, visitedNodes: List<Node>): Path? {
        return target.path(destination, visitedNodes)?.apply { this.prepend(this@Link) }
    }
}

internal typealias CostStrategy = (Double) -> Double