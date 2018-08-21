package ca.cutterslade.experiment.gk

import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

class Greeter : GreeterGrpcKt.GreeterImplBase() {
  override fun sayHello(request: HelloRequest): Deferred<HelloReply> = async {
    HelloReply.newBuilder().setMessage("Hello ${request.name}").build()
  }

  override fun sayHelloAgain(request: HelloRequest): Deferred<HelloReply> = async {
    HelloReply.newBuilder().setMessage("Hello again ${request.name}").build()
  }
}
