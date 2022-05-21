package org.ivcode.filter.params

import org.ivcode.filter.args.Args

/**
 * Represents a store to get parameters
 */
interface ParameterStore {
    fun getParameter(name: String): String?
}

fun createParameterStores(args: Args): Map<String, ParameterStore> {
    val map = mutableMapOf<String, ParameterStore>()
    map["env"] = EnvParameterStore.create(args)
    map["aws"] = AwsParameterStore.create(args)

    return map;
}
