package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.{Success}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 20:10
 */
class OneOfSpec extends AnyFlatSpec with Matchers{
  import State.Instances.{given, *}
  import Parsec.Instances.{given, *}
  "Simple" should "Just success" in {
    val state = "hello".state;

    val oof = OneOf(Seq('b', 'e', 'h', 'f').toSet);

    val c = oof(state);
    c should be (Success('h'))
  }

  "Failed" should "Fail" in {
    val state = "hello".state
    val oof = OneOf(Seq('d', 'a', 't', 'e').toSet)
    assert(oof(state).isFailure)
  }
}
