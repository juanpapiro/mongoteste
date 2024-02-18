docker exec -i svr1 mongosh --host localhost --port 10001 <<EOF
var config = {
    "_id": "cfgrsvr",
    "configsvr": true,
    "members": [
        {
            "_id": 0,
            "host": "svr1:10001"
        },
        {
            "_id": 1,
            "host": "svr2:10002"
        },
        {
            "_id": 2,
            "host": "svr3:10003"
        }
    ]
};
rs.initiate(config);
rs.status();
EOF

docker exec -i srd1 mongosh --host localhost --port 11001 <<EOF
var shardconfigsrd1 = {"_id":"cfgsrd1","members":[{"_id":0,"host":"srd1:11001"}]};
rs.initiate(shardconfigsrd1);
rs.status();
EOF

docker exec -i srd2 mongosh --host localhost --port 11002 <<EOF
var shardconfigsrd2 = {"_id":"cfgsrd2","members":[{"_id":0,"host":"srd2:11002"}]};
rs.initiate(shardconfigsrd2);
rs.status();
EOF