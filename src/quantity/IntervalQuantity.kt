/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package quantity

import order.Orderable

// Understands a particular measurement
open class IntervalQuantity internal constructor(amount: Number, protected val unit: Unit)
        : Orderable<IntervalQuantity> {

    private val amount = amount.toDouble()

    override fun equals(other: Any?) = this === other || other is IntervalQuantity && this.equals(other)

    private fun equals(other: IntervalQuantity) = this.isCompatible(other) && this.amount == convertedAmount(other)

    override fun hashCode() = unit.hashCode(amount)

    private fun isCompatible(other: IntervalQuantity) = this.unit.isCompatible(other.unit)

    protected fun convertedAmount(other: IntervalQuantity): Double {
        return this.unit.convertedAmount(other.amount, other.unit)
    }

    operator fun unaryPlus() = this

    override fun isBetterThan(other: IntervalQuantity) = this.amount > convertedAmount(other)
}