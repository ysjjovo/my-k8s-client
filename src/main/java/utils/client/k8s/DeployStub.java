package utils.client.k8s;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import utils.client.k8s.model.Container;
import utils.client.k8s.model.Deploy;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.apps.*;
import io.fabric8.kubernetes.client.KubernetesClient;

public class DeployStub {
    private KubernetesClient client;
    public DeployStub(){
        this.client = K8sClient.getInstance();
    }
    public Deployment createOrReplace(Deploy d, utils.client.k8s.model.Container...c){
        PodTemplateSpecFluent.SpecNested<DeploymentSpecFluent.TemplateNested<DeploymentFluent.SpecNested<DeploymentBuilder>>> builder = new DeploymentBuilder()
                .withNewMetadata()
                .addToLabels(d.getLabels())
                .withName(d.getName())
                .endMetadata()
                .withNewSpec()
                .withReplicas(d.getReplicas())
                .withNewSelector()
                .addToMatchLabels(d.getSelector())
                .endSelector()
                .withNewTemplate()
                .withNewMetadata()
                .addToLabels(d.getLabels())
                .endMetadata().withNewSpec();

        for(Container e: c){
            builder.addNewContainer()
                    .withName(e.getName())
                    .withImage(e.getImage())
                    .withImagePullPolicy(e.getPullPolicyStr())
                    .withName(e.getName())
                    .addNewPort()
                    .withContainerPort(e.getPort())
                    .endPort()
                    .endContainer();
        }
        return client.apps().deployments().inNamespace(d.getNamespace()).createOrReplace(builder.endSpec().endTemplate().endSpec().build());
    }

    public boolean del(String ns, String name){
        return client.apps().deployments().inNamespace(ns).withName(name).delete();
    }

    public DeploymentList list(String ns){
        return client.apps().deployments().inNamespace(ns).list();
    }
    public Deployment get(String ns, String name){
        return client.apps().deployments().inNamespace(ns).withName(name).get();
    }
    public Deployment scale(String ns, String name, Integer replicas){
        return client.apps().deployments().inNamespace(ns).withName(name).scale(replicas, true);
    }
    public Deployment updateImage(String ns, String name, String image){
        return client.apps().deployments().inNamespace(ns).withName(name).rolling().updateImage(image);
    }
}

