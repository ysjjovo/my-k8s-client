package utils.client.k8s;

import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.Config;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utils.client.enums.ImagePullPolicy;
import utils.client.enums.Proto;
import utils.client.enums.SvcType;
import utils.client.k8s.model.*;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.Service;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class K8sTest {
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
        Svc svc = newSvc();
        SvcPort port = newSvcPort();
        Service inserted = serviceStub.createOrReplace(svc, port);
        assertEquals(svc.getName(), inserted.getMetadata().getName());
        svc.setType(SvcType.NodePort);
        port.setNodePort(30000);
        Service updated = serviceStub.createOrReplace(svc, port);
        assertEquals(svc.getTypeStr(), updated.getSpec().getType());
        assertTrue(serviceStub.del(namespaceName, svcName));
    }

    public static SvcPort newSvcPort() {
        return new SvcPort().setName("http").setPort(80).setTargetPort(8080).setProtocol(Proto.TCP);
    }

    public static Svc newSvc() {
        return new Svc().setNamespace(namespaceName).setName(svcName).setType(SvcType.ClusterIP).setLabels(labels).setSelector(labels);
    }

    @Test
    void deployTest(){
        labels.put(k1, v1);
        Deploy d = newDeploy();
        Container c = newContainer();
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

    public static Deploy newDeploy() {
        return new Deploy().setNamespace(namespaceName).setName(deployName).setLabels(labels).setReplicas(1)
                .setSelector(labels);
    }

    public static Container newContainer() {
        return new Container().setName("app").setImage("nginx").setPort(80).setPullPolicy(ImagePullPolicy.IfNotPresent);
    }

    static NamespaceStub namespaceStub;
    static ServiceStub serviceStub;
    static DeployStub deployStub;
    static String namespaceName = "test";
    static String svcName = "test";
    static String deployName = "test";
    static String k1 = "k1";
    static String v1 = "v1";
    static String v2 = "v2";
    static Map<String,String> labels= new HashMap<>();
    static {
        labels.put(k1, v1);
    }
    static Ns ns = new Ns().setName(namespaceName).setLabels(labels);
    @BeforeAll
    static void setUp(){
        System.setProperty(Config.KUBERNETES_KUBECONFIG_FILE, System.getProperty("user.home") + "/.kube/config");
        namespaceStub = new NamespaceStub();
        serviceStub = new ServiceStub();
        deployStub = new DeployStub();
        namespaceStub.createOrReplace(ns);
    }
    @AfterAll
    static void destroy(){
        assertTrue(namespaceStub.del(namespaceName));
    }
}
