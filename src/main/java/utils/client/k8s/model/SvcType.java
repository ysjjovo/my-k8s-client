package utils.client.k8s.model;

public enum SvcType {
    ClusterIP,
    LoadBalancer,
    NodePort,
    ExternalName,
    ;
}
