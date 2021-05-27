package utils.client.k8s;

import utils.client.k8s.model.Ns;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceBuilder;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.client.KubernetesClient;

public class NamespaceStub {
    private KubernetesClient client;
    public NamespaceStub(){
        this.client = K8sClient.getInstance();
    }
    public Namespace createOrReplace(Ns ns){
       return client.namespaces().createOrReplace(new NamespaceBuilder()
                .withNewMetadata()
                .addToLabels(ns.getLabels())
                .withName(ns.getName())
                .endMetadata()
                .build());
    }
    public boolean del(String name){
        return client.namespaces().withName(name).delete();
    }
    public NamespaceList list(){
        return client.namespaces().list();
    }
    public Namespace get(String name){
        return client.namespaces().withName(name).get();
    }
}

