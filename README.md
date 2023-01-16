# ProjetDevOpsM2GI-fjbt
## Description
Projet de mise en place d'un système autour de l'architecture micro-services.
Réalisé à l'aide de Docker, Kubernetes, Quarkus, etc... Le système est protégé des pannes, possède un moniteur de contrôle de santé générale, de métriques...

## Fonctionnement
  1. Redéfinissez l'environnement docker pour minikube
  `eval $(minikube docker-env)`
  2. Recompilez chacun des 3 services et déployez les
  `mvn clean package; docker build -f src/main/docker/Dockerfile -t workshop/villain-service .; kubectl delete deployment villain-service; kubectl apply -f kubernetes/`
  3. Vérifiez la santé des services avec la commande `kubectl get pods -w`
  4. Accédez à l'url du fight-service : `minikube service fight-service --url`
  
## Authors
- Barbara Prapant
- Jad Jawlakh
- Thibault Ferrer
- Fabien Behra
