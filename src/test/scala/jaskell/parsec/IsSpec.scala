package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.Success

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 14:23
 */
class IsSpec extends AnyFlatSpec with Matchers {
  import State.Instances.{given, *}
  import Parsec.Instances.{given, *}
  "Is" should "get item if predicate passed" in {
    val parser = new Is[scala.Int]({number => (number % 2) == 0})
    val state = Seq(2).state

    (parser ? state) should be (Success(2))
  }

  "IsNot" should "failed when predicate not passed" in {
    val parser = new Is[scala.Int](number => number % 2== 1)
    val state = Seq(2).state

    (parser ? state).isFailure should be (true)
    state.current should be (1)
  }
}
