package utils.client.istio;

import me.snowdrop.istio.api.networking.v1alpha3.Gateway;
import me.snowdrop.istio.api.networking.v1alpha3.VirtualService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.client.enums.IstioProto;
import utils.client.istio.model.Dest;
import utils.client.istio.model.Gw;
import utils.client.istio.model.Vs;
import utils.client.k8s.DeployStub;
import utils.client.k8s.K8sTest;
import utils.client.k8s.NamespaceStub;
import utils.client.k8s.ServiceStub;
import utils.client.k8s.model.Ns;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IstioTest {
    final static private String nsName = "test";
    final static private String gwName = "test";
    final static private String vsName = "test";
    final static private String host = "test.com";
    final static private String innerHost, serviceName;
    final static private Integer port = 80;
    final static private String k1 = "k1";
    final static private String v1 = "v1";
    static private Map<String,String> labels= new HashMap<>();
    static {
        innerHost = serviceName = "test";
        labels.put(k1, v1);
    }
    static private NamespaceStub namespaceStub;
    static private GwStub gwStub;
    static private VsStub vsStub;
    static ServiceStub serviceStub;
    static DeployStub deployStub;
    @Test
    void gatewayTest(){
        Gw gw = newGw();
        Gateway created = gwStub.createOrReplace(gw);
        assertEquals(gw.getName(), created.getMetadata().getName());
        gw.setPort(81);
        Gateway updated = gwStub.createOrReplace(gw);
        assertEquals(gw.getPort(), updated.getSpec().getServers().get(0).getPort().getNumber());
        assertEquals(1, gwStub.list(gwName).getItems().size());
        assertEquals(gwName, gwStub.get(nsName, gwName).getMetadata().getName());
        assertEquals(true, gwStub.del(nsName, gwName));
    }

    private Gw newGw() {
        return new Gw().setHost(host).setLabels(labels)
                .setName(gwName).setNamespace(nsName).setPort(port)
                .setPortName("http").setProto(IstioProto.HTTP);
    }

    @Test
    void vsTest(){
        deployStub.createOrReplace(K8sTest.newDeploy(), K8sTest.newContainer());
        serviceStub.createOrReplace(K8sTest.newSvc(), K8sTest.newSvcPort());
        gwStub.createOrReplace(newGw());
        Vs vs = new Vs().setGateway(gwName).setHost(host).setLabels(labels)
                .setName(vsName).setNamespace(nsName).setPrefix("/");
        Dest dest = new Dest().setInnerHost(innerHost).setPort(80);
        VirtualService created = vsStub.createOrReplace(vs, dest);
        assertEquals(vs.getName(), created.getMetadata().getName());
        assertEquals(1, vsStub.list(nsName).getItems().size());
        assertEquals(vsName, vsStub.get(nsName, vsName).getMetadata().getName());
        assertEquals(true, vsStub.del(nsName, vsName));
    }

    @BeforeAll
    static void setUp(){
        namespaceStub = new NamespaceStub();
        gwStub = new GwStub();
        vsStub = new VsStub();
        serviceStub = new ServiceStub();
        deployStub = new DeployStub();
        namespaceStub.createOrReplace(new Ns().setName(nsName));
    }
    @AfterAll
    static void destroy(){
        assertTrue(namespaceStub.del(nsName));
    }

}
