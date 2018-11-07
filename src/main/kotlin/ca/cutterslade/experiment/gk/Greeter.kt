package ca.cutterslade.experiment.gk

class Greeter : GreeterGrpcKt.GreeterImplBase() {
  override suspend fun sayHello(request: HelloRequest): HelloReply {
    return HelloReply.newBuilder().setMessage("Hello ${request.name}").build()
  }

  override suspend fun sayHelloAgain(request: HelloRequest): HelloReply {
    return HelloReply.newBuilder().setMessage("Hello again ${request.name}").build()
  }
}
