db = db.getSiblingDB('veiculo');

db.veiculo.insertMany([
    {
        _id: UUID("0182e7e6-72f5-4b51-9229-967b16369b71"),
        marca: "Toyota",
        modelo: "Corolla",
        placa: "ABC-1234",
        idUsuario: UUID("1a417508-a5ee-4c8b-88e0-e6049e60bb72")
    },
    {
        _id: UUID("97d48eef-5d67-43f2-9dad-63bd8a0493a6"),
        marca: "Honda",
        modelo: "Civic",
        placa: "DEF-5678",
        idUsuario: UUID()
    }
]);
