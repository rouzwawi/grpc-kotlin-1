package ca.cutterslade.experiment.gk

import io.grpc.ManagedChannelBuilder
import io.grpc.ServerBuilder
import kotlinx.coroutines.runBlocking


fun main(args: Array<String>) {
  val server = ServerBuilder
      .forPort(0)
      .addService(Greeter())
      .build()
      .also { it.start() }
  val channel = ManagedChannelBuilder
      .forAddress("localhost", server.port)
      .usePlaintext()
      .build()

  val greeter = GreeterGrpcKt.newStub(channel)

  runBlocking {
    val response = greeter.sayHello(HelloRequest.newBuilder().setName("coroutines").build())
    println(response.message)

    val responseAgain = greeter.sayHelloAgain(HelloRequest.newBuilder().setName("kotlin").build())
    println(responseAgain.message)
  }

  server.shutdown()
}
