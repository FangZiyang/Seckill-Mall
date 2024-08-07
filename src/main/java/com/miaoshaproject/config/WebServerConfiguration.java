package com.miaoshaproject.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class WebServerConfiguration implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory configurableWebServerFactory) {
        //Customize our tomcat connector with the interface provided to us by the corresponding factory class
        ((TomcatServletWebServerFactory) configurableWebServerFactory).addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();

                //Customize keepalivetimeout, set the server to automatically disconnect
                // the keepalive link if there is no request within 30 seconds.
                protocol.setKeepAliveTimeout(30000);
                //Automatically breaks the keepalive link when the client sends more than 10,000 requests
                protocol.setMaxKeepAliveRequests(10000);
            }
        });
    }
}
