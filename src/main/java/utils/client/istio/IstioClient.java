package utils.client.istio;
import me.snowdrop.istio.client.DefaultIstioClient;

public class IstioClient {
    private volatile static me.snowdrop.istio.client.IstioClient instance;

    public static me.snowdrop.istio.client.IstioClient getInstance(){
        if(instance == null){
            synchronized (IstioClient.class){
                if(instance == null){
                    instance = new DefaultIstioClient();
                }
            }
        }
        return instance;
    }
}
