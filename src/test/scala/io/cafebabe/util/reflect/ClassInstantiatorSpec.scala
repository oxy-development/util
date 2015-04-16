package io.cafebabe.util.reflect

import org.scalatest._

class ClassInstantiatorSpec extends FlatSpec with Matchers {

  import ClassInstantiator._
  import ClassInstantiatorTestData._

  "Instantiator" should "instantiate class with default values" in {
    instanceOf[TestCaseClass] should be (TestCaseClass(null, 0, flag = false))
  }

  it should "use default constructor if possible" in {
    instanceOf[SeveralConstructors].name should be ("default")
  }

  it should "throw IllegalArgumentException if there are no public constructors" in {
    a [IllegalArgumentException] should be thrownBy {
      instanceOf[PrivateConstructors]
    }
  }
}

object ClassInstantiatorTestData {

  case class TestCaseClass(name: String, value: Short, flag: Boolean)

  class PrivateConstructors private (name: String)

  class SeveralConstructors(val name: String) {
    def this(value: Integer) = this(value.toString)
    def this() = this("default")
  }
}
