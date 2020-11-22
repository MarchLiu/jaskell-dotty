package jaskell.parsec

import scala.util.{Try, Success, Failure}

case class Ch(val char: Char, val caseSensitive: Boolean=true) extends Parsec[Char, Char]:
  val chr: Char = if (caseSensitive) char else char.toLower

  def apply(s: State[Char]): Try[Char] =
    s.next() flatMap { c =>
      if (caseSensitive) {
        if (chr == c) {
          return Success(c)
        }
      } else {
        if (chr == c.toLower) {
          return Success(c)
        }
      }
      s.trap(s"expect char $char (case sensitive $caseSensitive) but get $c")
    }
