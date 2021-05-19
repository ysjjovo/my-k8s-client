package utils.client.k8s.model;

import java.util.Map;

public class Ns {
    private String name;
    private Map<String,String> labels;

    public String getName() {
        return name;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public Ns setName(String name) {
        this.name = name;
        return this;
    }

    public Ns setLabels(Map<String, String> labels) {
        this.labels = labels;
        return this;
    }
}
