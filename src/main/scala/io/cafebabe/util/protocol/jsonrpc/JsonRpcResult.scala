/*
 * Copyright (C) 2015  Vladimir Konstantinov, Yuriy Gintsyak
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.cafebabe.util.protocol.jsonrpc

import org.json4s._

/**
 * TODO: Add description.
 *
 * @author Vladimir Konstantinov
 */
case class JsonRpcResult(result: JValue, id: Int) extends JsonRpcMessage {

  override val toJson = JObject(
    ("result", result),
    ("id", JInt(id))
  )
}

/**
 * TODO: Add description.
 *
 * @author Vladimir Konstantinov
 */
object JsonRpcResult {

  def from(json: JValue): Option[JsonRpcResult] = json match {
    case JObject(fields) =>
      (for {
        ("result", result) <- fields
        ("id", JInt(id)) <- fields
      } yield JsonRpcResult(result, id.toInt)).headOption
    case _ => None
  }
}
