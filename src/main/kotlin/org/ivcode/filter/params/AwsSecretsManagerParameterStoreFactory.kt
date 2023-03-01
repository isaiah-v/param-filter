package org.ivcode.filter.params

import com.fasterxml.jackson.databind.ObjectMapper
import org.ivcode.filter.args.Args
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient

class AwsSecretsManagerParameterStoreFactory: ParameterStoreFactory {
    override fun createParameterStore(args: Args): AwsSecretsManagerParameterStore {
        return AwsSecretsManagerParameterStore(SecretsManagerClient.builder().build(), ObjectMapper())
    }
}
