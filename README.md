A trimmed down version of [PDFRenderer](https://github.com/katjas/PDFrenderer), which only validates a PDF's password.

#### Usage

```java
File pdf = ...
String password = ...

PdfValidationResult validationResult = PdfValidator.validate(pdf, password);
switch(validationResult) {
  case VALID:
  case INVALID_PDF:
  case INCORRECT_PASSWORD:
  case UNKNOWN:
  case FILE_NOT_FOUND:	
}
```

If you only want to check if a PDF is password protected, then `PdfValidator.isPasswordProtected()` can also be used. Don't use them together though.
