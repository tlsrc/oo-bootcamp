/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package unit

import org.junit.jupiter.api.Test
import rectangle.Rectangle
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

// Ensures Rectangle operates correctly
internal class RectangleTest {

    @Test fun area() {
        assertEquals(24.0, Rectangle(4, 6).area)
    }

    @Test fun perimeter() {
        assertEquals(20.0, Rectangle(4, 6).perimeter())
    }

    @Test fun `invalid dimensions`() {
        assertFailsWith(IllegalArgumentException::class) { Rectangle (0, 6) }
        assertFailsWith(IllegalArgumentException::class) { Rectangle (4, 0) }
    }

}