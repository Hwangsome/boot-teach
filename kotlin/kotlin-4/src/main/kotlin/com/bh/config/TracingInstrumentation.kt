//package com.bh.config
//
//import arrow.core.Either
//import io.micrometer.core.instrument.MeterRegistry
//import io.micrometer.core.instrument.Timer
//import mu.KLogging
//import org.springframework.stereotype.Component
//
//open class TracingInstrumentation(val registry: MeterRegistry) {
//    companion object : KLogging()
//
//
//    inline fun <RQ, RS> traced(
//        name: String,
//        request: RQ,
//        function: () -> Either<Exception, RS>
//    ) {
//        return timed(name) {
//            logged(name,request,function)
//        }
//    }
//
//
//    inline fun <T> timed(name: String, function: () -> T): T {
//        val sample = Timer.start(registry)
//        return function.invoke().also {
//            sample.stop(registry.timer("$name.timer"))
//        }
//    }
//
//    inline fun <RQ, RS> logged(
//        name: String,
//        request: RQ,
//        function: () -> Either<Exception, RS>
//    ): Either<Exception, RS> {
//        val start = System.currentTimeMillis()
//        registry.counter("$name.call").increment()
//        return function.invoke()
//            .also {
//                response->
//                val latency = System.currentTimeMillis() - start
//                response.fold(
//                    {
//                        errors ->
//                        logger.info{
//                            """
//                                errors:$errors
//                            """.trimIndent()
//                        }
//                    },{
//                        registry.counter("$name.success").increment()
//                        logger.info{
//                            """
//                                endpoint=$name,request=$request,latency=$latency
//                            """.trimIndent()
//                        }
//                    }
//                )
//            }
//    }
//}