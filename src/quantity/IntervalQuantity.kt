/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package quantity

// Understands a particular measurement
class IntervalQuantity internal constructor(amount: Number, private val unit: Unit) {

    private val amount = amount.toDouble()

    override fun equals(other: Any?) = this === other || other is IntervalQuantity && this.equals(other)

    private fun equals(other: IntervalQuantity) = this.isCompatible(other) && this.amount == convertedAmount(other)

    override fun hashCode() = unit.hashCode(amount)

    private fun isCompatible(other: IntervalQuantity) = this.unit.isCompatible(other.unit)

    private fun convertedAmount(other: IntervalQuantity): Double {
        return this.unit.convertedAmount(other.amount, other.unit)
    }

    operator fun unaryPlus() = this
}