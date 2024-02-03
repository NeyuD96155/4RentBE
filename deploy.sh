echo "Building app..."
./mvnw clean install

echo "Deploy files to server..."
scp -r -i "D:\levietquocthinh" target/be.jar root@128.199.123.86:/var/www/be

ssh -i "D:\levietquocthinh" root@128.199.123.86 <<EOF
pid=\$(sudo lsof -t -i :8081)

if [ -z "\$pid" ]; then
    echo "Start server..."
else
    echo "Restart server..."
    sudo kill -9 "\$pid"
fi
cd /var/www/be
java -jar be.jar
EOF
exit
echo "Done!"