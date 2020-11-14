package jaskell.expression.parsers

import jaskell.expression.{Expression, Product}
import jaskell.parsec.Txt.skipWhiteSpaces
import jaskell.parsec.{Parsec, SkipWhitespaces, State}

import scala.util.Try

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/02 21:44
 */
class P(val prev: Expression) extends Parsec[Char, Expression] {

  import jaskell.parsec.Txt.ch
  import jaskell.parsec.state

  val skips: SkipWhitespaces = skipWhiteSpaces
  val op: Parsec[Char, Unit] = skips *> ch('*') *> skips
  val next = new Parser

  def apply(s: State[Char]): Try[Expression] = {
    for {
      _ <- op ? s
      exp <- next ? s
    } yield {new Product(prev, exp)}
  }
}
