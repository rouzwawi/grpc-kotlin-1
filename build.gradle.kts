import com.google.protobuf.gradle.GenerateProtoTask
import org.jetbrains.kotlin.gradle.dsl.Coroutines

repositories { jcenter() }

plugins {
  idea
  application
  kotlin("jvm") version "1.2.61"
  id("com.google.protobuf") version "0.8.6"
}

group = "ca.cutterslade.experiment"

application { mainClassName = "ca.cutterslade.experiment.gk.MainKt" }

dependencies {
  compile(kotlin("stdlib-jdk8"))
  compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:0.24.0")
  compile("com.google.protobuf:protobuf-java:3.6.1")
  compile("io.grpc:grpc-stub:1.14.0")
  compile("io.grpc:grpc-protobuf:1.14.0")
  compile("javax.annotation:javax.annotation-api:1.3.2")

  runtime("io.grpc:grpc-netty:1.14.0")
}

protobuf {
  withGroovyBuilder {
    "protobuf" {
      "protoc" {
        "setArtifact"("com.google.protobuf:protoc:3.6.1")
      }
      "plugins" {
        "create"("grpc-kotlin") {
          "setArtifact"("io.rouz:grpc-kotlin-gen:0.0.2:jdk8@jar")
        }
        "create"("grpc-java") {
          "setArtifact"("io.grpc:protoc-gen-grpc-java:1.14.0")
        }
      }
    }
  }
  tasks.withType<GenerateProtoTask> {
    plugins.create("grpc-kotlin")
    plugins.create("grpc-java")
  }
}
kotlin {
  experimental.coroutines = Coroutines.ENABLE
}
