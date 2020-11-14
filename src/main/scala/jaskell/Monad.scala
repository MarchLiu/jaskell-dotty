package jaskell
import scala.util.{Try, Success, Failure}

trait Monad[F[_]] extends Applicative[F] {

  extension [A, B](x: F[A]) {

    def >>= (f: A=> F[B]): F[B] = {
      x.flatMap(f)
    } 

    /** The `map` operation can now be defined in terms of `flatMap` */
    def map(f: A => B) = x.flatMap(f.andThen(pure))

    def >> (f: A => B) = map(f)
  }
}

given listMonad as Monad[List] {
  def pure[A](x: A): List[A] =
    List(x)
  extension [A, B](xs: List[A])
    def flatMap(f: A => List[B]): List[B] =
      xs.flatMap(f) // rely on the existing `flatMap` method of `List`
}

given optionMonad as Monad[Option]:
  def pure[A](x: A): Option[A] =
    Option(x)
  extension [A, B](xo: Option[A])
    def flatMap(f: A => Option[B]): Option[B] = xo match
      case Some(x) => f(x)
      case None => None

type Reuslt[A] = Either[Exception, A]

given tryMonad as Monad[Try]:
  def pure[A](x: A) = Success(x)
  extension [A, B](xt: Try[A])
      def flatMap(f: A => Try[B]): Try[B] = xt.flatMap(f)