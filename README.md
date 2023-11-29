# msa-monitoring

모니터링을 위해 사용된 스택

OpenTelemetry-autoconfigure, OpenTelemetry-javaagent (traces, logs 수집)

micrometer, prometheus (metrics 수집 및 데이터 저장 백앤드)

Tempo(Traces 저장 백앤드), Loki(Logs 저장 백앤드)

Grafana (데이터 시각화)


모니터링 대상 서버

Kotlin, Spring Boot 3.1.5


환경

Ubuntu 20.04, Docker Container
