R�sum� sc�ance AL

Format des objets

input du programme : objet carte
2 attributs
- liste d'adjacence (arraylist d'arraylist d'int)
- matrice des couts (liste de costs) (cost : matrice)

�diteur de carte : copie de la carte, pouvoir modifier la liste d'adjacence et la matrice des couts

simulateur : il s'occupe de modifier la carte en graphe et de faire les calculs

sortie du simulateur : objet de type r�sultat / compte rendu

- strings (max flow, liste ar�tes, proposition d'am�lioration des ar�tes...)
- maps : map de base (type carte) et carte �dit�e (type carte)

pour l'enregistrer : scinde en 3 
-simu1.data : contient les strings
-simu1.base : map de base
-simu1.edited : map �dit�e

Objectifs de la semaine prochaine :

Impl�menter toutes les boites autout du simulateur avec du code simple (print...)
Modifier la spec
Avoir un walking skeleton complet : le code doit parcourir TOUTES les boites avec un r�sultat simple

La semaine d'apr�s : d�ployement + bugs fixes

il reste 2 semaines avant le POC

Justification Base SQL
Map originale -> pouvoir la modifier mais retrouver l'originale
elle peut avoir n map �dited
Chaque map �dit�e : 1 r�sultat
tous les r�sultats sont ind�pendants
