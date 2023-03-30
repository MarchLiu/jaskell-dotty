package jaskell.parsec

import scala.collection.mutable
import scala.util.{Failure, Success, Try}

/**
 * Many try to parse the parse more times, and collect all results into a Seq.
 * Many could success 0 to any times.
 *
 * @author mars
 */
case class Many[E, T](parsec: Parsec[E, T]) extends Parsec[E, Seq[T]]:
  def apply(s: State[E]): Try[Seq[T]] =
    val re = new mutable.ListBuffer[T]
    for(item <- parsec iterate s){
      re append item
    }
    Success(re.toSeq)

