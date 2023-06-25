package io.seventytow.demo.camel;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

class FileRouteBuilderTest extends CamelTestSupport {

    @Test
    void uk_message() throws InterruptedException {
        getMockEndpoint("mock:uk")
                .expectedBodiesReceived("""
                        <person>
                            <lastName>Smith</lastName>
                            <firstName>John</firstName>
                            <city>London</city>
                        </person>""");

        template.sendBody("file:src/data", """
                <person>
                    <lastName>Smith</lastName>
                    <firstName>John</firstName>
                    <city>London</city>
                </person>""");

        MockEndpoint.assertIsSatisfied(context);
    }

    @Override
    protected RoutesBuilder createRouteBuilder() {
        FileRouteBuilder fileRouteBuilder = new FileRouteBuilder();
        fileRouteBuilder.fileUk = "mock:uk";
        return fileRouteBuilder;
    }
}
