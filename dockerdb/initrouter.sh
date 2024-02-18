docker exec -i router mongosh --host localhost --port 13001 <<EOF
sh.startBalancer();
sh.addShard("cfgsrd1/srd1:11001");
sh.addShard("cfgsrd2/srd2:11002");
sh.enableSharding("parking");
EOF