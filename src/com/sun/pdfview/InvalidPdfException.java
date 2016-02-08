package com.sun.pdfview;

/**
 * Raised when an invalid Pdf was supplied. It could either not have a ".PDF" extension or be
 * an invalid file disguised with ".PDF" extension.
 */
public class InvalidPdfException extends PDFParseException {

    public InvalidPdfException(String msg) {
        super(msg);
    }

    public InvalidPdfException(String msg, Throwable cause) {
        super(msg, cause);
    }

}