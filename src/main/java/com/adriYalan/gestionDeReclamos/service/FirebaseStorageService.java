package com.adriYalan.gestionDeReclamos.service;

import com.google.firebase.cloud.StorageClient;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class FirebaseStorageService {

    private static final String BUCKET_NAME = "gestion-rclamo-api-uade.appspot.com"; // Solo el nombre del bucket

    public String uploadFile(MultipartFile file, int idReclamo) throws IOException {
        // Obtener la referencia al bucket
        Bucket bucket = StorageClient.getInstance().bucket(BUCKET_NAME);

        // Crear un nombre único para el archivo
        String fileName = "ImgReclamo_" + idReclamo + "_" + UUID.randomUUID().toString().substring(0,6);

        // Subir el archivo al bucket
        Blob blob = bucket.create(fileName, file.getInputStream(), file.getContentType());

        // Generar el enlace público (si las reglas lo permiten)
        String publicUrl = "https://firebasestorage.googleapis.com/v0/b/" + BUCKET_NAME + "/o/" +
                URLEncoder.encode(fileName, StandardCharsets.UTF_8) + "?alt=media";

        return publicUrl;
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
            // Suponiendo que el enlace tiene el formato correcto:
            // Ejemplo: https://firebasestorage.googleapis.com/v0/b/<BUCKET_NAME>/o/<FILE_NAME>?alt=media
            String[] parts = fileLink.split("/o/");
            if (parts.length > 1) {
                String fileNameWithParams = parts[1].split("\\?")[0]; // Obtener solo el nombre del archivo
                return fileNameWithParams; // El nombre del archivo
            }
        } catch (Exception e) {
            System.out.println("Error al procesar el enlace: " + e.getMessage());
        }
        return null;
    }





}



