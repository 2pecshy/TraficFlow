=============================================================
======================Trafic Flow Tools======================
=============================================================
Script ip_config.sh:

./ip_config.sh [interface] [username]

Ce script génere les fichier *.yml avec l'ip de 'interface reseau choisi ainsi que l'username pour le bus rabbitMQ.

======================================================================
Script rabbitMQ_flush_all_queues.sh (A run en Root):

Ce script efface toutes les queues restantes dans le bus de rabbitMQ

======================================================================
Script rabbitmq_config.sh (A run en Root):

Ce script ajoute automatiquement l'utilisateur docko a rabbitMQ pour l'utilisation des conteneurs Docker

======================================================================

Script send_post.sh:

usage: ./send_post.sh [nb rep] option[JSON_FILE]

Ce script envoie nb_rep fois le json JSON_FILE par requete POST au service facade

======================================================================
