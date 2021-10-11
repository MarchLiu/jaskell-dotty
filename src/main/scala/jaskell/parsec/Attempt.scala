package jaskell.parsec

import scala.util.{Try, Success, Failure}

case class Attempt[E, T](parsec: Parsec[E, T]) extends Parsec[E, T]{
    def apply(state: State[E]): Try[T] = {
        val tran = state.begin()
        
        parsec(state) match {
            case result: Success[_] =>
                state commit tran
                result
            case failed: Failure[_] =>
                state rollback tran
                failed
        }
    }
}