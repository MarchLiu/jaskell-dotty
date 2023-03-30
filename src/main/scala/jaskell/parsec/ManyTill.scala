package jaskell.parsec

import java.io.EOFException
import scala.collection.mutable
import scala.util.control.NonLocalReturns.*
import scala.util.{Failure, Success, Try}

/**
 * manyTill p end applies parser p zero or more times until parser end succeeds. Returns the list of
 * values returned by p. This parser can be used to scan comments.
 *
 * @author Mars Liu
 * @version 1.0.0
 */
class ManyTill[E, T, L](val parser: Parsec[E, T], val end: Parsec[E, L]) extends Parsec[E, Seq[T]] {
  val psc = new Attempt[E, T](parser)
  val till = new Attempt[E, L](end)

  def apply(s: State[E]): Try[Seq[T]] = returning {
    val re = new mutable.ListBuffer[T]
    while (true)  {
      till ? s match {
        case Success(_) => throwReturn(Success(re.toSeq))
        case Failure(error: EOFException) => throwReturn(Failure(error))
        case Failure(_: ParsecException) =>
          psc ? s match {
            case Success(value) => re += value
            case Failure(e) => throwReturn(Failure(e))
          }
        case Failure(e) => throwReturn(Failure(e))
      }
    }
    throwReturn(Success(re.toSeq))
  }
}

object ManyTill {
  def parse[E, T, L](parser: Parsec[E, T], end: Parsec[E, L]): ManyTill[E, T, L] = new ManyTill(parser, end)
}