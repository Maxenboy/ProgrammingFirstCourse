#!/bin/sh
#TODO: Make it so that the user prompts one password for all the password prompts

read -p "Name of the user agent: " username 


if [ ! -e ca-key.pem ] || [ ! -e ca-certificate.pem ]; then
    echo Creating a key and certificate for CA, outputted files are: ca-key.pem, ca-certificate.pem
    openssl req -new -x509 -keyout ca-key.pem -out ca-certificate.pem -days 1825
fi

if [ ! -e serverKS ]; then
	echo "Create server certificate"
	keytool -keystore serverKS -genkey -alias server
	
	echo "Signing request for server cert"
	keytool -keystore serverKS -certreq -alias server -keyalg rsa -file server.csr
	
	echo "Import CA into server KS"
	keytool -import -keystore serverKS -file ca-certificate.pem -alias ca
	
	echo "Sign server certificate"
	openssl x509 -req -CA ca-certificate.pem -CAkey ca-key.pem -in server.csr -out server.cer -days 1825 -CAcreateserial
	
	echo "Import server certificate into server keystore"
	keytool -import -keystore serverKS -file server.cer -alias server
	
	echo "Create common client truststore and import CA"
	keytool -import -keystore clientTS -file ca-certificate.pem -alias ca
	
	echo "Import server certificate into client truststore"
	keytool -import -keystore clientTS -file server.cer -alias server	
fi


if [[ -e $username ]]; then
    echo "User $username already exists! Please try another username and run the script again"
else
    echo "Create a client certificate"
    keytool -keystore $username -genkey -alias $username

    echo "Generates a Certificate Signing Request, csr"
    keytool -keystore $username -certreq -alias $username -keyalg rsa -file $username.csr

    echo "Imports CA certificate into keystore"
    keytool -import -keystore $username -file ca-certificate.pem -alias ca

    echo "Generate a signed certificate for the associated Certificate Signing Request."
    openssl x509 -req -CA ca-certificate.pem -CAkey ca-key.pem -in $username.csr -out $username.cer -days 1825 -CAcreateserial

    echo "Imports the the signed certificate into the keystore $username"
    keytool -import -keystore $username -file $username.cer -alias $username

    echo "Importing the signed user certificate to the server truststore"
    keytool -import -alias $username -file $username.cer -keystore serverTS.jks 
fi
