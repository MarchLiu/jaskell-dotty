package jaskell.parsec

import scala.collection.mutable
import scala.util.{Failure, Success, Try}

/**
 * Many try to parse the parse more times, and collect all results into a Seq.
 * Many must success once at lest
 *
 * @author Mars Liu
 * @version 1.0.0
 */
class Many1[E, T](val parsec: Parsec[E, T]) extends Parsec[E, Seq[T]]:
  def apply(s: State[E]): Try[Seq[T]] = {
    val re = new mutable.ListBuffer[T]
    parsec ? s match {
      case Success(value) => re += value
      case Failure(e) => return Failure(e)
    }

    for(item <- parsec iterate s) {
        re += item
    }
    Success(re.toSeq)
  }

object Many1:
  def parse[E, T](parsec: Parsec[E, T]): Many1[E, T] = new Many1[E, T](parsec)

