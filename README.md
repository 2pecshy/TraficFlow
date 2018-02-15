Projet Simulateur de trafic routier
===================================

Le but de ce projet est de réaliser un simulateur basé sur les agents, pour
simuler un trafic routier prenant en compte des évènements comme le mouvement
pendulaire. Cet outil doit servir d'aide à la décision pour de potentiels travaux
d'amélioration ou de création de nouvelles voies de circulation.

Les clients sont intéréssés pour l'utiliser de manière intensive et ponctuel (nul
ne prévoit des travaux tout au long d'une année). C'est pourquoi nous fournissons
notre solution comme un SaaS disponible via internet avec un sytème de forfait.

Nous avons réalisé une architecture en services afin de découplés les fonctionnalités.
On retrouve un service dédié à la simulation, un service permettant à l'utilisateur
d'envoyer la configuration de trafic routier qu'il souhaite (carte de la zone, différents
évènements à traiter, durée de la simulation etc...), on retrouve ensuite un service Observateur
grâce auquel l'utilisateur peut obtenir des informations en temps réel sur l'état de sa
simulation (sans que l'utilisateur n'intéragisse directement avec le simulateur) et un 
service "FACADE" qui sert de LoadBalancer devant le simulateur et de filtres sur les 
requêtes provent d'internet (dans un soucis encore d'isoler la simulation qui est le coeur
de notre architecture).

Nous utilisons le framework _RabbitMq_ comme sytème de Message Broker sous forme de 
queues de communication entre les services. De ce fait, les services ne se font pas
d'appels directes, ce qui nous permet d'ajouter/retirer/modifier les différentes 
couches de services ainsi qu'ajouter ou supprimer des communications inter-services
de manière aisée et localisée. 

# Lancer le projet avec mvn:springboot

* Se placer à la racine du projet
* Lancer le script exec.sh
* Executer une requête POST sur l'url _"http://localhost:2225/config"_
(_requête body ex:_ { 
    "simulationLength" : 5,
     "simulationStart" : 3,
      "HOVLanes" : "true",
       "migrationPendulaire" : "False" ,
        "mapLink": "http://api.openstreetmap.org/api/0.6/map?bbox=11.54,48.14,11.543,48.145"
        })
        
## URL des services 
+ __WebConfiguration__ : http://localhost:2225/config
+ __Facade__ : http://localhost:2223/facade
+ __Simulateur__ : http://localhost:2226/simulateur
+ __Observateur__ : http://localhost:2227/observateur
        
# Lancer le projet avec docker

* Se placer à la racine du projet
* Lancer le script "docker_automagic_button.sh"
* Executer une requête POST sur l'url _"http://localhost:2225/configuration/config"_
(_requête body ex:_ { 
    "simulationLength" : 5,
     "simulationStart" : 3,
      "HOVLanes" : "true",
       "migrationPendulaire" : "False" ,
        "mapLink": "http://api.openstreetmap.org/api/0.6/map?bbox=11.54,48.14,11.543,48.145"
        })
        
## URL des services 
+ __WebConfiguration__ : http://localhost:2225/configuration/config
+ __Facade__ : http://localhost:2223/facade/facade
+ __Simulateur__ : http://localhost:2226/simulateur/simulateur
+ __Observateur__ : http://localhost:2227/observateur/observateur

./ipconfig.sh wlan0 docko -> to launch docker
  
Service Database :

Base MongoDB
Pour utiliser le service database, il faut installer mongodb et le lancer

Pour visualiser l'interieur de la base de données :

GET http://localhost:2229/database
GET http://localhost:2229/database/id


(Format des objets) A CONTINUER
input du programme : objet carte
1 attributs
- liste d'adjacence (arraylist d'arraylist de Route)

éditeur de carte : copie de la carte, pouvoir modifier la liste d'adjacence et la matrice des couts

simulateur : il s'occupe de modifier la carte en graphe et de faire les calculs

sortie du simulateur : objet de type résultat / compte rendu

- strings (max flow, liste arêtes, proposition d'amélioration des arêtes...)
- maps : map de base (type carte) et carte éditée (type carte)

pour l'enregistrer : scinde en 3 
-simu1.data : contient les strings
-simu1.base : map de base
-simu1.edited : map éditée

Justification Base SQL
Map originale -> pouvoir la modifier mais retrouver l'originale
elle peut avoir n map édited
Chaque map éditée : 1 résultat
tous les résultats sont indépendants