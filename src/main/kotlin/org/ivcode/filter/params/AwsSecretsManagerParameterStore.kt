package org.ivcode.filter.params

import com.fasterxml.jackson.databind.ObjectMapper
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest
import java.io.StringReader

class AwsSecretsManagerParameterStore(
    private val client: SecretsManagerClient,
    private val mapper: ObjectMapper
): ParameterStore {

    override fun getParameter(name: String): String? {
        val index = name.indexOf('.')

        val secretName: String
        val key: String?
        if (index!=-1) {
            secretName = name.substring(0, index)
            key = name.substring(index+1)
        } else {
            secretName = name
            key = null
        }

        val secretsRequest = GetSecretValueRequest.builder()
            .secretId(secretName)
            .build()

        val secrets = client.getSecretValue(secretsRequest)

        val secretsJson = secrets.secretString()

        return if(key!=null) {
            val reader = StringReader(secretsJson)
            val map = mapper.readValue(reader, Map::class.java)

            map[key]?.toString() ?: null
        } else {
            secretsJson
        }
    }
}