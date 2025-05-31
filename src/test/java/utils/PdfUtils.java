package utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Класс с методами для работы с pdf файлами
 */
public class PdfUtils {
    @Step("Saving pdf file")
    public static long savePdf(Response response, String fileName) {
        long contentLength = 0;

        if (response != null) {
            // Получаем response в форме потока ввода
            try (InputStream inputStream = response.getBody().asInputStream()) {

                // Получаем размер файла из заголовка Content-Length
                contentLength = response.getHeader("Content-Length") != null
                        ? Long.parseLong(response.getHeader("Content-Length"))
                        : -1;

                // 1. Создаём объект потока вывода и ассоциируем его с файлом на диске (указываем местоположение pdf)
                OutputStream outputStream = new FileOutputStream(fileName);

                // 2. Пока есть информация, читаем содержимое потока ввода inputStream и записываем его в поток вывода outputStream
                int bytesRead;
                byte[] buffer = new byte[4096];

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                // 3. Закрываем потоки
                inputStream.close();
                outputStream.close();

                System.out.println("PDF успешно сохранен в файл: " + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Размер оригинального файла: " + contentLength);
        return contentLength;
    }
}
