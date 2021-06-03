package utils.client.istio.model;

import java.util.Map;

public class Vs {
    private String namespace;
    private String name;
    private Map<String,String> labels;
    private String host;
    private String gateway;
    private String prefix;
    public String getNamespace() {
        return namespace;
    }

    public Vs setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public String getName() {
        return name;
    }

    public Vs setName(String name) {
        this.name = name;
        return this;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public Vs setLabels(Map<String, String> labels) {
        this.labels = labels;
        return this;
    }

    public String getHost() {
        return host;
    }

    public Vs setHost(String host) {
        this.host = host;
        return this;
    }

    public String getGateway() {
        return gateway;
    }

    public Vs setGateway(String gateway) {
        this.gateway = gateway;
        return this;
    }
    public String getPrefix() {
        return prefix;
    }

    public Vs setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }
}
