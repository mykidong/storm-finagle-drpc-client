# storm-finagle-drpc-client

Storm DRPC Client which supports load balancing, retry, connection pooling, etc using Finagle Thrift.


## Integration with Finagle Thrift

To integrate storm drpc client with finagle, finagle thrift compiler 0.5.0 (https://github.com/mariusaeriksen/thrift-0.5.0-finagle/) is used to generate storm thrift java classes.

The drpc part classes among the generated storm thrift classes reside in the pacakge 'storm.finagle.drpc.generated'.

## Usage

```
    String hosts = "storm02:3772,storm03:3772,storm04:3772";
    long tcpConnectTimeout = 5000;
    int tcpRetryCount = 2;
    int hostConnectionLimit = 10; 
  
    StormDrpcClient stormDrpcClient = new StormDrpcClient(hosts, tcpConnectTimeout, tcpRetryCount, hostConnectionLimit);		
    String result = stormDrpcClient.execute("electricPowerDrpc", "11,26,27,28,29,30,31,41,42,43,44,45,46,47,48,50");
    System.out.println("drpc result: [" + result + "]");	
```
