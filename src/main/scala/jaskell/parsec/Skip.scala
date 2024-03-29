package jaskell.parsec
import scala.language.postfixOps
import scala.util.{Try, Success, Failure}

/**
 * Skip p applies the parser p zero or more times, skipping its result.
 *
 * @author mars
 * @version 1.0.0
 */
case class Skip[E](psc: Parsec[E, _]) extends Parsec[E, Unit]:
  val p: Attempt[E, _] = Attempt(psc)
  def apply(s: State[E]): Try[Unit] = {
    for(_ <- p iterate s){}

    Success(())
  }


