package jaskell.parsec

import scala.collection.mutable
import scala.util.{Failure, Success, Try}

/**
 * Many try to parse the parse more times, and collect all results into a Seq.
 * Many could success 0 to any times.
 *
 * @author mars
 */
class Many[E, T](val parsec: Parsec[E, T]) extends Parsec[E, Seq[T]] {

  def apply(s: State[E]): Try[Seq[T]] = {
    var re = new mutable.ListBuffer[T]
    for(item <- parsec iterate s){
      re append item
    }
    return Success(re.toSeq)
  }
}

object Many {
  def parse[E, T](parsec: Parsec[E, T]): Many[E, T] = new Many[E, T](parsec)
}
