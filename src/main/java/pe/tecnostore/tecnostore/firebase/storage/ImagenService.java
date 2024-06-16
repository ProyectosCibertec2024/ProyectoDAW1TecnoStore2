package pe.tecnostore.tecnostore.firebase.storage;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Service
public class ImagenService {

    public String uploadFile(File file, String fileName, String contentType, String folderName) throws IOException {
        String fullPath = folderName + "/" + fileName;

        BlobId blobId = BlobId.of("proyectotecnostore-spring.appspot.com", fullPath); // Reemplaza con tu nombre de bucket
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
        InputStream inputStream = ImagenService.class.getClassLoader().getResourceAsStream("firebase.json"); // Cambia el nombre del archivo según el tuyo
        Credentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/proyectotecnostore-spring.appspot.com/o/%s?alt=media";
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fullPath, StandardCharsets.UTF_8));
    }

    public File convertToFile(MultipartFile multipartFile) throws IOException {
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }

    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public String upload(MultipartFile multipartFile, String nomCarpeta) {
        File file = null;
        try {
            String fileName = multipartFile.getOriginalFilename();
            String contentType = multipartFile.getContentType();

            file = this.convertToFile(multipartFile);
            String URL = this.uploadFile(file, fileName, contentType, nomCarpeta);
            return URL;
        } catch (Exception e) {
            System.out.println("Error Storage : " + e.getCause().getMessage());
            return "Image couldn't upload, Something went wrong";
        } finally {
            if (file != null && file.exists()) {
                file.delete();
            }
        }
    }

}
