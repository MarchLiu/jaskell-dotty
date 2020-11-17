package jaskell.parsec
import scala.util.{Try, Success, Failure}
import scala.language.implicitConversions
import jaskell.Monad

trait Parsec[A, B] {
    def apply(state: State[A]): Try[B]

    def ?(state: State[A]): Try[B] = this.apply(state)

    def !(state: State[A]): B = this.apply(state).get

    // def <|> (p: Parsec[A, B]): Parsec[A, B] = Choice(this, p)

    def attempt: Parsec[A, B] = new Attempt(this)

    def iterate(state: State[A]): Iterator[A, B] = new Iterator(this, state)

    def <|> (p: Parsec[A, B]): Parsec[A, B] = new Combine2(this, p)
}

given parsecConfig[E] as Monad[[T] =>> Parsec[E, T]] {
    def pure[A](x: A): Parsec[E, A] = new Pack[E, A](x)

    extension [A, B](x: Parsec[E, A]) {
        def flatMap(f: A => Parsec[E, B]): Parsec[E, B] = new Binder(x, f)
    }
}