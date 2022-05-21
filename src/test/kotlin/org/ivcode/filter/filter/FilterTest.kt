package org.ivcode.filter.filter

import org.ivcode.filter.test.MapParameterStore

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import software.amazon.awssdk.utils.StringInputStream
import java.io.ByteArrayOutputStream

class FilterTest {

    @ParameterizedTest
    @CsvSource(*[
        "\${test.A},a",               // Happy Path
        "\${test.hello},world",       // Happy Path
        "\${test.C,\${test.C",        // Missing closing should not be interpreted
        "\${},\${}",                  // Empty placeholder should not be interpreted
        "t\${test.C}t,tct",           // Test valid usage with surrounding text
        "t\${}t,t\${}t",              // Test invalid usage with surrounding text
        "\${test},\${test}",          // Valid prefix with no name should not be interpreted
        "\${test.},\${test.}",        // Valid prefix with no name should not be interpreted
        "\${tst.a},\${tst.a}",        // Invalid prefix should not be interpreted
        "\${test.D},\${test.D}",      // Valid prefix with undefined param should not be interpreted
    ])
    fun test(input: String, expected: String) {
        val varMap = mapOf(
            "A" to "a",
            "B" to "b",
            "C" to "c",
            "hello" to "world"
        )
        val storeMap = mapOf(
            "test" to MapParameterStore(varMap)
        )

        val actual = StringInputStream(input).use { inputStream->
            ByteArrayOutputStream().use { outputStream ->
                Filter(storeMap).filter(inputStream, outputStream)
                String(outputStream.toByteArray())
            }
        }

        assertEquals(expected, actual)
    }
}