package jaskell.parsec
import scala.util.Try
import scala.language.implicitConversions

class Decimal extends Parsec[Char, String]:
  val sign: Parsec[Char, String] = Attempt(Text("-")) <|> Pack("")
  val uDecimal: UDecimal = UDecimal()

  override def apply(st: State[Char]): Try[String] = {
    for {
      s <- sign ? st
      num <- uDecimal ? st
    } yield s + num
  }