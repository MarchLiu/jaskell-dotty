package jaskell.parsec

import scala.util.{Try, Success, Failure}
import scala.util.control.NonLocalReturns.returning

class Digit extends Parsec[Char, Char] {
    def apply(state: State[Char]): Try[Char] = state.next().flatMap { re =>
        if(re.isDigit){
            Success(re)
        } else {
            state.trap(s"Expect $re is digit")
        }
    }
}