package org.ivcode.filter.test

import org.ivcode.filter.params.ParameterStore

class MapParameterStore(
    private val map: Map<String, String>
): ParameterStore {
    override fun getParameter(name: String): String? {
        return map[name]
    }
}
