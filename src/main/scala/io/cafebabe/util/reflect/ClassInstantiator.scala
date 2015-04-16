package io.cafebabe.util.reflect

import java.lang.reflect.Constructor

import scala.reflect._

/**
 * Utility to create class instances using reflection.
 *
 * @author Vladimir Konstantinov
 * @version 1.0 (4/16/2015)
 */
object ClassInstantiator {

  private val defaults = Map[Class[_], AnyRef](
    classOf[Byte] -> java.lang.Byte.valueOf(0.toByte),
    classOf[Short] -> java.lang.Short.valueOf(0.toShort),
    classOf[Int] -> java.lang.Integer.valueOf(0),
    classOf[Long] -> java.lang.Long.valueOf(0L),
    classOf[Float] -> java.lang.Float.valueOf(0F),
    classOf[Double] -> java.lang.Double.valueOf(0D),
    classOf[Boolean] -> java.lang.Boolean.valueOf(false)
  )

  private def defaultParametersFor(constructor: Constructor[_]): Seq[AnyRef] = {
    constructor.getParameterTypes.map(defaults.getOrElse(_, null))
  }

  /**
   * Creates new class instance with default constructor parameters.
   * @tparam T Class to be instantiated.
   * @return New class instance.
   */
  def instanceOf[T: ClassTag]: T = {
    val clazz = classTag[T].runtimeClass
    require(clazz.getConstructors.length > 0, s"Class ${clazz.getName} should have at least one public constructor.")
    try {
      val defaultConstructor = clazz.getConstructor()
      defaultConstructor.newInstance().asInstanceOf[T]
    } catch {
      case e: NoSuchMethodException =>
        val constructor = clazz.getConstructors()(0)
        constructor.newInstance(defaultParametersFor(constructor): _*).asInstanceOf[T]
    }
  }
}
