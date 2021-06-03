package utils.client.istio;

import me.snowdrop.istio.api.networking.v1alpha3.*;
import utils.client.istio.model.Dest;
import utils.client.istio.model.Vs;

public class VsStub {
    private me.snowdrop.istio.client.IstioClient client;
    public VsStub(){
        client = IstioClient.getInstance();
    }
    public VirtualService createOrReplace(Vs vs, Dest... dests){
        VirtualServiceSpecFluent.HttpNested<VirtualServiceFluent.SpecNested<VirtualServiceBuilder>> builder = new VirtualServiceBuilder()
                .withNewMetadata()
                .withName(vs.getName())
                .withLabels(vs.getLabels())
                .endMetadata()
                .withNewSpec()
                .withHosts(vs.getHost())
                .withGateways(vs.getGateway())
                .addNewHttp().addNewMatch()
                .withNewUri().withNewPrefixMatchType(vs.getPrefix()).endUri()
                .endMatch();

        for(Dest dest: dests){
            builder.addNewRoute()
                    .withWeight(dest.getWeight())
                    .withNewDestination()
                    .withSubset(dest.getSubset())
                    .withHost(dest.getInnerHost())
                    .withNewPort()
                    .withNumber(dest.getPort())
                    .endPort()
                    .endDestination()
                    .endRoute()
                    .endHttp();
        }
        return client.v1alpha3VirtualService()
                .inNamespace(vs.getNamespace())
                .createOrReplace(builder.endHttp().endSpec().build());
    }


    public VirtualServiceList list(String ns){
        return client.v1alpha3VirtualService().inNamespace(ns).list();
    }

    public VirtualService get(String ns, String name){
        return client.v1alpha3VirtualService().inNamespace(ns).withName(name).get();
    }

    public boolean del(String ns, String name){
        return client.v1alpha3VirtualService().inNamespace(ns).withName(name).delete();
    }
}
