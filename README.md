# ChatSystem
Chat system created for a course at INSA Toulouse.
Tuteur : 

Bin�me : 
Julien Coustillas
Alvarro Pascual Legido

Version Java : 8 

Executer avec la classe launcher. Possible erreure de la classe Message impl�ment� deux foix : ignorer (garder celle dans le package message).  
Un jarfile ex�cutable est aussi pr�sent dans le dossier du projet.

Fonctionnalit�s impl�ment�es : 
-Connexion avec un ID. 
-Ecoute du r�seau pour la pr�sence d'autres users sur adresse Multicast
-Envoi de messages de pr�sences sur adresse Multicast
-R�ception de message TCP
-Envoi de message TCP (testable en local via le localUser)
-Envoi de fichier via TCP
-Envoi � plusieurs destinataires 
-d�connexion 
-interface graphique
	-JList avec pattern Observer (actualisation automatique)
	-TextArea
	-Bouton d'actions
	-s�lection de fichier 
	

Fonctionnalit�s non impl�ment�es : 
-renommer le user local (facile mais oubli�)
-affichage des messages suivant le pattern Observer
-Autoscale des objets graphiques
-choix de destination de sauvegarde des fichiers (d�dault : mydocs)
-lancer deux fois l'application sur la m�me machine (probl�me avec le port mais erreur signal�e)

Dans la suite : nous pr�sentons les r�sultats des tests JUnit des classes UserListmodel et NetworkInteractionController qui sont capitales et qui � elles seules font fonctionner tout le projet.
Nous n'avons pas impl�ment� JUnit en m�me temps que nous concevions le projet c'est pourquoi ces tests ne sont pas tr�s vari�s. 

Nous n'avons pas pu impl�ment� tests de groupes. 

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
�a plante, je sais pas pourquoi ! 
[user1   CONNECTED, user2   CONNECTED]
[user1   CONNECTED, user2   CONNECTED] listes identiques


------------------------
Tests Sur la classe NetworkInteractionController
------------------------
------------------------
Test de d�connexion
------------------------
Thread presence start !
Test OK !
Connected to Multicast group
------------------------
Test r�ception de message de pr�sence
------------------------
Thread reception Start
Thread presence start !
NEW COME : localuser
[localuser   CONNECTED]
[localuser   CONNECTED]
Test OK !
------------------------
Test activation de deux messages de pr�sence
------------------------
Thread presence start !
[localuser   CONNECTED]
Test OK !
------------------------
Test message de pr�sence utilisateur null
------------------------
Thread reception Start
Test OK !
------------------------
Test message de d�connexion
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
Test envoie de 100 messages coucou diff�rents
------------------------
Test OK !
