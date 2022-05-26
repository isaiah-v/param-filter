package org.ivcode.filter.params

import org.ivcode.filter.args.Args

interface ParameterStoreFactory {
    fun createParameterStore(args: Args): ParameterStore
}