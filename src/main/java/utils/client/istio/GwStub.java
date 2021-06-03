package utils.client.istio;


import me.snowdrop.istio.api.networking.v1alpha3.*;
import utils.client.istio.model.Gw;

import java.util.ArrayList;
import java.util.List;

public class GwStub {
    private me.snowdrop.istio.client.IstioClient client;
    public GwStub(){
        client = IstioClient.getInstance();
    }
    public Gateway createOrReplace(Gw gw){
        Server server = new Server();
        Port port = new Port(gw.getName(), gw.getPort(), gw.getProtoStr(), null);
        server.setPort(port);
        List<String> hosts = new ArrayList<>();
        hosts.add(gw.getHost());
        server.setHosts(hosts);
        return client.v1alpha3Gateway().inNamespace(gw.getNamespace())
                .createOrReplace(new GatewayBuilder()
                .withNewMetadata()
                .withName(gw.getName())
                .withLabels(gw.getLabels())
                .endMetadata()
                .withNewSpec()
                .addToSelector("istio", "ingressgateway")
                .withServers(server)
                .endSpec().build());
    }

    public GatewayList list(String ns){
        return client.v1alpha3Gateway().inNamespace(ns).list();
    }

    public Gateway get(String ns, String name){
        return client.v1alpha3Gateway().inNamespace(ns).withName(name).get();
    }

    public boolean del(String ns, String name){
        return client.v1alpha3Gateway().inNamespace(ns).withName(name).delete();
    }
}
