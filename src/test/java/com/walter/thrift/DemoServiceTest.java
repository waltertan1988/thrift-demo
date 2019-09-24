package com.walter.thrift;

import com.walter.MainServer;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.junit.Test;

public class DemoServiceTest {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int TIMEOUT = 5000;

    @Test
    public void testDemoService() throws TException {
        TTransport transport = new TSocket(SERVER_IP, MainServer.PORT, TIMEOUT);
        // 协议要和服务端一致
        TProtocol protocol = new TBinaryProtocol(transport);
        DemoService.Client client = new DemoService.Client(protocol);
        transport.open();
        String result = client.sayHello("Walter");
        System.out.println("result: " + result);
        transport.close();
    }
}
