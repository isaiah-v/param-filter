package org.ivcode.filter.params

/**
 * Represents a store to get parameters
 */
interface ParameterStore {
    fun getParameter(name: String): String?
}
