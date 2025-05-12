db = db.getSiblingDB('parking');

db.vehicles.insertMany([
    {
        "_id": new BinData(3, "s0mxHXDOaVXefDsKVMfAnw=="),
        "userId": new BinData(3, "7ERiOO0pyb8blPwUezF2vg=="),
        "brand": "HYUNDAI",
        "model": "IX35",
        "licensePlate": "XPTO",
        "_class": "com.fiap.techchallenge.veiculo.model.Veiculo"
    }
]);
