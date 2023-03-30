package jaskell.parsec

import scala.util.{Try, Success, Failure}

class NCh(val char: Char, val caseSensitive: Boolean = true) extends Parsec[Char, Char] {
  val chr: Char = if (caseSensitive) char else char.toLower

  def apply(s: State[Char]): Try[Char] = {
    s.next() flatMap { c =>
      caseSensitive match {
        case true if (chr != c) => Success(c)
        case false if (chr != c.toLower) => Success(c)
        case _ =>
          s.trap(s"expect char $char (case sensitive $caseSensitive) but get $c")
      }
    }
  }
}