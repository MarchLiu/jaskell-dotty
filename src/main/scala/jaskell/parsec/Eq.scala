package jaskell.parsec

import scala.util.{Try, Success, Failure}

case class Eq[A](val x: A) extends Parsec[A, A] {
  /**
   * eq check next element from state if equals the prepared.
   * @param state
   * @return
   */
  def apply(state: State[A]): Try[A] = state.next() match {
        case Success(re) => {
            if(re == x){
                Success(re)
            } else {
                state.trap(s"except $x but $re")
            }
        }
        case Failure(e) => Failure(e)
    }
}