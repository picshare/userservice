package picshare.userservice.storitve.beans;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@Health
@ApplicationScoped
public class HealthBean implements HealthCheck {
    private static final Logger LOG = Logger.getLogger(HealthBean.class.getSimpleName());

    @Inject
    private DummyBean dB;

    @Override
    public HealthCheckResponse call() {
            if (dB.isHealty()) {
                return HealthCheckResponse.named(HealthBean.class.getSimpleName()).up().build();
            } else {
                return HealthCheckResponse.named(HealthBean.class.getSimpleName()).down().build();

            }
    }
}
