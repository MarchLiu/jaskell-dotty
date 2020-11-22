package jaskell.expression.parsers

import jaskell.expression.{Expression, Quote}
import jaskell.parsec.{Parsec, SkipWhitespaces, State}

import scala.util.Try

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/03 12:13
 */
class Q extends Parsec[Char, Expression]:
  import jaskell.parsec.Combinator.between
  import jaskell.parsec.Txt.{skipWhiteSpaces, ch}
  import jaskell.parsec.parsecConfig

  lazy val p = new Parser
  val skips: SkipWhitespaces = skipWhiteSpaces
  val parser: Parsec[Char, Expression] = 
    between(ch('(') *> skips, skips *> ch(')'), p)

  def apply(s: State[Char]): Try[Expression] = 
    parser ? s map {new Quote(_)}

