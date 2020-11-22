package jaskell.parsec

import scala.util.Try
import scala.language.implicitConversions

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 */
class ScNumber extends Parsec [Char, String]:
  val decimal = new Decimal
  val exp: Parsec[Char, String] = (new Parsec[Char, String] {
    val ep: Text = Text("e", caseSensitive = false)
    val sp: Parsec[Char, String] = Text("+").attempt <|> Text("-").attempt <|> Pack("")
    val np: UInt = new jaskell.parsec.UInt
    def apply(st: State[Char]): Try[String] = {
      for {
        e <- ep ? st
        s <- sp ? st
        n <- np ? st
      } yield {e + s + n}
    }
  }).attempt

  def apply(s: State[Char]): Try[String] = 
    decimal ? s map  { mantissa =>
      exp ? s map {e => mantissa + e} getOrElse mantissa
    }
