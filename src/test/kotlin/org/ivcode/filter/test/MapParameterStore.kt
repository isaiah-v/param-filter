package org.ivcode.filter.test

import org.ivcode.filter.args.Args
import org.ivcode.filter.params.ParameterStore
import org.ivcode.filter.params.ParameterStoreFactory

class MapParameterStore(
    private val map: Map<String, String>
): ParameterStore {
    override fun getParameter(name: String): String? {
        return map[name]
    }
}

class MapParameterStoreFactory(
    private val map: Map<String, String>
): ParameterStoreFactory {
    override fun createParameterStore(args: Args) =
        MapParameterStore(map)

}
