package org.ivcode.filter.params

import org.ivcode.filter.args.Args

class ParameterStoreMap(
    private val args: Args,
    private val factoryMap: Map<String, ParameterStoreFactory>
) {
    private val storeMap = mutableMapOf<String, ParameterStore?>()

    fun get(key: String): ParameterStore? {
        return storeMap.computeIfAbsent(key) {
            factoryMap[key]?.createParameterStore(args) ?: null
        }
    }

    companion object {
        fun create(args: Args) =
            ParameterStoreMap(args, mapOf(
                "env" to EnvParameterStoreFactory(),
                "aws" to AwsParameterStoreFactory()
            ))
    }
}