/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package quantity

// Understands a particular measurement
class Quantity internal constructor(amount: Number, private val unit: Unit) {

    private val amount = amount.toDouble()

    override fun equals(other: Any?): Boolean {
        return this === other || other is Quantity && this.equals(other)
    }

    private fun equals(other: Quantity) = this.amount == convertedAmount(other)

    override fun hashCode(): Int {
        return unit.hashCode(amount)
    }

    private fun convertedAmount(other: Quantity) = this.unit.convertedAmount(other.amount, other.unit)


}