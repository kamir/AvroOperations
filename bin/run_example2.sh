##
#
# Clean, build and execute the ModuleA class ...
#
##

export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home

cd ..
mvn clean compile exec:java -Dexec.mainClass="packageA.ModuleA"

