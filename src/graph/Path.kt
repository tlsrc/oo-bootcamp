/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package graph

// Understands a way to get to a specific Node
class Path {
    private val links = mutableListOf<Link>()

    internal fun prepend(link: Link) = links.add(0, link)

    val hopCount get() = links.count()

    val cost get() = Link.totalCost(links)
}