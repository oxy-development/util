package io.cafebabe.util.reflect

import io.cafebabe.util.i18n.Locales
import org.scalatest._

import java.util.Locale

/**
 * @author Vladimir Konstantinov
 * @version 1.0 (4/18/2015)
 */
class LocalesSpec extends FlatSpec with Matchers {

  "Locale parser" should "parse only language string" in {
    Locales.parse("lang") should be (new Locale("lang"))
  }

  it should "parse language and country string with underscore as separator" in {
    Locales.parse("lang_CNTRY") should be (new Locale("lang", "CNTRY"))
  }

  it should "parse ignoring case" in {
    Locales.parse("LANG_CNTRY") should be (new Locale("lang", "cntry"))
  }

  it should "parse HTTP header Accept-Language using first language" in {
    Locales.parse("en-ca,en;q=0.8,en-us;q=0.6,de-de;q=0.4,de;q=0.2") should be (new Locale("en", "ca"))
  }

  it should "return default locale if string is not matched by pattern" in {
    Locales.parse("4ar124") should be (Locale.getDefault)
  }

  it should "return default locale for null string" in {
    Locales.parse(null) should be (Locale.getDefault)
  }
}
