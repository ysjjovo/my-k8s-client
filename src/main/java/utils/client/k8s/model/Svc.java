package utils.client.k8s.model;

import java.util.Map;

public class Svc {
    private String namespace;
    private String name;
    private Map<String,String> labels;
    private Map<String,String> selector;
    private SvcType type;
    public Svc(){}
    public Svc(String namespace, String name, Map<String, String> labels, Map<String, String> selector) {
        this.namespace = namespace;
        this.name = name;
        this.labels = labels;
        this.selector = selector;
    }
    public String getNamespace() {
        return namespace;
    }
    public Svc setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }
    public Svc setName(String name) {
        this.name = name;
        return this;
    }
    public String getName() {
        return name;
    }
    public Svc setLabels(Map<String, String> labels) {
        this.labels = labels;
        return this;
    }

    public Map<String, String> getLabels() {
        return labels;
    }
    public Svc setSelector(Map<String, String> selector) {
        this.selector = selector;
        return this;
    }
    public Map<String, String> getSelector() {
        return selector;
    }
    public Svc setType(SvcType type) {
        this.type = type;
        return this;
    }
    public SvcType getType() {
        return type;
    }
    public String getTypeStr() {
        return type.name();
    }
}
