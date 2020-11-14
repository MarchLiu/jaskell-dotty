package jaskell.parsec

import scala.util.{Try, Success, Failure}

case class Eq[A](val x: A) extends Parsec[A, A] {
    def apply(state: State[A]): Try[A] = state.next() match {
        case Success(re) => {
            if(re == x){
                return Success(re)
            } else {
                return state.trap(s"except $x but $re")
            }
        }
        case Failure(e) => Failure(e)
    }
}