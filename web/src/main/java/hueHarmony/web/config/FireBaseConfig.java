package hueHarmony.web.config;

import hueHarmony.web.service.FirebaseStorageService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FireBaseConfig {

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public FirebaseApp firebaseApp() {
        try {
            InputStream serviceAccount = resourceLoader.getResource("classpath:vivolk-d3303-firebase-adminsdk-essjo-d62f5edb0e.json").getInputStream();

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket(FirebaseStorageService.getBucketName())
                    .build();

            return FirebaseApp.initializeApp(options);
        } catch (IOException exception) {
            throw new IllegalStateException("Failed to initialize FirebaseApp", exception);
        }
    }
}
