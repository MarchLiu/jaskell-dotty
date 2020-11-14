package jaskell.parsec

import scala.util.{Try, Failure}

trait State[E] {
    type Index
    type Status
    type Tran
    def next(): Try[E]
    def status: Status
    def begin(): Tran
    def commit(tran: Tran): Unit
    def rollback(tran: Tran): Unit
    def trap[T](message: String): Failure[T] = {
        Failure(new ParsecException(this.status, message))
    }
    def pack[T](item: T): Parsec[E, T] = new Pack(item)

    def eof: Parsec[E, Unit] = new Eof
}

trait Config {}

given stateConfig as Config {
    extension [Char](txt: String) 
        def state: TxtState = new TxtState(txt)

    extension [E](content: Seq[E])
        def state: CommonState[E] = new CommonState[E](content)
}