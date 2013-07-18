package storm.finagle.drpc;

import org.apache.thrift.protocol.TBinaryProtocol;

import storm.finagle.drpc.generated.DistributedRPC;

import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ClientBuilder;
import com.twitter.finagle.thrift.ThriftClientFramedCodec;
import com.twitter.finagle.thrift.ThriftClientRequest;
import com.twitter.util.Duration;

public class StormDrpcClient {
	
	private Service<ThriftClientRequest, byte[]> clientConnection;
	private DistributedRPC.ServiceIface client;	
	
	public StormDrpcClient(String hosts,
							long tcpConnectTimeout, 
							int tcpRetryCount, 
							int hostConnectionLimit)
	{
		clientConnection = ClientBuilder.safeBuild(ClientBuilder.get()
				.hosts(hosts)
				.tcpConnectTimeout(new Duration(tcpConnectTimeout))
				.retries(tcpRetryCount).codec(ThriftClientFramedCodec.get())
				.hostConnectionLimit(hostConnectionLimit));	

		client = new DistributedRPC.ServiceToClient(clientConnection,
				new TBinaryProtocol.Factory());
	}
	
	public String execute(String functionName, String funcArgs)
	{
		return client.execute(functionName, funcArgs).get();
	}
	
	public void destroy() throws Exception {
		clientConnection.release();
	}
}
