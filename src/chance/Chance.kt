/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package chance

// Understands the likelihood of something occurring
class Chance(likelihoodAsFraction: Number) {
    private val fraction = likelihoodAsFraction.toDouble()

    override fun equals(other: Any?): Boolean {
        return this === other || other is Chance && this.equals(other)
    }

    private fun equals(other: Chance) = this.fraction == other.fraction

    override fun hashCode() = fraction.hashCode()
}