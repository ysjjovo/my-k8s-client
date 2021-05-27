package utils.client.k8s;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class K8sClient {
    private volatile static KubernetesClient instance;
    public static KubernetesClient getInstance(){
        if(instance == null){
            synchronized (K8sClient.class){
                if(instance == null){
                    instance = new DefaultKubernetesClient();
                }
            }
        }
        return instance;
    }
}
