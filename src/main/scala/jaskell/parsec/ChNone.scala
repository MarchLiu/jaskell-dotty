package jaskell.parsec

import scala.util.{Try, Success}
import scala.util.control.NonLocalReturns._

case class ChNone(content: String, caseSensitive: Boolean = true) extends Parsec[Char, Char] {
  val contentSet: Set[Char] = if (caseSensitive) content.toSet else content.toLowerCase.toSet

  def apply(s: State[Char]): Try[Char] = s.next() flatMap { c =>
    caseSensitive match {
      case true if (!(contentSet contains c)) => Success(c)
      case false if (!(contentSet contains c.toLower)) => Success(c)
      case _ => s.trap(s"expect any char none of $content (case sensitive $caseSensitive) but get $c")
    }
  }
}