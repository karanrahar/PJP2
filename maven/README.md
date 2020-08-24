Benefits of local repository

Maven local repository keeps your project's all dependencies (library jars, plugin jars etc.). When you run a Maven build, then Maven automatically downloads all the dependency jars into the local repository. 
It helps to avoid references to dependencies stored on remote machine every time a project is build. Reduced likelihood of version conflicts. Faster loading during the first build.
