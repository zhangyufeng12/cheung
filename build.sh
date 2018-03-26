#!/usr/bin/env bash

# NOTE
# 1. This script only fits for springboot-gradle-build-jar-style project.
# 2. DO NOT set version info in your build config, because I don't know how to deal with it

JAVA_HOME="/home/xiaoju/tools/jdk1.8.0_144"
CLASSPATH="${JAVA_HOME}/lib/dt.jar:${JAVA_HOME}/lib/tools.jar"
PATH="${JAVA_HOME}/bin:${PATH}"
export JAVA_HOME CLASSPATH PATH

archives_base_name=`gradle properties | sed '/archivesBaseName:.*/!d;s/archivesBaseName: //'`
build_libs_dir=`gradle properties | sed '/libsDir:.*/!d;s/libsDir: //'`
final_archive_file_name="${archives_base_name}-0.0.1-SNAPSHOT.jar"
final_archive_file_path="${build_libs_dir}/${final_archive_file_name}"
scmpf_output_dir="./output"

# check
if [ -z ${scmpf_output_dir} ] ; then
    echo "Variable 'scmpf_output_dir' not set. Abort."
    exit 1
fi

# clean last building
gradle clean
if [ $? -gt 0 ] ; then
    echo "Execute 'gradle clean' failed. Abort"
    exit 1
fi 

# prepare
if [ ! -d ${scmpf_output_dir} ] ; then
    mkdir -p ${scmpf_output_dir}
    echo "Create dir '${scmpf_output_dir}'"
else
    for file in `ls ${scmpf_output_dir}`
    do
        rm "${scmpf_output_dir}/${file}"
        if [ $? -gt 0 ] ; then
            echo "Remove file ${scmpf_output_dir}/${file} failed"
            echo "Build failed. Abort."
            exit 1
        else
            echo "Removed a file: '${scmpf_output_dir}/$file'"
        fi
    done
fi

# build
gradle clean build
if [ $? -gt 0 ] ; then
    echo "Execute 'gradle build' failed."
    exit 1
fi

# find target jar file
if [ ! -f "${final_archive_file_path}" ] ; then
    echo "Target jar file not found: ${final_archive_file_path}"
    echo "Abort"
    exit 1
fi

# generate file
cp "${final_archive_file_path}" "${scmpf_output_dir}/${final_archive_file_name}"
echo "cp '${final_archive_file_path}' '${scmpf_output_dir}/${final_archive_file_name}'"
sed "s/%%PROGRAM_NAME%%/${archives_base_name}/g" ./control.sh > "${scmpf_output_dir}/control.sh"
echo "generate '${scmpf_output_dir}/control.sh'"

echo "Build successfully."
