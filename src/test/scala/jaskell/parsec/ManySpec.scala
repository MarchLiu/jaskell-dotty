package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import jaskell.parsec.Txt._
import jaskell.parsec.Combinator._

import scala.util.{Success}
/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 19:40
 */
class ManySpec extends AnyFlatSpec with Matchers {
  import State.Instances.{given, *}
  import Parsec.Instances.{given, *}
  "Simple" should "Run match 2 times" in {
    val state = "HelloHello".state
    val parser = many(text("hello", caseSensitive = false))
    val re = parser(state)
    re.map(_.size) should be (Success(2))
    re.map(_.head) should be (Success("Hello"))
    re.map(_(1)) should be (Success("Hello"))
  }
}
