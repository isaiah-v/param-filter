package org.ivcode.filter

import org.ivcode.filter.args.Args
import org.ivcode.filter.filter.Filter
import org.ivcode.filter.params.createParameterStores
import java.io.FileInputStream
import java.io.FileOutputStream


fun main(argv: Array<String>) {
    val args = Args.create(argv)
    val parameterStores = createParameterStores(args)

    FileInputStream(args.getInput()).use { input ->
        if(args.getOutput()==null) {
            Filter(parameterStores).filter(input, System.out)
        } else {
            FileOutputStream(args.getOutput()).use { output ->
                Filter(parameterStores).filter(input, output)
            }
        }
    }
}

