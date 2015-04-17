package io.cafebabe.util.i18n

import java.util.Locale
import java.util.regex.Pattern

/**
 * Locale factory.
 *
 * @author Vladimir Konstantinov
 * @version 1.0 (4/9/15)
 */
object Locales {

  val Regex = Pattern.compile("(?<lang>[a-zA-Z]{2})(_(?<country>[a-zA-Z]{2}))?")

  /**
   * Parses locale string to produce instance of [[java.util.Locale Locale]].
   *
   * @param locale String representation of locale.
   * @return New instance of Locale or default Locale if string is null or is not matched by pattern.
   */
  def apply(locale: String): Locale = {
    if (locale != null) {
      val matcher = Regex.matcher(locale)
      matcher.find() match {
        case true =>
          val lang = matcher.group("lang")
          val country = Option(matcher.group("country")).getOrElse("")
          new Locale(lang, country)
        case _ => Locale.getDefault
      }
    } else Locale.getDefault
  }
}
