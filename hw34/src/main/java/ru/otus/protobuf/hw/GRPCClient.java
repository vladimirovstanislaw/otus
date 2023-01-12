package ru.otus.protobuf.hw;

import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import ru.otus.protobuf.generated.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class GRPCClient {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8191;

    public static void main(String[] args) throws InterruptedException {
        var channel = ManagedChannelBuilder.forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext()
                .build();

        AtomicInteger lastNumberFromServer = new AtomicInteger(0);
        AtomicInteger currentValue = new AtomicInteger(0);
        var latch = new CountDownLatch(1);

        var nonBlockingStub = RemoteNumberSequenceServiceGrpc.newStub(channel);
        nonBlockingStub.getNumberSequence(InitialClientMessage.newBuilder().setFirstValue(0).setLastValue(30).build(), new StreamObserver<>() {
            @Override
            public void onNext(ServerMessage sm) {
                lastNumberFromServer.set(sm.getCurrentValue());
                System.out.printf("{A number from server: %d}%n",
                        lastNumberFromServer.get());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println(t);
            }

            @Override
            public void onCompleted() {
                System.out.println("\nDatastreaming done.");
                latch.countDown();
            }
        });

        for (int i = 0; i < 50; i++) {
            currentValue.set(currentValue.get() + lastNumberFromServer.get() + 1);
            System.out.printf("{currentValue: %d}%n",
                    currentValue.get());
            lastNumberFromServer.set(0);
            Thread.sleep(1000);
        }
        latch.await();
        System.out.println("We all done.");
        channel.shutdown();
    }
}
