
Résumé scéance AL

Format des objets

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

Objectifs de la semaine prochaine :

Implémenter toutes les boites autout du simulateur avec du code simple (print...)
Modifier la spec
Avoir un walking skeleton complet : le code doit parcourir TOUTES les boites avec un résultat simple

La semaine d'après : déployement + bugs fixes

il reste 2 semaines avant le POC

Justification Base SQL
Map originale -> pouvoir la modifier mais retrouver l'originale
elle peut avoir n map édited
Chaque map éditée : 1 résultat
tous les résultats sont indépendants
