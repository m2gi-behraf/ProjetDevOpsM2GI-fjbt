package me.fjbt.workshop.supes;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.temporal.ChronoUnit;

@RegisterRestClient(configKey = "hero-service")
@Produces(MediaType.TEXT_PLAIN)
public interface HeroServiceClient {

    @Timeout(value = 2, unit = ChronoUnit.SECONDS) 
    @Fallback(fallbackMethod = "getFallbackHero") 
    
    @Path("/hero")
    @GET
    Hero getHero();

    @Path("/heroes/random")
    @GET
    Hero findRandom();

    @Path("/crash")
    @GET
    String crash();

    @CircuitBreaker(successThreshold = 10, requestVolumeThreshold = 4, failureRatio=0.75,delay = 1000)
    // A simple fallback
    default Hero getFallbackHero() {
        return new Hero("Titi", "Titi  the majestuous bird", 2, "https://github.com/m2gi-behraf/ProjetDevOpsM2GI-fjbt/blob/main/microservices/fightService/src/main/resources/images/tweety.png");
    }
}
