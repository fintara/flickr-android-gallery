package com.tsovedenski.flickrgallery.common

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
sealed class Either <out L, out R> {
    data class Left <out L> (val value: L) : Either<L, Nothing>()
    data class Right <out R> (val value: R) : Either<Nothing, R>()

    inline fun <T> map(
        crossinline f: (R) -> T
    ): Either<L, T> = when (this) {
        is Left -> this
        is Right -> Right(f(value))
    }

    inline fun <T> mapLeft(
        crossinline f: (L) -> T
    ): Either<T, R> = when (this) {
        is Left -> Left(f(value))
        is Right -> this
    }

    inline fun <T> fold(
        crossinline left: (L) -> T,
        crossinline right: (R) -> T
    ): T = when (this) {
        is Left -> left(value)
        is Right -> right(value)
    }
}

typealias Try <T> = Either<Exception, T>

@Suppress("FunctionName")
suspend inline fun <T> Try(crossinline action: suspend () -> T) = try {
    Either.Right(action())
} catch (e: Exception) {
    Either.Left(e)
}