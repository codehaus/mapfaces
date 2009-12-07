
Profile Execution Examples:

JBoss Portal 2.7.0.GA + JBoss AS 4.2.2 (Bundled)
run the following from a command line:
=========
mvn install -Plocal-portal cargo:start
mvn cargo:deploy -Plocal-portal
 - visit the RichFaces tab

To use a locally configured server bundled with portal:

JBoss Portal 2.7.0.GA
-----------------------
mvn install cargo:start -Plocal-portal -DJBOSS_ZIP_HOME=/{path to zipped portal + JBoss AS}/jboss-portal-2.7.0.GA-bundled.zip -DJBOSS_HOME_DIR=jboss-portal-2.7.0.GA-bundled/jboss-portal-2.7.0.GA
mvn cargo:deploy -Plocal-portal

*Note - the variable for JBOSS_HOME_DIR is related to how you zip the server directory. If you zip the files
under JBOSS_HOME/* then it will only be the name of your archive. But if you zip the actual folder JBOSS_HOME then
JBOSS_HOME_DIR must be defined as 'zip file name/JBOSS_HOME folder name' as shown above.