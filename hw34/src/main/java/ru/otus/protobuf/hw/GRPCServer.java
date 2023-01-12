package ru.otus.protobuf.hw;


import io.grpc.ServerBuilder;
import ru.otus.protobuf.hw.service.RemoteServerServiceImpl;
import ru.otus.protobuf.hw.service.ServerServiceImpl;

import java.io.IOException;

public class GRPCServer {

    public static final int SERVER_PORT = 8191;

    public static void main(String[] args) throws IOException, InterruptedException {

        var service = new ServerServiceImpl();
        var remoteService = new RemoteServerServiceImpl(service);

        var server = ServerBuilder
                .forPort(SERVER_PORT)
                .addService(remoteService).build();
        server.start();
        System.out.println("server waiting for client connections...");
        server.awaitTermination();
    }
}
