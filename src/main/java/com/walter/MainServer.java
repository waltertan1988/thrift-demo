package com.walter;

import com.walter.thrift.DemoService;
import com.walter.thrift.DemoServiceHandler;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

public class MainServer {

    public static final int PORT = 7081;

    public static void main(String[] args) throws TTransportException {
        DemoService.Processor processor = new DemoService.Processor<DemoService.Iface>(new DemoServiceHandler());
        // 简单的单线程服务模型，一般用于测试
        TServerTransport transport = new TServerSocket(PORT);
        TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(transport);
        tArgs.processor(processor);
        tArgs.protocolFactory(new TBinaryProtocol.Factory());
        tArgs.transportFactory(new TTransportFactory());
        tArgs.minWorkerThreads(10);
        tArgs.maxWorkerThreads(20);
        TServer server = new TThreadPoolServer(tArgs);
        server.serve();
    }
}
