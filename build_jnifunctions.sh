gcc -fPIC -I/usr/lib/jvm/java-21-openjdk/include/linux -I/usr/lib/jvm/java-21-openjdk/include ./src/main/c/jnifunctions.c -c -o ./lib/jnifunctions.o
gcc -shared ./lib/jnifunctions.o -o ./lib/jnifunctions.so
