package jaskell.parsec

import scala.collection.mutable
import scala.util.control.NonLocalReturns.{returning, throwReturn}
import scala.util.{Failure, Success, Try}

/**
 * SepBy1 p sep parses one or more occurrences of p, separated by sep. Returns a list of values returned by p.
 *
 * @author mars
 * @version 1.0.0
 */
case class SepBy1[E, T](parsec: Parsec[E, T], by: Parsec[E, _]) extends Parsec[E, Seq[T]] {
  import Parsec.Instances.{given, *}

  val b = new Attempt(by)
  val p = new Attempt[E, T](parsec)
  val psc: Parsec[E, T] = b *> p

  def apply(s: State[E]): Try[Seq[T]] = returning {
    val re = new mutable.ListBuffer[T]
    parsec ? s map { head =>
      re += head
      for (item <- psc iterate s){
        re += item
      }
      throwReturn(Success(re.toSeq))
    }
  }
}

