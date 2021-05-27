package utils.client.istio.model;

import java.util.Map;

public class Gateway {
    private String namespace;
    private String name;
    private Map<String,String> labels;
    private Map<String,String> selector;

    public String getNamespace() {
        return namespace;
    }

    public Gateway setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public String getName() {
        return name;
    }

    public Gateway setName(String name) {
        this.name = name;
        return this;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public Gateway setLabels(Map<String, String> labels) {
        this.labels = labels;
        return this;
    }

    public Map<String, String> getSelector() {
        return selector;
    }

    public Gateway setSelector(Map<String, String> selector) {
        this.selector = selector;
        return this;

    }
}
