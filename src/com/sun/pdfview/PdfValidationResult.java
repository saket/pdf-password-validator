package com.sun.pdfview;

public enum PdfValidationResult {
    /**
     * Valid Pdf + Valid password (if it was password protected)
     */
    VALID,

    /**
     * The file is not a valid Pdf
     */
    INVALID_PDF,

    /**
     * The Pdf is password protected and the entered password is wrong.
     */
    INVALID_PASSWORD,

    /**
     * Bastard uploaded a file that we just cannot understand.
     */
    UNKNOWN,

    /**
     * Couldn't find the file provided
     */
    FILE_NOT_FOUND,
}
