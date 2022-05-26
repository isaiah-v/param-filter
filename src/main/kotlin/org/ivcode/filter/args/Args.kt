package org.ivcode.filter.args

import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException
import java.io.File

class Args {
    @Parameter(names = ["-i", "--in"], description = "input filename (default: standard in)")
    private var input: File? = null

    @Parameter(names = ["-o", "--out"], description = "output filename (default: standard out)")
    private var output: File? = null

    fun getInput(): File? = input
    fun getOutput(): File? = output

    companion object {
        fun create(argv: Array<String>): Args {
            val argObject = Args()
            val jCommander = JCommander.newBuilder()
                .addObject(argObject)
                .build()

            jCommander.parse(*argv)

            return argObject
        }

        fun printHelp() {
            JCommander.newBuilder()
                .addObject(Args())
                .build()
                .usage()
        }
    }
}