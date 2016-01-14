cd rentalexchange-backend
mvn -gs WIKI/maven/settings.xml -PliquibaseProps -PdeployDBSchema clean install > build.log
