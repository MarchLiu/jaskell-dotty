package jaskell.parsec

import scala.util.Try
import scala.language.implicitConversions

/**
 * check next content if a \n char or \r\n
 *
 * @author mars
 * @version 1.0.0
 */
class EndOfLine extends Parsec[Char, String] {
  val parsec: Parsec[Char, String] = Text("\n") <|> Text("\r\n")

  def apply(s: State[Char]): Try[String] = parsec ? s
}

object EndOfLine {
  def apply(): EndOfLine = new EndOfLine()
}