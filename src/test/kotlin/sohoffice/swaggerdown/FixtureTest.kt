package sohoffice.swaggerdown

import org.slf4j.LoggerFactory
import java.io.File
import java.lang.RuntimeException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributes
import java.util.function.BiPredicate
import kotlin.streams.toList
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Use the fixtures in src/test/resources/fixtures to test.
 *
 * Any file has the name `test_*.yml` will be considered as test fixture.
 * The expected result is named `test_*_${flavor}.md`
 *
 * Basically the render will be executed and then compare with the expected file.
 */
class FixtureTest {

  @Test
  fun testStandard() {
    testFixture("fixtures", Flavor.STANDARD)
  }

  @Test
  @Ignore
  fun testExperimental() {
    testFixture("experimental-fixtures", Flavor.STANDARD)
  }

  private fun testFixture(base: String, flavor: Flavor) {
    getFixtures(base).forEach { fixture ->
      logger.info("Fixture: {}", fixture.toFile().name)
      val app = App()
      val parsed = app.parse(fixture.toFile()) ?: throw RuntimeException("fixture $fixture cannot be parsed")
      val dir = fixture.toFile().parentFile
      val expectedFn = "${fixture.toFile().nameWithoutExtension}_${flavor.pathFragment}.md"
      val expected = File(dir, expectedFn).readText()
      val data = app.prepare(parsed)
      val result = app.merge(data, flavor.pathFragment)

      assertEquals(expected, result)
      logger.info("         OK")
    }
  }

  private fun getFixtures(base: String): List<Path> {
    val res = this.javaClass.classLoader?.getResource(base) ?: throw RuntimeException("fixtures not found")
    val dir = Paths.get(res.toURI())
    return Files.find(dir, 1, BiPredicate<Path, BasicFileAttributes> { path, attr ->
      val fn = path.fileName?.toString()
      if (fn == null) {
        false
      } else {
        fn.startsWith("test_") && fn.endsWith(".yml")
      }
    }).toList()
  }

  private val logger = LoggerFactory.getLogger(FixtureTest::class.java)
}
