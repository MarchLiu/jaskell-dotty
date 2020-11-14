package jaskell.parsec

import scala.collection.mutable
import scala.util.{Failure, Success, Try}

/**
 * sepBy p sep parses zero or more occurrences of p, separated by sep. Returns a list of values returned by p.
 *
 * @author Mars Liu
 * @version 1.0.0
 */
case class SepBy[E, T](val parsec: Parsec[E, T], val by: Parsec[E, _]) extends Parsec[E, Seq[T]] {
  val b: Parsec[E, _] = by.attempt
  val p: Parsec[E, T] = parsec.attempt
  val psc: Parsec[E, T] = b *> p

  def apply(s: State[E]): Try[Seq[T]] = {
    val re = new mutable.ListBuffer[T]
    p ? s map { head =>
      re += head
      for(item <- psc iterate s){
          re += item
      }
      re.toSeq
    }
  }
}

