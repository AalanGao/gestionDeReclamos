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

    public void deleteFileByLink(String fileLink) {
        // Obtener la referencia al bucket
        Bucket bucket = StorageClient.getInstance().bucket(BUCKET_NAME);

        try {
            // Extraer el nombre del archivo del enlace
            String fileName = extractFileNameFromLink(fileLink);

            if (fileName != null && !fileName.isEmpty()) {
                // Intentar obtener el archivo por su nombre
                Blob blob = bucket.get(fileName);

                if (blob != null && blob.exists()) {
                    // Eliminar el archivo
                    blob.delete();
                    System.out.println("Archivo eliminado exitosamente: " + fileName);
                } else {
                    System.out.println("El archivo no existe: " + fileName);
                }
            } else {
                System.out.println("No se pudo extraer un nombre de archivo válido del enlace.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el archivo: " + e.getMessage());
        }
    }

    // Método auxiliar para extraer el nombre del archivo de un enlace
    private String extractFileNameFromLink(String fileLink) {
        try {
            // Suponiendo que el enlace tiene un formato estándar como el proporcionado
            // Ejemplo: https://storage.googleapis.com/download/storage/v1/b/<BUCKET>/o/<FILE_NAME>?<PARAMS>
            String[] parts = fileLink.split("/o/");
            if (parts.length > 1) {
                String fileNameWithParams = parts[1];
                return fileNameWithParams.split("\\?")[0]; // Elimina los parámetros
            }
        } catch (Exception e) {
            System.out.println("Error al procesar el enlace: " + e.getMessage());
        }
        return null;
    }



}



