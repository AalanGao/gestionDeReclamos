package com.adriYalan.gestionDeReclamos.service;

import com.google.firebase.cloud.StorageClient;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class FirebaseStorageService {

    private static final String BUCKET_NAME = "gestion-rclamo-api-uade.appspot.com"; // Solo el nombre del bucket

    public String uploadFile(MultipartFile file, int idReclamo) throws IOException {
        // Obtener la referencia al bucket
        Bucket bucket = StorageClient.getInstance().bucket(BUCKET_NAME);

        String fileName = "ImgReclamo_" + idReclamo + "_" + UUID.randomUUID().toString().substring(0,6);

        Blob blob = bucket.create(fileName, file.getInputStream(), file.getContentType());

        return blob.getMediaLink();
    }
}



