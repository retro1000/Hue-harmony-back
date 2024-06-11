package hueHarmony.web.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Service
public class FirebaseStorageService {

    private final Storage storage;

    @Autowired
    public FirebaseStorageService(Storage storage){
        this.storage = storage;
    }

    @Getter
    private final static String bucketName = "vivolk-d3303.appspot.com";

    public String uploadFile(String fileName, byte[] fileBytes, String contentType) {
        // Get the bucket instance from Firebase StorageClient
        var bucket = StorageClient.getInstance().bucket();

        // Create the BlobInfo object
        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, fileName))
                .setContentType(contentType)
                .build();

        // Upload the file to the bucket
        bucket.create(blobInfo.getName(), fileBytes, blobInfo.getContentType());

        return blobInfo.getBlobId().getName();
    }

    public String getFileDownloadUrl(String blobId, int time, TimeUnit timeUnit) {
        BlobId blob = BlobId.of(bucketName, blobId);
        return storage.signUrl(BlobInfo.newBuilder(blob).build(), time, timeUnit, Storage.SignUrlOption.withV4Signature()).toString();
    }

    public void deleteFile(String blobId) {
//        String blobId;
//        try {
//            String path = (new URL(url)).getPath();
//            blobId = path.startsWith("/") ? path.substring(1) : path;
//        } catch (MalformedURLException e) {
//            throw new IllegalArgumentException("Invalid URL", e);
//        }
        BlobId blob = BlobId.of(bucketName, blobId);
        StorageClient.getInstance().bucket().getStorage().delete(blob);
    }

    public void deleteFileUsingUrl(String url) {
        String blobId;
        try {
            String path = (new URL(url)).getPath();
            blobId = path.startsWith("/") ? path.substring(1) : path;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL", e);
        }
        BlobId blob = BlobId.of(bucketName, blobId);
        StorageClient.getInstance().bucket().getStorage().delete(blob);
    }
}

