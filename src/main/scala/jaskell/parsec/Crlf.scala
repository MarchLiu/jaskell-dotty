package jaskell.parsec

import scala.util.{Try}

case class Crlf(val r: Ch = Ch('\r'), val n: Ch = Ch('\n')) extends Parsec[Char, String] {
    def apply(state: State[Char]): Try[String] = {
        for {
            _ <- r ? state
            _ <- n ? state
        } yield "\r\n"
    }
}