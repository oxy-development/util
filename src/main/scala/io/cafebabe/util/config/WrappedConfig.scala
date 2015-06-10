package io.cafebabe.util.config

import com.typesafe.config._

import scala.collection.JavaConversions._
import scala.concurrent.duration.{FiniteDuration, Duration}
import java.util.concurrent.TimeUnit

/**
 * @author Vladimir Konstantinov
 * @version 1.0 (6/10/2015)
 */
class WrappedConfig(config: Config) {
  def findBoolean(path: String): Option[Boolean] = if (config.hasPath(path)) Some(config.getBoolean(path)) else None
  def findNumber(path: String): Option[Number] = if (config.hasPath(path)) Some(config.getNumber(path)) else None
  def findInt(path: String): Option[Int] = if (config.hasPath(path)) Some(config.getInt(path)) else None
  def findLong(path: String): Option[Long] = if (config.hasPath(path)) Some(config.getLong(path)) else None
  def findDouble(path: String): Option[Double] = if (config.hasPath(path)) Some(config.getDouble(path)) else None
  def findString(path: String): Option[String] = if (config.hasPath(path)) Some(config.getString(path)) else None
  def findObject(path: String): Option[ConfigObject] = if (config.hasPath(path)) Some(config.getObject(path)) else None
  def findConfig(path: String): Option[Config] = if (config.hasPath(path)) Some(config.getConfig(path)) else None
  def findValue(path: String): Option[ConfigValue] = if (config.hasPath(path)) Some(config.getValue(path)) else None
  def findBytes(path: String): Option[Long] = if (config.hasPath(path)) Some(config.getBytes(path)) else None
  def findMemorySize(path: String): Option[ConfigMemorySize] = if (config.hasPath(path)) Some(config.getMemorySize(path)) else None
  def findDuration(path: String): Option[Duration] = if (config.hasPath(path)) Some(Duration(config.getString(path))) else None
  def findList(path: String): Option[ConfigList] = if (config.hasPath(path)) Some(config.getList(path)) else None
  def findBooleanList(path: String): Option[List[Boolean]] = if (config.hasPath(path)) Some(config.getBooleanList(path).map(_.booleanValue).toList) else None
  def findNumberList(path: String): Option[List[Number]] = if (config.hasPath(path)) Some(config.getNumberList(path).toList) else None
  def findIntList(path: String): Option[List[Int]] = if (config.hasPath(path)) Some(config.getIntList(path).map(_.intValue).toList) else None
  def findLongList(path: String): Option[List[Long]] = if (config.hasPath(path)) Some(config.getLongList(path).map(_.longValue).toList) else None
  def findDoubleList(path: String): Option[List[Double]] = if (config.hasPath(path)) Some(config.getDoubleList(path).map(_.doubleValue).toList) else None
  def findStringList(path: String): Option[List[String]] = if (config.hasPath(path)) Some(config.getStringList(path).toList) else None
  def findObjectList(path: String): Option[List[ConfigObject]] = if (config.hasPath(path)) Some(config.getObjectList(path).toList) else None
  def findConfigList(path: String): Option[List[Config]] = if (config.hasPath(path)) Some(config.getConfigList(path).toList) else None
  def findBytesList(path: String): Option[List[Long]] = if (config.hasPath(path)) Some(config.getBytesList(path).map(_.longValue).toList) else None
  def findMemorySizeList(path: String): Option[List[ConfigMemorySize]] = if (config.hasPath(path)) Some(config.getMemorySizeList(path).toList) else None
  def findDurationList(path: String): Option[List[Duration]] = if (config.hasPath(path)) Some(config.getStringList(path).map(Duration.apply).toList) else None

  def findFiniteDuration(path: String): Option[FiniteDuration] =
    if (config.hasPath(path)) Some(FiniteDuration(config.getDuration(path, TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS))
    else None
}
