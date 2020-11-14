package jaskell.expression.parsers

import jaskell.expression.Expression
import jaskell.parsec.{Ahead, Attempt, Ch, Eof, Parsec, SkipWhitespaces, State}

import scala.util.{Failure, Success, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/02 21:45
 */
class Parser extends Parsec[Char, Expression] {

  import jaskell.parsec.Txt.{skipWhiteSpaces, ch}
  import jaskell.parsec.Combinator.{ahead}
  import jaskell.parsec.Atom.eof

  val rq: Parsec[Char, Char] = ch(')').attempt
  val skips: SkipWhitespaces = skipWhiteSpaces
  val e: Eof[Char] = eof

  val end: Ahead[Char, Unit] = ahead(new Parsec[Char, Unit]{
    def apply(s: State[Char]):Try[Unit] = {
    rq ? s match {
      case Failure(_) =>
        e ? s
      case Success(_) =>
        Success(())
    }
  }})

  override def apply(s: State[Char]): Try[Expression] = {
    val np = ((new Num).attempt <|> (new Param).attempt <|> new Q).attempt

    np ? s flatMap { left =>
      (for {
        _ <- skips ? s
        e <- end ? s
      } yield {
        e
      }) match {
        case Success(_) => Success(left)
        case Failure(_) =>
          val next = 
            (new A(left)).attempt <|> (new S(left)).attempt <|> (new P(left)).attempt <|> new D(left).attempt
          next ? s
      }
    }
  }
}
