/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package unit

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import quantity.*

// Ensures Quantity operates correctly
class QuantityTest {

    @Test fun `equality of like units`() {
        assertEquals(8.tablespoons, 8.tablespoons)
        assertNotEquals(8.tablespoons, 6.tablespoons)
        assertNotEquals(8.tablespoons, "a string")
        assertNotEquals(8.tablespoons, null)
    }

    @Test fun `equality of unlike units`() {
        assertEquals(8.tablespoons, 0.5.cups)
        assertNotEquals(8.tablespoons, 8.teaspoons)
    }

    @Test fun `hash set support`() {
        assert(hashSetOf(8.tablespoons).contains(0.5.cups))
    }

    @Test fun `hash code`() {
        assertEquals(8.tablespoons.hashCode(), 0.5.cups.hashCode())
    }

    @Test fun arithmetic() {
        assertEquals(0.5.quarts, 6.tablespoons + 13.ounces)
        assertEquals((-6).tablespoons, -6.tablespoons)
        assertEquals(-0.5.pints, 10.tablespoons - 13.ounces)
    }
}