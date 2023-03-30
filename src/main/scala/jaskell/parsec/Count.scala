package jaskell.parsec

import scala.util.{Failure, Success, Try}
import scala.collection.mutable
import scala.util.control.NonLocalReturns.{returning, throwReturn}

case class Count[E, T](p: Parsec[E, T], n: scala.Int) extends Parsec[E, Seq[T]] {

  def apply(s: State[E]): Try[Seq[T]] = returning {
    if (n <= 0) {
      throwReturn(Success(Seq.empty))
    }

    val re = mutable.ListBuffer[T]()
    for (_ <- 0 to n) {
      p ? s match {
        case Success(value) => re += value
        case Failure(error) =>
          throwReturn(Failure(error))
      }
    }
    Success(re.toSeq)
  }
}