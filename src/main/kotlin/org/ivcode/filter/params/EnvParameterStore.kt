package org.ivcode.filter.params

/**
 * A parameter store that pulls values from environment variables
 */
class EnvParameterStore: ParameterStore {
    override fun getParameter(name: String): String? {
        return System.getenv(name)
    }
}