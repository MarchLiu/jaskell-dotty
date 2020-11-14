package jaskell.parsec

import scala.util.{Try, Success}

case class Pack[E, T](val value: T) extends Parsec[E, T] {
    def apply(state: State[E]): Try[T] = Success(value)
}

object Pack {
    def apply[E, T](value: T): Pack[E, T] = new Pack(value)
}