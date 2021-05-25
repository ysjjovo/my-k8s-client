package utils.client.k8s;

import io.fabric8.kubernetes.api.model.apps.Deployment;
import utils.client.k8s.model.*;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.Service;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StubTest extends BaseTest {
    @Test
    void namespaceTest(){
        Namespace s = namespaceStub.get(namespaceName);
        assertEquals(v1, s.getMetadata().getLabels().get(k1));
        labels.put(k1, v2);
        Namespace updated = namespaceStub.createOrReplace(ns);
        assertEquals(v2, updated.getMetadata().getLabels().get(k1));
    }
    @Test
    void serviceTest(){
        labels.put(k1, v1);
        Svc svc = new Svc().setNamespace(namespaceName).setName(svcName).setType(SvcType.ClusterIP).setLabels(labels).setSelector(labels);
        SvcPort port =new SvcPort().setName("http").setPort(80).setTargetPort(8080).setProtocol(Proto.TCP);
        Service inserted = serviceStub.createOrReplace(svc, port);
        assertEquals(svc.getName(), inserted.getMetadata().getName());
        svc.setType(SvcType.NodePort);
        port.setNodePort(30000);
        Service updated = serviceStub.createOrReplace(svc, port);
        assertEquals(svc.getTypeStr(), updated.getSpec().getType());
        assertTrue(serviceStub.del(namespaceName, svcName));
    }
    @Test
    void deployTest(){
        labels.put(k1, v1);
        Deploy d = new Deploy().setNamespace(namespaceName).setName(deployName).setLabels(labels).setReplicas(1)
                .setSelector(labels);
        Container c = new Container().setName("app").setImage("nginx").setPort(80).setPullPolicy(ImagePullPolicy.IfNotPresent);
        Deployment inserted = deployStub.createOrReplace(d, c);
        assertEquals(d.getName(), inserted.getMetadata().getName());
        d.setReplicas(2);
        Deployment updated = deployStub.createOrReplace(d, c);
        assertEquals(2, updated.getSpec().getReplicas());
        Deployment scale = deployStub.scale(namespaceName, deployName, 3);
        assertEquals(3, scale.getSpec().getReplicas());
        String image = "nginx:my-latest";
        Deployment updateImaged = deployStub.updateImage(namespaceName, deployName, image);
        assertEquals(image, updateImaged.getSpec().getTemplate().getSpec().getContainers().get(0).getImage());
        assertTrue(deployStub.del(namespaceName, deployName));
    }
}
