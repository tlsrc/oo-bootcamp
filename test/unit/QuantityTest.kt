/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package unit

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import quantity.Quantity

// Ensures Quantity operates correctly
class QuantityTest {

    @Test fun `equality of like units`() {
        assertEquals(Quantity(8, Quantity.tablespoon), Quantity(8, Quantity.tablespoon))
        assertNotEquals(Quantity(8, Quantity.tablespoon), Quantity(6, Quantity.tablespoon))
        assertNotEquals(Quantity(8, Quantity.tablespoon), "a string")
        assertNotEquals(Quantity(8, Quantity.tablespoon), null)
    }
    
    @Test fun `equality of unlike units`() {
        assertNotEquals(Quantity(8, Quantity.tablespoon), Quantity(8, Quantity.teaspoon))
    }
}