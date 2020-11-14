package jaskell.parsec

import scala.util.{Try, Success, Failure}
import scala.collection.mutable

case class Count[E, T](val p: Parsec[E, T], val n: scala.Int) extends Parsec[E, Seq[T]] {

  def apply(s: State[E]): Try[Seq[T]] = {
    if (n <= 0) {
      return Success(Seq.empty)
    }
    val re = mutable.ListBuffer[T]()
    for (_ <- 0 to n) {
      p ? s match {
        case Success(value) => re += value
        case Failure(error) =>
          return Failure(error)
      }
    }
    Success(re.toSeq)
  }
}