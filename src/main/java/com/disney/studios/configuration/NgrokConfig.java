package com.disney.studios.configuration;


import io.github.kilmajster.ngrok.control.NgrokApiClient;
import io.github.kilmajster.ngrok.control.NgrokSystemCommandExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class NgrokConfig {


    @Bean
    public NgrokApiClient ngrokApiClient(@Value("${ngrok.api.url:http://localhost:8080}") String ngrokApiUrl) {
        return new NgrokApiClient(ngrokApiUrl);
    }


    @Bean
    public NgrokSystemCommandExecutor ngrokSystemCommandExecutor(@Value("${ngrok.waitForStartup.millis:3000}") long waitForStartupMillis) {
        return new NgrokSystemCommandExecutor(waitForStartupMillis);

}

    @Bean
    public TaskExecutor ngrokExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Set core pool size as needed
        executor.setMaxPoolSize(10); // Set maximum pool size as needed
        executor.setQueueCapacity(25); // Set queue capacity as needed
        executor.initialize();
        return executor;
    }

}