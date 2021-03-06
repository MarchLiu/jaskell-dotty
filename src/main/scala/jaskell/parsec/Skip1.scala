package jaskell.parsec

import scala.util.{Success, Try}
import scala.language.implicitConversions

/**
 * Skip1 p applies the parser p one or more times, skipping its result.
 *
 * @author mars
 * @version 1.0.0
 */
class Skip1[E](val psc: Parsec[E, _]) extends Parsec[E, Unit]:
  val skip = new Skip(psc)
  val parser: Parsec[E, _] = psc *> skip

  def apply(s: State[E]): Try[Unit] = 
    (parser ? s).flatMap(_ => Success(()))

object Skip1:
  def parse[E](psc: Parsec[E, _]): Skip1[E] = new Skip1[E](psc)