package com.example.mockitoadvanced;

/**
 * A simple file-reading abstraction, mocked in tests.
 * NOTE: this is a custom interface (not java.io.FileReader) so that the
 * exercise's solution code compiles as written, using a mockable type.
 */
public interface FileReader {
    String read();
}
