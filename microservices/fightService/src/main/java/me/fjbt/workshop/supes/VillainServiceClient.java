package me.fjbt.workshop.supes;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.faulttolerance.exceptions.TimeoutException;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@RegisterRestClient(configKey = "villain-service")
@Produces(MediaType.TEXT_PLAIN)
public interface VillainServiceClient {

    @Path("/villain")
    @GET
    Villain getVillain();
    
    @Retry(retryOn = TimeoutException.class,
            maxRetries = 4, maxDuration = 4,
            durationUnit = ChronoUnit.SECONDS)
    //@Timeout(value = 2, unit = ChronoUnit.SECONDS) 
    @Timeout(value = 2000)
    @Fallback(fallbackMethod = "getFallbackVillain")
    //@CircuitBreaker(successThreshold = 10, requestVolumeThreshold = 4, failureRatio=0.75,delay = 1000)
    @Path("/villains/random")
    @GET
    Villain findRandom();
    
    @Path("/crash")
    @GET
    String crash();
    
    default Villain getFallbackVillain() {
        final Villain villain = new Villain();
        villain.name = "Jad";
        villain.otherName = "Jaddie the baddie";
        villain.level = 1;
        villain.id = new Random().nextLong();
        villain.picture = "https://github.com/m2gi-behraf/ProjetDevOpsM2GI-fjbt/blob/main/microservices/fightService/src/main/resources/images/Jaddie%20the%20baddie.png";
        return villain;
    }
}
