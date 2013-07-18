package storm.finagle.drpc;

import org.apache.thrift.TException;
import org.junit.Test;

import storm.finagle.drpc.StormDrpcClient;
import storm.finagle.drpc.generated.DRPCExecutionException;


public class StormDrpcClientTestSkip {
	
	@Test
	public void doExecuteTest() throws TException, DRPCExecutionException,
			InterruptedException {
		
		String hosts = "storm02:3772,storm03:3772,storm04:3772";
		long tcpConnectTimeout = 5000;
		int tcpRetryCount = 2;
		int hostConnectionLimit = 10;

		StormDrpcClient stormDrpcClient = new StormDrpcClient(hosts, tcpConnectTimeout, tcpRetryCount, hostConnectionLimit);
		
		String result = stormDrpcClient.execute("electricPowerDrpc", "11,26,27,28,29,30,31,41,42,43,44,45,46,47,48,50");
		System.out.println("drpc result: [" + result + "]");	
	}
}
