package jaskell.parsec

import scala.util.{Try, Success}

case class ChIn(content: String, caseSensitive: Boolean = false) extends Parsec[Char, Char]:
  val contentSet: Set[Char] = if (caseSensitive) content.toSet else content.toLowerCase.toSet

  def apply(s: State[Char]): Try[Char] = s.next() flatMap { c =>
      if (caseSensitive) {
        if (contentSet contains c) {
          return Success(c)
        }
      } else {
        if (contentSet contains c.toLower) {
          return Success(c)
        }
      }
      s.trap(s"expect any char in $content (case sensitive $caseSensitive) but get $c")
    }
  