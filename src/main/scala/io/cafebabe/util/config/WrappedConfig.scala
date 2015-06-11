package io.cafebabe.util.config

import com.typesafe.config._

import scala.collection.JavaConversions._
import scala.concurrent.duration.{Duration, FiniteDuration}
import java.util.concurrent.TimeUnit

/**
 * @author Vladimir Konstantinov
 * @author Yuriy Gintsyak
 * @version 1.0 (6/10/2015)
 */
class WrappedConfig(config: Config) {

  // Internal
  @inline
  private [this] def lookupValue[V](path:String, f:(String)=>V):Option[V] = {
    if (config.hasPath(path)) Option(f(path))
    else None
  }

  @inline
  private [this] def lookupIterable[V](path:String, f:(String) => java.util.List[V]):Iterable[V] = {

    lookupValue(path, f) match {
      case Some(x) => x.toIterable
      case None => Iterable.empty
    }
  }

  // Value retrieval
  def findBoolean(path: String):Option[Boolean] = lookupValue(path, config.getBoolean)
  def findNumber(path: String): Option[Number] = lookupValue(path, config.getNumber)
  def findInt(path: String): Option[Int] = lookupValue(path, config.getInt)
  def findLong(path: String): Option[Long] = lookupValue(path, config.getLong)
  def findDouble(path: String): Option[Double] = lookupValue(path, config.getDouble)
  def findString(path: String): Option[String] = lookupValue(path, config.getString)
  def findObject(path: String): Option[ConfigObject] = lookupValue(path, config.getObject)
  def findConfig(path: String): Option[Config] = lookupValue(path, config.getConfig)
  def findValue(path: String): Option[ConfigValue] = lookupValue(path, config.getValue)
  def findBytes(path: String): Option[Long] = lookupValue(path, config.getBytes) // ?? o_O
  def findMemorySize(path: String): Option[ConfigMemorySize] = lookupValue(path, config.getMemorySize)

  def findDuration(path: String): Option[Duration] = findString(path).map(Duration(_))
  def findFiniteDuration(path: String): Option[FiniteDuration] =
    findDuration(path).map(duration => FiniteDuration(duration.toNanos, TimeUnit.NANOSECONDS))

  // Collection retrieval
  def findList(path: String): Option[ConfigList] = lookupValue(path, config.getList)
  def findBooleanList(path: String): Iterable[Boolean] = lookupIterable(path, config.getBooleanList).map(_.booleanValue())
  def findNumberList(path: String): Iterable[Number] = lookupIterable(path, config.getNumberList)
  def findIntList(path: String): Iterable[Int] = lookupIterable(path, config.getIntList).map(_.intValue())
  def findLongList(path: String): Iterable[Long] = lookupIterable(path, config.getLongList).map(_.longValue)
  def findDoubleList(path: String): Iterable[Double] = lookupIterable(path, config.getDoubleList).map(_.doubleValue)
  def findStringList(path: String): Iterable[String] = lookupIterable(path, config.getStringList)

  def findObjectList(path: String): Iterable[_ <: ConfigObject] =  lookupValue(path, config.getObjectList) match {
    case Some(x) => x.toIterable
    case None => Iterable.empty
  }

  def findConfigList(path: String): Iterable[_ <: Config] = lookupValue(path, config.getConfigList) match {
    case Some(x) => x.toIterable
    case None => Iterable.empty
  }

  def findBytesList(path: String): Iterable[Long] = lookupIterable(path, config.getBytesList).map(_.longValue)
  def findMemorySizeList(path: String): Iterable[ConfigMemorySize] = lookupIterable(path, config.getMemorySizeList)
  def findDurationList(path: String): Iterable[Duration] = findStringList(path).map(Duration.apply)
}
