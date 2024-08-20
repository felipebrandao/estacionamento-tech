db = db.getSiblingDB('estacionamento');
db.createUser({
    user: 'user',
    pwd: 'password',
    roles: [{ role: 'readWrite', db: 'estacionamento' }]
});

db.localEstacionamento.insertMany([
    {
        _id: UUID("3b89e93b-f6b5-423c-8926-84fdcfbf98aa"),
        logradouro: "Avenida Paulista",
        bairro: "Bela Vista",
        cep: "01311-000",
        intervaloDeNumero: "1000-2000"
    },
    {
        _id: UUID("4a19c400-8ea8-4481-8c04-60783e90cd9a"),
        logradouro: "Rua Augusta",
        bairro: "Consolação",
        cep: "01304-000",
        intervaloDeNumero: "500-1500"
    }
]);

db.veiculoEstacionado.insertMany([
    {
        idUsuario: UUID("1a417508-a5ee-4c8b-88e0-e6049e60bb72"),
        idVeiculo: UUID("0182e7e6-72f5-4b51-9229-967b16369b71"),
        idLocalEstacionamento: UUID("3b89e93b-f6b5-423c-8926-84fdcfbf98aa"),
        dataHoraInicio: ISODate("2024-07-26T12:00:00Z"),
        status: true,
        dataHoraExpira: ISODate("2024-07-26T14:00:00Z"),
        notificacaoEnviada: false
    }
]);
