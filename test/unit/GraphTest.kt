/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package unit

import graph.Node
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

// Ensure algorithms on directed graphs operate correctly
class GraphTest {
    private val a = Node()
    private val b = Node()
    private val c = Node()
    private val d = Node()
    private val e = Node()
    private val f = Node()
    private val g = Node()

    init {
        b.cost(5).to(a)
        b.cost(6).to(c).cost(7).to(d).cost(2).to(e).cost(3).to(b).cost(4).to(f)
        c.cost(1).to(d)
        c.cost(8).to(e)
    }

    @Test fun `can reach`() {
        assert(b.canReach(b))
        assert(b.canReach(a))
        assert(b.canReach(f))
        assert(b.canReach(d))
        assert(c.canReach(f))
        assertFalse(g.canReach(b))
        assertFalse(a.canReach(b))
        assertFalse(b.canReach(g))
    }

    @Test fun `hop count`() {
        assertEquals(0, b.hopCount(b))
        assertEquals(1, b.hopCount(a))
        assertEquals(1, b.hopCount(f))
        assertEquals(2, b.hopCount(d))
        assertEquals(3, c.hopCount(f))
        assertFailsWith(IllegalArgumentException::class) { g.hopCount(b) }
        assertFailsWith(IllegalArgumentException::class) { a.hopCount(b) }
        assertFailsWith(IllegalArgumentException::class) { b.hopCount(g) }
    }

    @Test fun cost() {
        assertEquals(0.0, b.cost(b))
        assertEquals(5.0, b.cost(a))
        assertEquals(4.0, b.cost(f))
        assertEquals(7.0, b.cost(d))
        assertEquals(10.0, c.cost(f))
        assertFailsWith(IllegalArgumentException::class) { g.cost(b) }
        assertFailsWith(IllegalArgumentException::class) { a.cost(b) }
        assertFailsWith(IllegalArgumentException::class) { b.cost(g) }
    }

    @Test fun path() {
        assertPath(a, a, 0, 0)
        assertPath(b, a, 1, 5)
        assertPath(b, f, 1, 4)
        assertPath(b, d, 2, 7)
        assertPath(c, f, 4, 10)
        assertFailsWith(IllegalArgumentException::class) { g.path(b) }
        assertFailsWith(IllegalArgumentException::class) { a.path(b) }
        assertFailsWith(IllegalArgumentException::class) { b.path(g) }
    }

    private fun assertPath(source: Node, destination: Node, expectedHopCount: Int, expectedCost: Number) {
        val p = source.path(destination)
        assertEquals(expectedHopCount, p.hopCount)
        assertEquals(expectedCost.toDouble(), p.cost)
    }
}