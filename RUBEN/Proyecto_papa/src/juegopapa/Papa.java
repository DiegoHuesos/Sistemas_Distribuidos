package juegopapa;

import java.io.Serializable;

public class Papa implements Serializable {
    private String id;
    private int ttl;

    public Papa(String id, int ttl){
        this.id=id;
        this.ttl=ttl;
    }

    public boolean touch(){
        if(ttl==0) return true;
        ttl--;
        return false;
    }
    public String getId() {
        return id;
    }

    public int getTtl() {
        return ttl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
}
