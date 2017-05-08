# ChatSystem
Chat system created for a course at INSA Toulouse.
Tuteur : Ulrich Matchi Aivodji 

Binôme : 
Julien Coustillas
Alvarro Pascual Legido

Version Java : 8 

Executer avec la classe launcher. Possible erreure de la classe Message implémenté deux foix : ignorer (garder celle dans le package message).  
Un jarfile exécutable est aussi présent dans le dossier du projet.

Fonctionnalités implémentées : 
-Connexion avec un ID. 
-Ecoute du réseau pour la présence d'autres users sur adresse Multicast
-Envoi de messages de présences sur adresse Multicast
-Réception de message TCP
-Envoi de message TCP (testable en local via le localUser)
-Envoi de fichier via TCP
-Envoi à plusieurs destinataires 
-déconnexion 
-interface graphique
	-JList avec pattern Observer (actualisation automatique)
	-TextArea
	-Bouton d'actions
	-sélection de fichier 
	

Fonctionnalités non implémentées : 
-renommer le user local (facile mais oublié)
-affichage des messages suivant le pattern Observer
-Autoscale des objets graphiques
-choix de destination de sauvegarde des fichiers (dédault : mydocs)
-lancer deux fois l'application sur la même machine (problème avec le port mais erreur signalée)

Dans la suite : nous présentons les résultats des tests JUnit des classes UserListmodel et NetworkInteractionController qui sont capitales et qui à elles seules font fonctionner tout le projet.
Nous n'avons pas implémenté JUnit en même temps que nous concevions le projet c'est pourquoi ces tests ne sont pas très variés. 

Nous n'avons pas pu implémenté les tests de groupes. 

------------------------
Tests Sur la classe UserListModel
------------------------
NEW COME : user1
[user1   CONNECTED]
NEW COME : user2
[user1   CONNECTED, user2   CONNECTED]
------------------------
Test ajout de User
------------------------
test OK !
------------------------
Test ajout de User existant
------------------------
test OK !
------------------------
Test ajout de User null
------------------------
test OK !
NEW LEAVE : user1
NEW LEAVE : user2
NEW COME : user1
[user1   CONNECTED]
NEW COME : user2
[user1   CONNECTED, user2   CONNECTED]
NEW COME : user3
[user1   CONNECTED, user2   CONNECTED, user3   CONNECTED]
------------------------
Test retrait de User existant
------------------------
NEW LEAVE : user3
test OK !
------------------------
Test retrait de User inexistant
------------------------
test OK !
------------------------
Test retrait de User null
------------------------
test OK !
NEW LEAVE : user1
NEW LEAVE : user2
NEW COME : user1
[user1   CONNECTED]
NEW COME : user2
[user1   CONNECTED, user2   CONNECTED]
------------------------
Test de retour de listes
------------------------
ça plante, je sais pas pourquoi ! 
[user1   CONNECTED, user2   CONNECTED]
[user1   CONNECTED, user2   CONNECTED] listes identiques


------------------------
Tests Sur la classe NetworkInteractionController
------------------------
------------------------
Test de déconnexion
------------------------
Thread presence start !
Test OK !
Connected to Multicast group
------------------------
Test réception de message de présence
------------------------
Thread reception Start
Thread presence start !
NEW COME : localuser
[localuser   CONNECTED]
[localuser   CONNECTED]
Test OK !
------------------------
Test activation de deux messages de présence
------------------------
Thread presence start !
[localuser   CONNECTED]
Test OK !
------------------------
Test message de présence utilisateur null
------------------------
Thread reception Start
Test OK !
------------------------
Test message de déconnexion
------------------------
Test OK !
------------------------	
Test envoie de message pour un utilisateur null
------------------------
Thread receptionMessage Start
Test OK !
------------------------
Test envoie de message null
------------------------
Test OK !
------------------------
Test envoie de 100 messages coucou différents
------------------------
Test OK !
