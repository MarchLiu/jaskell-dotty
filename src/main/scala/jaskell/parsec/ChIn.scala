package jaskell.parsec

import scala.util.{Try, Success}
import scala.util.control.NonLocalReturns.*

case class ChIn(content: String, caseSensitive: Boolean = false) extends Parsec[Char, Char]:
  val contentSet: Set[Char] = if (caseSensitive) content.toSet else content.toLowerCase.toSet

  def apply(s: State[Char]): Try[Char] =
    s.next().flatMap { c =>
      caseSensitive match {
        case true if(contentSet contains c) => Success(c)
        case false if (contentSet contains c.toLower) => Success(c)
        case _ =>
          s.trap(s"expect any char in $content (case sensitive $caseSensitive) but get $c")
      }
    }

