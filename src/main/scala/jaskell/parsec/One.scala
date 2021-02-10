package jaskell.parsec

import scala.util.{Try, Success}

class One[A] extends Parsec[A, A] {
  /**
   * one parser get next item from state, failed when eof.
   * @param state
   * @return
   */
  def apply(state: State[A]): Try[A] = state.next()
}