import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.cp.CPMap;

import java.util.Map;

public final class Client4P {
    public static void main(String[] args) throws Exception {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("127.0.0.1");
        clientConfig.getNetworkConfig().setSmartRouting(false);

        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        Map<String, String> map = client.getMap("map");
        map.put("1", "Tokyo");
        map.put("2", "Paris");
        map.put("3", "New York");
        System.out.println("Finished loading map");

        CPMap<String, String> cpMap = client.getCPSubsystem().getMap("cp-map");
        cpMap.set("1", "Japan");
        cpMap.set("2", "France");
        cpMap.set("3", "USA");

        HazelcastClient.shutdownAll();
    }
}

