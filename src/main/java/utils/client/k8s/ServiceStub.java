package utils.client.k8s;

import utils.client.k8s.model.Svc;
import utils.client.k8s.model.SvcPort;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.KubernetesClient;

public class ServiceStub {
    private KubernetesClient client;
    public ServiceStub(){
        this.client = K8sClient.getInstance();
    }
    public Service createOrReplace(Svc svc, SvcPort...ports){
        ServiceFluent.SpecNested<ServiceBuilder> builder = new ServiceBuilder()
                .withNewMetadata()
                .addToLabels(svc.getLabels())
                .withName(svc.getName())
                .endMetadata()
                .withNewSpec()
                .withType(svc.getTypeStr())
                .withSelector(svc.getSelector());
        for(SvcPort e: ports){
            builder.addNewPort()
                    .withName(e.getName())
                    .withProtocol(e.getProtocolStr())
                    .withPort(e.getPort())
                    .withTargetPort(new IntOrString(e.getTargetPort()))
                    .withNodePort(e.getNodePort())
                    .endPort();
        }
        return client.services().inNamespace(svc.getNamespace()).createOrReplace(builder.endSpec().build());
    }
    public boolean del(String ns, String name){
        return client.services().inNamespace(ns).withName(name).delete();
    }

    public ServiceList list(String ns){
        return client.services().inNamespace(ns).list();
    }
    public Service get(String ns, String name){
        return client.services().inNamespace(ns).withName(name).get();
    }
}

