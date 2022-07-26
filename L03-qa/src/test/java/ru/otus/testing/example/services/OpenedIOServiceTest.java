package ru.otus.testing.example.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
@DisplayName("OpenedConsoleIOService ")
public class OpenedIOServiceTest {
    private static final String EOL = System.lineSeparator();
    private static final String TEXT_TO_PRINT1 = "hate windows-1252";
    private static final String TEXT_TO_PRINT2 = "hate windows-1252. return of the king";

    private ByteArrayOutputStream bos;
    private IOService ioService;

    @BeforeEach
    void setUp() {
        System.out.println(Thread.currentThread().getName());

        bos = new ByteArrayOutputStream();
        ioService = new OpenedStreamsIOService(new PrintStream(bos), System.in);
    }

    @DisplayName("должно печатать \"" + TEXT_TO_PRINT1 + "\"")
    @Test
    void shouldPrintOnlyFirstCreedLine() throws InterruptedException {
        ioService.out(TEXT_TO_PRINT1);
        Thread.sleep(1000);
        assertThat(bos.toString()).isEqualTo(TEXT_TO_PRINT1 + EOL);
    }

    @DisplayName("должно печатать \"" + TEXT_TO_PRINT2 + "\"")
    @Test
    void shouldPrintOnlySecondCreedLine() {
        ioService.out(TEXT_TO_PRINT2);
        assertThat(bos.toString()).isEqualTo(TEXT_TO_PRINT2 + EOL);
    }

}
