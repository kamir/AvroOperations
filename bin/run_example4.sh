##
#
# Clean, build and execute the ModuleC class ...
#
##

export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home

cd ..
mvn clean compile exec:java -Dexec.mainClass="packageC.ModuleC"

