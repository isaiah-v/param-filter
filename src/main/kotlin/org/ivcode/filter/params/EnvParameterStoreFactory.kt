package org.ivcode.filter.params

import org.ivcode.filter.args.Args

class EnvParameterStoreFactory: ParameterStoreFactory {
    override fun createParameterStore(args: Args) = EnvParameterStore()
}