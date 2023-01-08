package org.priyank

import zioConsole.ZIO

object runtime {
  def unsafeRunSync[E, A](zio: => ZIO[E, A]): Either[E, A] = {
    zio.thunk()
  }
}
