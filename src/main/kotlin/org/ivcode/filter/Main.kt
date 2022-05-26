package org.ivcode.filter

import org.ivcode.filter.args.Args
import org.ivcode.filter.filter.Filter
import org.ivcode.filter.params.ParameterStoreMap
import java.io.FileInputStream
import java.io.FileOutputStream


fun main(argv: Array<String>) {
    val args = Args.create(argv)
    val parameterStoreMap = ParameterStoreMap.create(args)

    val inputStream = if (args.getInput()!=null) {
        FileInputStream(args.getInput())
    } else {
        System.`in`
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

