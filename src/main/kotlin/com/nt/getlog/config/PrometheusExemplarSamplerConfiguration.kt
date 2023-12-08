package com.nt.getlog.config

import io.prometheus.client.exemplars.tracer.otel_agent.OpenTelemetryAgentSpanContextSupplier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PrometheusExemplarSamplerConfiguration {
    @Bean
    fun openTelemetryAgentSpanContextSupplier() = OpenTelemetryAgentSpanContextSupplier();
}