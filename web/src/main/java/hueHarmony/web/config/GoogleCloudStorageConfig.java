package hueHarmony.web.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GoogleCloudStorageConfig {

    private final ResourceLoader resourceLoader;

    public GoogleCloudStorageConfig(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public Storage storage() throws IOException {
        InputStream serviceAccount = resourceLoader.getResource("classpath:vivolk-d3303-firebase-adminsdk-essjo-d62f5edb0e.json").getInputStream();

        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount)
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));

        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}
