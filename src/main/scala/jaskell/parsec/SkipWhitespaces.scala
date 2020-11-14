package jaskell.parsec

import scala.util.{Success, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 */
class SkipWhitespaces extends Parsec[Char, Unit] {
  val parsec = new Skip[Char](new Whitespace())

  def apply(s: State[Char]): Try[Unit] = for {_ <- parsec ? s} yield ()
}

object SkipWhitespaces {
  def apply(): SkipWhitespaces = new SkipWhitespaces()
}