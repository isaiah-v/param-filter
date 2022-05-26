package org.ivcode.filter.params

import software.amazon.awssdk.services.ssm.SsmClient
import software.amazon.awssdk.services.ssm.model.GetParameterRequest

/**
 * A parameter store that pulls values from aws ssm parameter store
 */
class AwsParameterStore (
    private val client: SsmClient
): ParameterStore {
    override fun getParameter(name: String): String? {
        val request = GetParameterRequest.builder()
            .withDecryption(true)
            .name(name)
            .build()

        val response = client.getParameter(request)
        return response.parameter().value()
    }
}