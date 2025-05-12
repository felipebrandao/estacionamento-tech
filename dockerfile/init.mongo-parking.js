db = db.getSiblingDB('parking');
db.createUser({
    user: 'mongodb',
    pwd: 'mongodb',
    roles: [{ role: 'readWrite', db: 'parking' }]
});

db.parkingSpots.insertMany([
    {
        "_id": new BinData(3,"xUWGNkrN7ZBX9UK9nnamow=="),
        "neighborhood": "Bela Vista",
        "postalCode": "01310-000",
        "numberRange": "1000 a 1500",
        "street": "Avenida Paulista",
        "_class": "com.fiap.techchallenge.parking.model.ParkingSpot"
    },
    {
        "_id": new BinData(3,"MEpQBDcYhC1QeObAx0xmgg=="),
        "neighborhood": "Consolação",
        "postalCode": "01304-000",
        "numberRange": "500 a 1000",
        "street": "Rua Augusta",
        "_class": "com.fiap.techchallenge.parking.model.ParkingSpot"
    }
]);

