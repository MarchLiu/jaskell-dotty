package jaskell.parsec

import scala.util.{Try, Success, Failure}

case class UInt(val digit:Digit = new Digit()) extends Parsec[Char, String] {
    def apply(state: State[Char]): Try[String] = {
        (digit ? state) flatMap { first =>
            val builder = new StringBuilder()
            builder.append(first)
            val iter = digit.iterate(state)
            
            for (c <- iter) {
                builder.append(c)
            }

            Success(builder.toString)
        }
    }
}
