package io.cafebabe.util.i18n

import java.util.Locale

/**
 * Locale parser.
 *
 * @author Vladimir Konstantinov
 * @version 1.0 (4/9/15)
 */
object Locales {

    private val Pattern = """^([a-zA-Z]{1,8})(?:[_-]([a-zA-Z]{1,8}))?""".r.unanchored

  /**
   * Parses locale string to produce instance of [[java.util.Locale Locale]].
   * <p>Locale string should match the following pattern:
   * {{{
   *   ^([a-zA-Z]{1,8})(?:[_-]([a-zA-Z]{1,8}))?
   * }}}
   *
   * @param locale String representation of locale.
   * @return New instance of Locale or default Locale if string is null or is not matched by any pattern.
   */
  def parse(locale: String): Locale = locale match {
    case Pattern(language, country) => new Locale(language, Option(country).getOrElse(""))
    case _ => Locale.getDefault
  }
}
