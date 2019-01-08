package picshare.userservice.storitve.beans;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DummyBean {
    private boolean isHealty = true;

    public boolean isHealty() {
        return isHealty;
    }

    public void setHealty(boolean healty) {
        isHealty = healty;
    }
}
