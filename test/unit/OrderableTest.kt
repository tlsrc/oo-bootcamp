/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package unit

import chance.Chance
import order.best
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import rectangle.Rectangle

internal class OrderableTest {

    @Test fun `rectangle with largest area`() {
        assertEquals(24.0, listOf(
                Rectangle(2.0, 3.0),
                Rectangle(4.0, 6.0),
                Rectangle(3.0, 3.0)).best()!!.area)
        assertNull(emptyList<Rectangle>().best())
    }

    @Test fun `most likely chance`() {
        assertEquals(Chance(0.75), listOf(
                Chance(0.5), Chance(0.75), Chance(0.25)).best())
        assertNull(emptyList<Chance>().best())
    }
}