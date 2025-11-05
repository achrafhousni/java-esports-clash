package com.nexapay.esportsclash.core.infrastructure.bean;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PipelineConfiguration {

    /*@Bean
    Pipeline pipeline(ObjectProvider<Command.Handler> commandHandlers,
                      ObjectProvider<Command.Middleware> middlewares,
                      ObjectProvider<Notification.Handler> notificationHandlers)
    {

        return new Pipelinr()
                .with(commandHandlers::orderedStream)
                .with(middlewares::orderedStream)
                .with(notificationHandlers::orderedStream);
    }*/

    @Bean
    Pipeline pipeline(ObjectProvider<Command.Handler> commandHandlers,
                      ObjectProvider<Notification.Handler> notificationHandlers,
                      ObjectProvider<Command.Middleware> middlewares) {
        return new Pipelinr()
                .with(() -> commandHandlers.orderedStream())
                .with(() -> notificationHandlers.orderedStream())
                .with(()-> middlewares.orderedStream());
    }
}
