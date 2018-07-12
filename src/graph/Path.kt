/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package graph

import kotlin.Double.Companion.POSITIVE_INFINITY

// Understands a way to get to a specific Node
abstract class Path {
    companion object {
        internal val leastCost = { p: Path? -> p?.cost ?: POSITIVE_INFINITY }
        internal val hopCount = { p: Path? -> p?.hopCount?.toDouble() ?: POSITIVE_INFINITY }
        internal val actual get() = ActualPath()
    }
    abstract val hopCount: Int
    abstract val cost: Double
    internal open fun prepend(link: Link) {}

    internal class ActualPath: Path() {

        private val links = mutableListOf<Link>()

        override fun prepend(link: Link) = links.add(0, link)

        override val hopCount get() = links.count()

        override val cost get() = Link.totalCost(links)
    }

    internal object None: Path() {

        override val hopCount get() = Int.MAX_VALUE

        override val cost get() = Double.POSITIVE_INFINITY
    }
}

internal typealias PathStrategy = (Path?) -> Double