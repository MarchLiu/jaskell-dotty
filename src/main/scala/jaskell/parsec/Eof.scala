package jaskell.parsec

import scala.util.{Try, Success, Failure}

class Eof[E] extends Parsec[E, Unit] {
  /**
   * Eof success only state eof, else return failure
   * @param state parse state
   * @return a unit Try object
   */
  def apply(state: State[E]): Try[Unit] = state.next() match {
        case Success(re) => state.trap(s"except eof but $re")
        case Failure(_) => Success(())
    }
}

object Eof {
  def apply[E](): Eof[E] = new Eof()
}