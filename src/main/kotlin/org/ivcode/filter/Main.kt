package org.ivcode.filter

import org.ivcode.filter.args.Args
import org.ivcode.filter.filter.Filter
import org.ivcode.filter.params.ParameterStoreMap
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.IllegalStateException


fun main(argv: Array<String>) {
    try {
        runFilter(Args.create(argv))
    } catch (th: Throwable) {
        System.err.println(th.message)
        Args.printHelp()
    }
}

fun runFilter(args: Args) {
    val parameterStoreMap = ParameterStoreMap.create(args)

    val inputStream = if (args.getInput()!=null) {
        FileInputStream(args.getInput())
    } else {
        if(System.`in`.available() > 0) {
            System.`in`
        } else {
            throw IllegalStateException("input file not defined and standard in data not available")
        }
    }

    val outputStream = if (args.getOutput()!=null) {
        FileOutputStream(args.getOutput())
    } else {
        System.out
    }

    inputStream.use { input ->
        outputStream.use { output ->
            Filter(parameterStoreMap).filter(input, output)
        }
    }
}

