/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package unit

import chance.Chance
import chance.chance
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

// Confirms that Chance operates correctly
internal class ChanceTest {
    private val certain = 1.chance
    private val likely = 0.75.chance
    private val equallyLikely = 0.5.chance
    private val unlikely = 0.25.chance
    private val impossible = 0.chance

    @Test fun equality() {
        assertEquals(likely, Chance(0.75))
        assertNotEquals(likely, unlikely)
        assertNotEquals(likely, "a string")
        assertNotEquals(likely, null)
    }

    @Test fun `hash set support`() {
        assert(hashSetOf(likely).contains(Chance(0.75)))
    }

    @Test fun `hash code`() {
        assertEquals(likely.hashCode(), Chance(0.75).hashCode())
    }

    @Test fun not() {
        assertEquals(unlikely, likely.not())
        assertEquals(likely, likely.not().not())
        assertEquals(unlikely, !likely)
        assertEquals(likely, !!likely)
        assertEquals(impossible, certain.not())
    }

    @Test fun and() {
        assertEquals(unlikely, equallyLikely.and(equallyLikely))
        assertEquals(Chance(0.1875), likely.and(unlikely))
        assertEquals(likely.and(unlikely), unlikely.and(likely))
        assertEquals(impossible, likely.and(impossible))
        assertEquals(likely, certain.and(likely))
    }

    @Test fun or() {
        assertEquals(likely, equallyLikely.or(equallyLikely))
        assertEquals(Chance(0.8125), likely.or(unlikely))
        assertEquals(likely.or(unlikely), unlikely.or(likely))
        assertEquals(likely, likely.or(impossible))
        assertEquals(certain, certain.or(likely))
    }

    @Test fun `invalid fractions`() {
        assertFailsWith(IllegalArgumentException::class) { Chance(1.01)}
        assertFailsWith(IllegalArgumentException::class) { Chance(-0.01)}
    }
}