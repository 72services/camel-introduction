package io.seventytow.demo.camel;

import org.apache.camel.builder.RouteBuilder;

public class FileRouteBuilder extends RouteBuilder {

    String fileUk = "file:target/messages/uk";
    String fileOthers = "file:target/messages/others";

    @Override
    public void configure() {
        from("file:src/data")
                .id("file-route")
                .choice()
                .when(xpath("/person/city = 'London'"))
                .log("UK message")
                .to(fileUk)
                .otherwise()
                .log("Other message")
                .to(fileOthers);
    }
}
