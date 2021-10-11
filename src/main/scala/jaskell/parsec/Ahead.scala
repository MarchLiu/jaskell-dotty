package jaskell.parsec

import scala.util.Try

/**
 * Ahead use a parser to parse state, return the result and rollback whatever
 *
 * @author mars
 * @version 1.0.0
 */
case class Ahead[E, T](parser: Parsec[E, T]) extends Parsec[E, T] {

  def apply(s: State[E]): Try[T] = {
    val tran = s.begin()
    val result = parser ? s
    s.rollback(tran)
    result
  }
}

