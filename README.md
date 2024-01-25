# spring-boot-otel-baggage-demo
Spring boot demo project to show an issue related with OpenTelemetry baggage not being passed from a Filter to a Controller when using Undertow as an application server.

See issue at Github: [Baggage is lost between Filter and Controller when using Spring-Boot with Undertow #10323](https://github.com/open-telemetry/opentelemetry-java-instrumentation/issues/10323)

POM file already has the jvmArguments to run the OpenTelemetry Java Agent (2.0.0 version), which is included in the _opentelemetry_ directory.

### Run Locally

Go to the project root folder and run:

```bash
./mvnw spring-boot:run
```

Perform an http request

```bash
curl http://localhost:9091
```

Check the logs to confirm the empty Baggage reaching the controller with this log entry **Controller Baggage: {}** 

```
2024-01-25 10:26:20.036  INFO 16536 --- [  XNIO-1 task-1] c.g.r.springboot.otelbaggage.DemoFilter  : ### DemoFilter start ###
2024-01-25 10:26:20.057  INFO 16536 --- [  XNIO-1 task-1] c.g.r.springboot.otelbaggage.DemoFilter  : Baggage inside DemoFilter try-with-resources: {otel.baggage.demo.key=this-is-a-value}
2024-01-25 10:26:20.074  INFO 16536 --- [  XNIO-1 task-1] c.g.r.s.otelbaggage.DemoController       : ### DemoController start ###
2024-01-25 10:26:20.074  INFO 16536 --- [  XNIO-1 task-1] c.g.r.s.otelbaggage.DemoController       : Controller Baggage: {}
2024-01-25 10:26:20.074  INFO 16536 --- [  XNIO-1 task-1] c.g.r.s.otelbaggage.DemoController       : ### DemoController end ###
[otel.javaagent 2024-01-25 10:26:20:108 +0000] [XNIO-1 task-1] INFO io.opentelemetry.exporter.logging.LoggingSpanExporter - 'GET /' : d9318e820db4c8e3016077348a6dd3cc 793bf5be17149746 SERVER [tracer: io.opentelemetry.undertow-1.4:2.0.0-alpha] AttributesMap{data={http.route=/, http.request.method=GET, network.peer.port=21643, url.path=/, server.address=localhost, network.peer.address=0:0:0:0:0:0:0:1, network.protocol.version=1.1, http.response.status_code=200, thread.id=22, server.port=9091, user_agent.original=curl/7.65.0, url.scheme=http, thread.name=XNIO-1 I/O-5}, capacity=128, totalAddedValues=13}
2024-01-25 10:26:20.109  INFO 16536 --- [  XNIO-1 task-1] c.g.r.springboot.otelbaggage.DemoFilter  : Baggage inside DemoFilter try-with-resources after 'filterChain.doFilter': {otel.baggage.demo.key=this-is-a-value}
2024-01-25 10:26:20.109  INFO 16536 --- [  XNIO-1 task-1] c.g.r.springboot.otelbaggage.DemoFilter  : ### DemoFilter end ###
2024-01-25 10:26:39.158  INFO 16536 --- [ionShutdownHook] io.undertow                              : stopping server: Undertow - 2.2.28.Final
2024-01-25 10:26:39.161  INFO 16536 --- [ionShutdownHook] io.undertow.servlet                      : Destroying Spring FrameworkServlet 'dispatcherServlet'
```
