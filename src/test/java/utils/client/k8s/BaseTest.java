package utils.client.k8s;

import utils.client.k8s.model.Ns;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseTest {
    static KubernetesClient client;
    static NamespaceStub namespaceStub;
    static ServiceStub serviceStub;
    static DeployStub deployStub;
    static String namespaceName = "test";
    static String svcName = "test-svc";
    static String deployName = "test-deploy";
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
        System.setProperty(Config.KUBERNETES_KUBECONFIG_FILE, "/Users/xxx/projects/my-k8s-client/.kubeconfig");
        client = new DefaultKubernetesClient();
        namespaceStub = new NamespaceStub(client);
        serviceStub = new ServiceStub(client);
        deployStub = new DeployStub(client);
        namespaceStub.createOrReplace(ns);
    }
    @AfterAll
    static void destroy(){
        assertTrue(namespaceStub.del(namespaceName));
    }
}
