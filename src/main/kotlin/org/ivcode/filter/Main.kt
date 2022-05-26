package org.ivcode.filter

import org.ivcode.filter.args.Args
import org.ivcode.filter.filter.Filter
import org.ivcode.filter.params.ParameterStoreMap
import java.io.FileInputStream
import java.io.FileOutputStream


fun main(argv: Array<String>) {
    val args = Args.create(argv)
    val parameterStoreMap = ParameterStoreMap.create(args)

    FileInputStream(args.getInput()).use { input ->
        if(args.getOutput()==null) {
            Filter(parameterStoreMap).filter(input, System.out)
        } else {
            FileOutputStream(args.getOutput()).use { output ->
                Filter(parameterStoreMap).filter(input, output)
            }
        }
    }
}

