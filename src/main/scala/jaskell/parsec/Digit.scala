package jaskell.parsec

import scala.util.{Try, Success, Failure}

class Digit extends Parsec[Char, Char] {
    def apply(state: State[Char]): Try[Char] = state.next().flatMap { re =>
        if(re.isDigit){
            return Success(re)
        } else {
            return state.trap(s"Expect $re is digit")
        }
    }
}