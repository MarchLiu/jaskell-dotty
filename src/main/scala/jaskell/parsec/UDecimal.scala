package jaskell.parsec

import scala.util.{Try, Success, Failure}
import scala.language.implicitConversions

case class UDecimal(val uint:UInt = UInt()) extends Parsec[Char, String] {
    val dot: Parsec[Char, Char] = Ch('.').attempt
    val tail: Parsec[Char, String] = dot *> uint
    def apply(state: State[Char]): Try[String] = {
        uint ? state map { value =>
            tail ? state match {
                case Failure(_) => value
                case Success(t) => s"$value.$t"
            }
        }
    }
}