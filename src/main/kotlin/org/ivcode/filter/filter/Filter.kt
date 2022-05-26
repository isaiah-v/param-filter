package org.ivcode.filter.filter

import org.ivcode.filter.params.ParameterStoreMap
import java.io.*

class Filter(
    private val parameterStoreMap: ParameterStoreMap
) {
    companion object {
        /** The maximum parameter name length **/
        private const val MAX_NAME_LENGTH = 1024
    }

    fun filter(input: InputStream, output: OutputStream) {
        val reader = InputStreamReader(input)
        val bufferedReader = BufferedReader(reader)

        val writer = OutputStreamWriter(output)
        val bufferedWriter = BufferedWriter(writer)

        outPlaceholder(bufferedReader, bufferedWriter)
    }

    private fun outPlaceholder(reader: BufferedReader, writer: BufferedWriter) {
        var ch: Int = reader.read()
        while(ch>=0) {
            if('$'==ch.toChar()) {
                inPlaceholder(reader, writer)
            } else {
                writer.write(ch)
            }

            ch = reader.read()
        }

        writer.flush()
    }

    private fun inPlaceholder(reader: BufferedReader, writer: BufferedWriter) {
        var ich: Int = reader.read()
        if('{'!=ich.toChar()) {
            writer.write("\$${ich.toChar()}")
            return
        }

        val sb = StringBuilder()
        ich = reader.read()
        while(ich!=-1 && sb.length < MAX_NAME_LENGTH ) {
            val ch = ich.toChar()

            if('}'==ch) {
                // found terminating token, write param
                writeParam(sb.toString(), writer)
                return
            } else {
                // searching for terminating token
                sb.append(ch)
            }

            ich = reader.read()
        }

        //EOF or name too long, write back in everything that was read
        writer.write("\${$sb")
    }

    private fun writeParam(name: String, writer: BufferedWriter) {
        if(name.isBlank()) {
            // no variable defined, write back in the empty placeholder
            writer.write("\${}")
            return
        }

        val delimIndex = name.indexOf('.')
        if(delimIndex==-1) {
            // not the correct format, write back in the placeholder
            writer.write("\${$name}")
            return
        }

        val key = name.substring(0, delimIndex)
        val paramName = name.substring(delimIndex+1)

        val paramStore = parameterStoreMap.get(key)
        if(paramStore==null) {
            // key does not reference a parameter stores, write value back in
            writer.write("\${$name}")
            return
        }

        val param = paramStore.getParameter(paramName)
        if(param!=null) {
            // Parameter found, write value
            writer.write(param.trim())
        } else {
            // Parameter not found, write value back
            writer.write("\${$name}")
        }
    }
}