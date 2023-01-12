package ru.otus.protobuf.hw.service;

import ru.otus.protobuf.generated.RemoteNumberSequenceServiceGrpc;
import ru.otus.protobuf.generated.ServerMessage;


public class RemoteServerServiceImpl extends RemoteNumberSequenceServiceGrpc.RemoteNumberSequenceServiceImplBase {
    private final ServerService realService;

    public RemoteServerServiceImpl(ServerService realService) {
        this.realService = realService;
    }

    @Override
    public void getNumberSequence(ru.otus.protobuf.generated.InitialClientMessage request,
                                  io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.ServerMessage> responseObserver) {
        var allInts = realService.getNumberSequence(request.getFirstValue(), request.getLastValue());
        allInts.forEach(u -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            responseObserver.onNext(Int2ServerMessage(u));
        });
        responseObserver.onCompleted();

    }

    private ServerMessage Int2ServerMessage(Integer i) {
        return ServerMessage.newBuilder()
                .setCurrentValue(i)
                .build();
    }
}
