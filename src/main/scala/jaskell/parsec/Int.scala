package jaskell.parsec

import scala.util.{Try, Success}

case class Int(val sign:Parsec[Char, Char] = Ch('-').attempt, 
                val uint: Parsec[Char, String] = new UInt()) extends Parsec[Char, String] {
    
    def apply(state: State[Char]):Try[String] = {
        val buffer = new StringBuilder()
        val s = sign ? state

        s foreach {buffer.append(_)}

        (uint ? state).flatMap { n =>
            buffer.append(n)
            Success(buffer.toString)
        }

    }
}
