package utils.client.istio.model;

import java.util.Map;

public class VirtualService {
    private String namespace;
    private String name;
    private Map<String,String> labels;
    private String host;
    private String prefix;
    private String serviceName;
    private String servicePort;

    public String getNamespace() {
        return namespace;
    }

    public VirtualService setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public String getName() {
        return name;
    }

    public VirtualService setName(String name) {
        this.name = name;
        return this;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public VirtualService setLabels(Map<String, String> labels) {
        this.labels = labels;
        return this;
    }

    public String getHost() {
        return host;
    }

    public VirtualService setHost(String host) {
        this.host = host;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public VirtualService setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public VirtualService setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getServicePort() {
        return servicePort;
    }

    public VirtualService setServicePort(String servicePort) {
        this.servicePort = servicePort;
        return this;
    }

}
