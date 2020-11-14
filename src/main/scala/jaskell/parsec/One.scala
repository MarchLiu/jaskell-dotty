package jaskell.parsec

import scala.util.{Try, Success}

class One[A] extends Parsec[A, A] {
    def apply(state: State[A]): Try[A] = state.next()
}