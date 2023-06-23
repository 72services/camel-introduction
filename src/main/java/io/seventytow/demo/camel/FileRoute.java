package io.seventytow.demo.camel;

import org.apache.camel.builder.RouteBuilder;

public class FileRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("file:src/data")
                .choice()
                .when(xpath("/person/city = 'London'"))
                .log("UK message")
                .to("file:target/messages/uk")
                .otherwise()
                .log("Other message")
                .to("file:target/messages/others");
    }
}
