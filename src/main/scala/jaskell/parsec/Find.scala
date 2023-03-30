package jaskell.parsec

import java.io.EOFException
import scala.util.{Failure, Success, Try}
import scala.util.control.Breaks.*
import scala.util.control.NonLocalReturns.{returning, throwReturn}

/**
 * Find try and next util success or eof.
 *
 * @author mars
 * @version 1.0.0
 */
case class Find[E, T](val psc: Parsec[E, T]) extends Parsec[E, T] {

  def apply(s: State[E]): Try[T] = returning {
    var error: Exception = null
    while (true) {
      psc ? s match {
        case right: Success[_] =>
          throwReturn(right)
        case Failure(err: ParsecException) =>
          if (error == null) {
            error = err
          }
        case Failure(err) =>
          if (error == null) {
            throwReturn(Failure(err))
          } else {
            throwReturn(Failure(error))
          }
      }
    }
    s.trap("find parsec should not run to this!")
  }
}

object Find {
  def apply[E, T](psc: Parsec[E, T]): Find[E, T] = new Find(psc)
}
