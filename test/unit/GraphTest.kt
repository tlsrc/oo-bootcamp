/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package unit

import graph.Node
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

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
        b.to(a)
        b.to(c).to(d).to(e).to(b).to(f)
        c.to(d)
        c.to(e)
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
}