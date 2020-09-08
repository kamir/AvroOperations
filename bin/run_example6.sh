cd ..

##
#
# This project uses two different schema files to generate a java class of the same name in the same package.
#
##

export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home

#
# unflag the avsc file so that it is used for source code generation. Backup schema files and generated java class
#
# all flagged files with extension "avsc_" will be ignored in the maven plugin.
#
mv src/main/avro/mmpojo_v2.avsc_ src/main/avro/mmpojo_v2.avsc
mv src/main/avro/mmpojo.avsc src/main/avro/mmpojo.avsc_
#
# the generated class is moved out for comparison
#
mv src/main/java/data/MMPojo.java data/out/MMPojo.java

mvn clean generate-sources

#
# Now we switch back the flags so that version 1 is used for durther work.
#
mv src/main/avro/mmpojo_v2.avsc src/main/avro/mmpojo_v2.avsc_
mv src/main/avro/mmpojo.avsc_ src/main/avro/mmpojo.avsc
