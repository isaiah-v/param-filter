package org.ivcode.filter.params

import org.ivcode.filter.args.Args

/**
 * A parameter store that pulls values from environment variables
 */
class EnvParameterStore: ParameterStore {
    override fun getParameter(name: String): String? {
        return System.getenv(name)
    }

    companion object {
        fun create(args: Args) = EnvParameterStore()
    }
}