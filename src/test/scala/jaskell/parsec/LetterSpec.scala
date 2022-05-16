package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.Success

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/12 18:57
 */
class LetterSpec extends AnyFlatSpec with Matchers {
  import State.Instances.{given, *}
  import Parsec.Instances.{given, *}
  import Txt._

  "Letter" should "test a char is letter" in {
    val content = "a".state
    val parser = letter

    parser ? content should be (Success('a'))
  }

  "no" should "test a char is letter" in {
    val content = "22".state
    val parser = letter

    (parser ? content).isFailure should be (true)
  }

}
