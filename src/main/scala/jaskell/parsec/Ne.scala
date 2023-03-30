package jaskell.parsec

import scala.util.{Try, Success, Failure}

case class Ne[A](val x: A) extends Parsec[A, A] {

  /**
   * ne is not equals checker. return success with state's next element if it not equal prepared.
   * @param state parsec state
   * @return
   */
  def apply(state: State[A]): Try[A] = state.next().flatMap { re =>
    if (re != x) {
      Success(re)
    } else {
      state.trap(s"except something not $x")
    }
  }
}