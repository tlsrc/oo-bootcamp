/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package quantity

import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

// Understands a particular measurement
class Quantity(amount: Number, private val unit: Any?) {
    companion object {
        val teaspoon = Object()
        val tablespoon = Object()
        val ounce = Object()
        val cup = Object()
        val pint = Object()
        val quart = Object()
        val gallon = Object()
    }

    private val amount = amount.toDouble()

    override fun equals(other: Any?): Boolean {
        return this === other || other is Quantity && this.equals(other)
    }

    private fun equals(other: Quantity): Boolean {
        return this.amount == other.amount && this.unit == other.unit
    }
}