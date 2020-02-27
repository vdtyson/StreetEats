package com.versilistyson.androidstreeteats.domain.common

// https://github.com/android10/Android-CleanArchitectureKotlin/blob/master/app/src/main/kotlin/com/fernandocejas/sample/core/functional/Either.kt
/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Either] are either an instance of [Left] or [Right].
 * FP Convention dictates that [Left] is used for "failure"
 * and [Right] is used for "success".
 *
 * @see Left
 * @see Right
 */
sealed class Either<out L, out R> {
    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class Left<out L>(val left: L): Either<L, Nothing>()

    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class Right<out R>(val right: R): Either<Nothing, R>()

    /**
     * Returns true if this is a Right, false otherwise.
     * @see Right
     */
    val isRight get(): Boolean = this is Right<R>

    /**
     * Returns true if this is a Left, false otherwise.
     * @see Left
     */
    val isLeft get(): Boolean = this is Left<L>

    /**
     * Creates a Left type.
     * @see Left
     */
    fun <L> left(left: L): Left<L> =
        Left(left)

    /**
     * Creates a Left type.
     * @see Right
     */
    fun <R> right(right: R): Right<R> =
        Right(right)

    /**
     * Applies fnL if this is a Left or fnR if this is a Right.
     * @see Left
     * @see Right
     */

    fun fold(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when(this) {
            is Left -> fnL(left)
            is Right -> fnR(right)
        }
}