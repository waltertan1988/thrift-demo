package com.walter.thrift;

import org.apache.thrift.TException;

public class DemoServiceHandler implements DemoService.Iface {
    @Override
    public String sayHello(String username) throws TException {
        return "Hello " + username;
    }
}
