package com.example.mockitoadvanced;

/**
 * A simple file-writing abstraction, mocked in tests.
 * NOTE: this is a custom interface (not java.io.FileWriter) so that the
 * exercise's solution code compiles as written, using a mockable type.
 */
public interface FileWriter {
    void write(String content);
}
