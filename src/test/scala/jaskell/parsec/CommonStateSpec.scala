package jaskell.parsec

import java.io.EOFException

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.Success

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 21:52
 */
class CommonStateSpec extends AnyFlatSpec with Matchers{
  import State.Instances.{given, *}
  import Parsec.Instances.{given, *}
  
  "Index" should "Test index status" in {
    val data = "It is a \"string\" for this unit test";
    val state = data.state;
    while (state.status < data.length()){
      val index = state.status
      val c = state.next();
      val chr = data.charAt(index);
      c should be (Success(chr))
    }
    state.next().isFailure should be (true)
  }

  "Begin" should "Test begin tran and rollback then next" in {
    val state = "hello".state

    val c = state.next()


    c should be (Success('h'))

    val a = state.begin()

    state.next()
    state.next()
    state.next()

    state rollback a

    val d = state.next()

    d should be (Success('e'))
  }

  "Commit" should "Test begin a transaction and commit" in {
    val state = "hello".state
    val tran = state.begin()
    val c = state.next()


    c should be (Success('h'))
    state.next()

    state.commit(tran);

    val d = state.next();

    d should be (Success('l'))
  }

  "Rollback" should "Test rollback" in {
    val state = "hello".state;

    val tran = state.begin();
    val c = state.next();


    c should be (Success('h'))

    state rollback tran

    val d = state.next();

    d should be (Success('h'))
  }

  "Next" should "Test state next method" in {
    val state = "hello".state


    val c = state.next()

    c should be (Success('h'))

    val d = state.next()

    d should be (Success('e'))

    val e = state.next()

    e should be (Success('l'))

    val f = state.next()

    f should be (Success('l'))
    val g = state.next()

    g should be (Success('o'))
  }


}
