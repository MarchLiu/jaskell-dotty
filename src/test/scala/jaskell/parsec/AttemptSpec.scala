package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import jaskell.parsec.Atom.{eqs}

import scala.util.Success

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 22:33
 */
class AttemptSpec extends AnyFlatSpec with Matchers {

  import State.Instances.{given, *}
  import Parsec.Instances.{given, *}

  val data = Seq("Hello", "World")
  "Simple" should "Run a simple test" in {

    val state: CommonState[String] = data.state
    val idx = state.status
    val tryIt: Parsec[String, String] = eqs("Hello").attempt

    val re = tryIt
    ? state

    re should be(Success("Hello"))
    idx should not be (state.status)
  }

  "Fail" should "Run a failed test" in {
    val state: State[String] = data.state
    val idx = state.status
    val tryIt: Parsec[String, String] = (new Eq("hello")).attempt

    assert((tryIt apply state).isFailure)

    idx should be(state.status)

    val tryIti = (s: State[String]) => {
      s.next() flatMap { content =>
        if (content.toLowerCase == "hello") {
          Success(content)
        } else {
          s.trap(f"expect a word match [Hello] and case insensitive but get [${content}]")
        }
      }
    }

    val re = tryIti(state)
    re should be(Success("Hello"))
  }
}
