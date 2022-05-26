package org.ivcode.filter.params

import org.ivcode.filter.args.Args
import software.amazon.awssdk.services.ssm.SsmClient

class AwsParameterStoreFactory: ParameterStoreFactory {
    override fun createParameterStore(args: Args) =
        AwsParameterStore(SsmClient.builder().build())
}