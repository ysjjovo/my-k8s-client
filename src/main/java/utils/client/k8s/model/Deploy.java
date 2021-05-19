package utils.client.k8s.model;

import java.util.Map;

public class Deploy {
    private String namespace;
    private String name;
    private Map<String,String> labels;
    private Map<String,String> selector;
    private Integer replicas = 1;

    public String getNamespace() {
        return namespace;
    }
    public Deploy setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }
    public Deploy setName(String name) {
        this.name = name;
        return this;
    }
    public String getName() {
        return name;
    }
    public Deploy setLabels(Map<String, String> labels) {
        this.labels = labels;
        return this;
    }

    public Map<String, String> getLabels() {
        return labels;
    }
    public Deploy setSelector(Map<String, String> selector) {
        this.selector = selector;
        return this;
    }
    public Map<String, String> getSelector() {
        return selector;
    }

    public Integer getReplicas() {
        return replicas;
    }

    public Deploy setReplicas(Integer replicas) {
        this.replicas = replicas;
        return this;
    }
}
