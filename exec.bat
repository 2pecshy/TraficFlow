
SET DEVELOPMENT_HOME=C:\Users\Jeremy\IdeaProjects\TraficFlow

cd %DEVELOPMENT_HOME%\webconfig
start mvn clean package spring-boot:run

cd %DEVELOPMENT_HOME%Ffacade
start mvn clean package spring-boot:run

cd %DEVELOPMENT_HOME%\simulateur
start mvn clean package spring-boot:run

cd %DEVELOPMENT_HOME%\Observateur
mvn clean package spring-boot:run

