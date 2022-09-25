package ru.otus.testing.example.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
@DisplayName("ClosedIOService")
public class ClosedIOServiceTest {
    private static final String EOL = System.lineSeparator();
    private static final String TEXT_TO_PRINT1 = "hate windows-1252";
    private static final String TEXT_TO_PRINT2 = "hate windows-1252. return of the king";

    private PrintStream backup;
    private ByteArrayOutputStream bos;
//    private IOService ioService;
//
//    @BeforeEach
//    void setUp() {
//        System.out.println(Thread.currentThread().getName());
//        backup = System.out;
//        bos = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(bos));
//        ioService = new ClosedConsoleIOService();
//    }
//
//    @AfterEach
//    void tearDown() {
//
//        System.setOut(backup);
//    }
//
//    @DisplayName("должно печатать \"" + TEXT_TO_PRINT1 + "\"")
//    @Test
//    void shouldPrintOnlyFirstCreedLine() throws InterruptedException {
//        ioService.out(TEXT_TO_PRINT1);
//        Thread.sleep(1000);
//        assertThat(bos.toString()).isEqualTo(TEXT_TO_PRINT1 + EOL);
//    }
//
//    @DisplayName("должно печатать \"" + TEXT_TO_PRINT2 + "\"")
//    @Test
//    void shouldPrintOnlySecondCreedLine() {
//        ioService.out(TEXT_TO_PRINT2);
//        assertThat(bos.toString()).isEqualTo(TEXT_TO_PRINT2 + EOL);
//    }
}
