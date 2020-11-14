package jaskell.parsec

import scala.util.{Try}

case class Cons[A, B, C](val prev:Parsec[A, B], val next: Parsec[A, C]) extends Parsec[A, C] {
    def apply(state: State[A]): Try[C] = for {
        _ <- prev(state)
        b <- next(state)
    } yield b
}