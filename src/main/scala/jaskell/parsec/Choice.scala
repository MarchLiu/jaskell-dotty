package jaskell.parsec

import java.io.EOFException
import scala.util.{Failure, Success, Try}
import scala.util.control.NonLocalReturns.{returning, throwReturn}

/**
 * Choice just the operator <|> in Haskell parsec
 *
 * @author mars
 * @version 1.0.0
 */
case class Choice[E, T](parsecs: Seq[Parsec[E, T]]) extends Parsec[E, T] {

  override def apply(s: State[E]): Try[T] = returning {
    var err: Throwable = null
    val status = s.status
    for (psc <- this.parsecs) {
      psc ? s match {
        case right: Success[T] =>
          throwReturn(right)
        case Failure(error) =>
          err = error
          if (status != s.status) {
            throwReturn(Failure(error))
          }
      }
    }
    if (err == null) {
      s.trap("Choice Error : All parsec parsers failed.")
    } else {
      s.trap(s"Choice Error $err, stop at $status")
    }
  }
}
