/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package order

interface Orderable<T> {
    fun isBetterThan(other: T) : Boolean
}

fun <T: Orderable<T>> List<T>.best() : T? {
    if (this.isEmpty()) return null
    var champion = this[0]
    for (challenger : T in this)
        if (challenger.isBetterThan(champion)) champion = challenger
    return champion
}