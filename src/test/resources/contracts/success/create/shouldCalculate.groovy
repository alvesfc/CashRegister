package success.create

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description 'Should calculate total amount when'
    name 'shouldCalculate'
    label 'shouldCalculate'

    request {
        headers {
            contentType(applicationJsonUtf8())
        }
        method HttpMethods.HttpMethod.POST
        url value(consumer(regex('/orders')), producer('/orders'))
        body([

                    "orderItens":[
                        [
                            "productCode":1,
                            "quantity":5
                        ],
                        [
                            "productCode":2,
                            "quantity":3
                        ]
                ],
                    "coupons":[
                        "BLACK_PREVIEW_40"
                ]

        ])

    }

    response {
        status 200

        body([
                "amount": 19.37
        ])
    }
}


