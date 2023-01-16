package me.fjbt.workshop.supes;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;

@RegisterRestClient(configKey = "hero-service")
@Produces(MediaType.APPLICATION_JSON)
public interface HeroServiceClient {
    
    @CircuitBreaker(successThreshold = 10, requestVolumeThreshold = 4, failureRatio=0.75,delay = 1000)
    @Timeout(value = 500)
    @Fallback(fallbackMethod = "getFallbackHero") 
    @Path("/heroes/random")
    @GET
    Hero findRandom();
    
    default Hero getFallbackHero() {
        final Hero hero = new Hero();
        hero.name = "Titi";
        hero.otherName = "Titi  the majestuous bird";
        hero.level = 2;
        hero.id = new Random().nextLong();
        hero.picture = "https://github.com/m2gi-behraf/ProjetDevOpsM2GI-fjbt/blob/main/microservices/fightService/src/main/resources/images/tweety.jpg?raw=true";
        return hero;
    }
}
