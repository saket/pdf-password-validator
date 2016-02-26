package com.sun.pdfview;

import com.sun.pdfview.decrypt.PDFAuthenticationFailureException;
import com.sun.pdfview.decrypt.PDFPassword;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Utility class for checking the validity of a Pdf file and / or its password.
 */
public class PdfValidator {

    private static final String TAG = "Pdf";

    /**
     * Validates the Pdf file and checks if it cna be unlocked using <var>password</var>
     * (if it's password protected).
     *
     * @return See {@link PdfValidationResult}
     */
    public static PdfValidationResult validate(File pdfFile, String password) {

        ByteBuffer byteBuffer;
        try {
            byteBuffer = FileUtils.readToByteBuffer(new FileInputStream(pdfFile));

        } catch (IOException e) {
            e.printStackTrace();
            return PdfValidationResult.FILE_NOT_FOUND;
        }

        try {
            new PDFFile(byteBuffer, new PDFPassword(password));
            return PdfValidationResult.VALID;

        } catch (PDFAuthenticationFailureException authException) {
            return PdfValidationResult.INCORRECT_PASSWORD;

        } catch (InvalidPdfException invalidPdfException) {
            return PdfValidationResult.INVALID_PDF;

        } catch (Exception e) {
            e.printStackTrace();
            return PdfValidationResult.UNKNOWN;
        }

    }

    /**
     * Checks if a Pdf is password protected. Prefer using {@link #validate(File, String)
     * validate()} directly if you plan to call it immediately after calling this method
     * (isPasswordProtected()) as it is an expensive method.
     */
    public static boolean isPasswordProtected(File file) {
        return validate(file, null) == PdfValidationResult.INCORRECT_PASSWORD;
    }

    static class FileUtils {

        private static final int bufferSize = 0x20000; // ~130K.

        /**
         * Loads a file to a Document.
         *
         * @return Document
         * @throws IOException on IO error
         */
        static ByteBuffer readToByteBuffer(InputStream inStream) throws IOException {
            byte[] buffer = new byte[bufferSize];
            ByteArrayOutputStream outStream = new ByteArrayOutputStream(bufferSize);
            int read;
            while (true) {
                read = inStream.read(buffer);
                if (read == -1)
                    break;
                outStream.write(buffer, 0, read);
            }
            return ByteBuffer.wrap(outStream.toByteArray());
        }


    }

}