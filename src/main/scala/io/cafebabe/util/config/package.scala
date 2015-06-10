package io.cafebabe.util

import com.typesafe.config.Config

/**
 * @author Vladimir Konstantinov
 * @version 1.0 (6/10/2015)
 */
package object config {
  implicit def wrapped(config: Config): WrappedConfig = new WrappedConfig(config)
}
